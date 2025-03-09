package com.achievesoft.sunday.models.config

import com.google.gson.annotations.SerializedName

data class TaskExecV010(
    @get:SerializedName("run")
    val run: String,
    @get:SerializedName("args")
    val args: List<String>?
)

data class TaskConfigV010(
    @get:SerializedName("taskName")
    val taskName: String,
    @get:SerializedName("exec")
    val exec: TaskExecV010
)

data class StageConfigV010(
    @get:SerializedName("taskName")
    val stageName: String,
    @get:SerializedName("taskName")
    val tasks: List<TaskConfigV010>?
)

data class ConfigV010(
    @get:SerializedName("name")
    val name: String,
    @get:SerializedName("description")
    val description: String?,
    @get:SerializedName("stages")
    val stages: List<StageConfigV010>?
)
