package com.achievesoft.sunday.services.configreaders

import com.achievesoft.sunday.models.ConfigPayload
import com.achievesoft.sunday.models.Pipeline

interface ConfigReader {
    val versionCode: String
    fun read(configPayload: ConfigPayload): Pipeline
}
