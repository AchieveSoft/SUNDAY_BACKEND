package com.achievesoft.sunday.models

data class ConfigPayload(
    val version: String,
    val payload: Map<String, Any>,
    val createBy: String
)
