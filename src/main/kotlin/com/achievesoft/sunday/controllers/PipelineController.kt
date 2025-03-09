package com.achievesoft.sunday.controllers

import com.achievesoft.sunday.models.ConfigPayload
import com.achievesoft.sunday.models.Pipeline
import com.achievesoft.sunday.models.Task
import com.achievesoft.sunday.models.responses.BaseResponse
import com.achievesoft.sunday.services.InternalAgentService
import com.achievesoft.sunday.services.PipelineService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class PipelineController(private val pipelineService: PipelineService, private val internalAgentService: InternalAgentService) {
    @GetMapping("/api/ping")
    fun ping(): BaseResponse<Any> = BaseResponse.success(message = "server is running")

    @GetMapping("/api/pipeline/get-pipelines")
    fun getPipelines(): BaseResponse<List<Pipeline>> = pipelineService.getPipelines()

    @PostMapping("/api/pipeline/add-pipeline")
    fun addPipeline(@RequestBody req: ConfigPayload): BaseResponse<Any> = pipelineService.addPipeline(req)

    @GetMapping("/api/pipeline/test-task")
    fun testAgent(): BaseResponse<Any> {
        val task = Task(
            taskCode = "task_test_001",
            exec = "ping -t google.com"
        )
        return internalAgentService.executeTask(task)
    }

    @GetMapping("/api/pipeline/test-get-task-output")
    fun getOutputTestTask(): BaseResponse<String> = internalAgentService.getOutput("task_test_001")

    @GetMapping("/api/pipeline/test-pause-task")
    fun stopTestTask(): BaseResponse<Any> = internalAgentService.pauseTask("task_test_001")

    @GetMapping("/api/pipeline/test-resume-task")
    fun resumeTestTask(): BaseResponse<Any> = internalAgentService.resumeTask("task_test_001")
}
