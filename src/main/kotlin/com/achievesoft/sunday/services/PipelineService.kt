package com.achievesoft.sunday.services

import com.achievesoft.sunday.models.Pipeline
import com.achievesoft.sunday.models.responses.BaseResponse
import com.achievesoft.sunday.repositories.PipelineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PipelineService {
    @Autowired
    private lateinit var repository: PipelineRepository

    fun getPipelines(): BaseResponse<List<Pipeline>> = BaseResponse.success(data = repository.findAll())

    fun addPipeline(pipeline: Pipeline): BaseResponse<Any> {
        return try {
            repository.save(pipeline)
            BaseResponse.success(message = "add pipeline success")
        } catch (err: Exception) {
            BaseResponse.failed(message = "add pipeline failed: ${err.message}")
        }
    }
}
