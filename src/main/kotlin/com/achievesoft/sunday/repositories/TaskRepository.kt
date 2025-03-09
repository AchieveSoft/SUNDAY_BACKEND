package com.achievesoft.sunday.repositories

import com.achievesoft.sunday.models.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>
