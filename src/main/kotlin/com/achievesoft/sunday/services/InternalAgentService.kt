package com.achievesoft.sunday.services

import com.achievesoft.sunday.models.Task
import com.achievesoft.sunday.models.responses.BaseResponse
import com.achievesoft.sunday.utils.OsCheckUtil
import com.achievesoft.sunday.utils.OsType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class InternalAgentService {
    companion object {
        private val taskMap = ConcurrentHashMap<String, Task>()
        private val processMap = ConcurrentHashMap<String, Process>()
        private val outputListMap = ConcurrentHashMap<String, MutableList<String>>()
        private val errorListMap = ConcurrentHashMap<String, MutableList<String>>()
    }

    private fun startProcess(command: String): Pair<ProcessBuilder, Process> {
        val processBuilder = ProcessBuilder(command.split(" "))
        val process = processBuilder.start()
        return Pair(processBuilder, process)
    }

    private fun exec(command: String) {
        val execPayload = command.split(" ").toTypedArray()
        Runtime.getRuntime().exec(execPayload)
    }

    fun executeTask(task: Task): BaseResponse<Any> {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            val processStartResult = startProcess(task.exec)
            val process = processStartResult.second
            taskMap[task.taskCode] = task
            processMap[task.taskCode] = process

            val outputList = mutableListOf<String>()
            val errorList = mutableListOf<String>()
            outputListMap[task.taskCode] = outputList
            errorListMap[task.taskCode] = outputList

            launch {
                process.inputReader().use { reader ->
                    reader.lines().forEach {
                        outputList.add(it)
                    }
                }
            }

            launch {
                process.inputReader().use { reader ->
                    reader.lines().forEach {
                        errorList.add(it)
                    }
                }
            }

            process.waitFor()
            //todo: [Nil] - Keep output on db.
            processMap.remove(task.taskCode)
            outputListMap.remove(task.taskCode)
            errorListMap.remove(task.taskCode)

        }

        return BaseResponse.success("task is running")
    }

    fun getOutput(taskCode: String): BaseResponse<String> {
        val outputText = if (processMap.keys.contains(taskCode))
            outputListMap[taskCode]?.joinToString("\n")
        else
            "" //todo: [Nil] - Get from db.

        return BaseResponse.success<String>(data = outputText)
    }

    fun getError(taskCode: String): BaseResponse<String> {
        val errorText = if (processMap.keys.contains(taskCode))
            errorListMap[taskCode]?.joinToString("\n")
        else
            "" //todo: [Nil] - Get from db.

        return BaseResponse.success<String>(data = errorText)
    }

    fun pauseTask(taskCode: String): BaseResponse<Any> {
        processMap[taskCode]?.destroy()
        return BaseResponse.success("pause success")
    }

    fun resumeTask(taskCode: String): BaseResponse<Any> {
        taskMap[taskCode]?.let { executeTask(it) }
        return BaseResponse.success("resume success")
    }

    @Deprecated("[Nil] - Note: not working")
    fun pauseTaskActualProcess(taskCode: String): BaseResponse<Any> {
        processMap[taskCode]?.let { pid ->
            when (OsCheckUtil.getOsType()) {
                OsType.Windows -> exec("cmd /c taskkill /F /PID $pid")
                OsType.Linux, OsType.Unix -> exec("kill -SIGSTOP $pid")
                else -> {}
            }
        }

        return BaseResponse.success("pause success")
    }

    @Deprecated("[Nil] - Note: not working")
    fun resumeTaskActualProcess(taskCode: String): BaseResponse<Any> {
        processMap[taskCode]?.let{ pid ->
            when (OsCheckUtil.getOsType()) {
                OsType.Windows -> exec("cmd /c wmic process where ProcessId=$pid call resume")
                OsType.Linux, OsType.Unix -> exec("kill -SIGCOUNT $pid")
                else -> {}
            }
        }

        return BaseResponse.success("resume success")
    }
}
