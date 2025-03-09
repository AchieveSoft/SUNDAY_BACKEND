package com.achievesoft.sunday.repositories

import com.achievesoft.sunday.models.Stage
import org.springframework.data.jpa.repository.JpaRepository

interface StageRepository : JpaRepository<Stage, Long>
