package com.achievesoft.sunday.services.configreaders

import com.achievesoft.sunday.models.ConfigPayload
import com.achievesoft.sunday.models.Pipeline
import com.achievesoft.sunday.models.Stage
import com.achievesoft.sunday.models.Task
import com.achievesoft.sunday.models.config.ConfigV010
import com.google.gson.Gson
import org.springframework.stereotype.Service
import java.util.Date
import java.util.UUID

@Service
class ConfigReaderV010 : ConfigReader {
    override val versionCode: String = "0.1.0"

    override fun read(configPayload: ConfigPayload): Pipeline {
        return try {
            val pipeline = Pipeline()
            val gson = Gson()
            val payloadStr = gson.toJson(configPayload.payload)
            val config = gson.fromJson(payloadStr, ConfigV010::class.java)
            val pipelineCode = UUID.randomUUID().toString()

            pipeline.pipelineCode = pipelineCode
            pipeline.name = config.name
            pipeline.description = config.description
            pipeline.stages = config.stages?.map { stage ->
                if (stage.tasks.isNullOrEmpty())
                    throw Exception("empty task on stage: ${stage.stageName}")

                val stageCode = UUID.randomUUID().toString()

                Stage(
                    pipelineCode = pipelineCode,
                    stageCode = stageCode,
                    name = stage.stageName,
                    tasks = stage.tasks.map { task ->
                        if (task.exec.run.isBlank())
                            throw Exception("empty execute task on task: ${task.taskName}")

                        Task(
                            taskCode = UUID.randomUUID().toString(),
                            name = task.taskName,
                            exec = "${task.exec.run} ${task.exec.args?.joinToString()}"
                        )
                    }
                )
            }?.toList() ?: listOf()
            pipeline.createDate = Date()
            pipeline.createBy = configPayload.createBy

            pipeline
        } catch (err: Exception) {
            throw Exception("invalid config: ${err.message}")
        }
    }
}
