package com.achievesoft.sunday.controllers

import com.achievesoft.sunday.models.Pipeline
import com.achievesoft.sunday.models.responses.BaseResponse
import com.achievesoft.sunday.services.PipelineService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class PipelineController(private val pipelineService: PipelineService) {
    @GetMapping("/api/ping")
    fun ping(): BaseResponse<Any> = BaseResponse.success(message = "server is running")

    @GetMapping("/api/pipeline/get-pipelines")
    fun getPipelines(): BaseResponse<List<Pipeline>> = pipelineService.getPipelines()

    @PostMapping("/api/pipeline/add-pipeline")
    fun addPipeline(@RequestBody pipeline: Pipeline): BaseResponse<Any> = pipelineService.addPipeline(pipeline)
}
