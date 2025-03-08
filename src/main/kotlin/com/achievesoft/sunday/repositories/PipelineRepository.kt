package com.achievesoft.sunday.repositories

import com.achievesoft.sunday.models.Pipeline
import org.springframework.data.jpa.repository.JpaRepository

interface PipelineRepository : JpaRepository<Pipeline, Long>
