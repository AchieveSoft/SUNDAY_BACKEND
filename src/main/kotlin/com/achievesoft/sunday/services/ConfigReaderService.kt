package com.achievesoft.sunday.services

import com.achievesoft.sunday.models.ConfigPayload
import com.achievesoft.sunday.models.Pipeline
import com.achievesoft.sunday.services.configreaders.ConfigReader
import com.achievesoft.sunday.services.configreaders.ConfigReaderV010
import org.springframework.stereotype.Service

@Service
class ConfigReaderService {
    companion object {
        private val readerImplList = listOf<ConfigReader>(
            ConfigReaderV010()
        )
    }

    fun read(config: ConfigPayload): Pipeline {
        return try {
            val reader = readerImplList.first { it.versionCode == config.version }
            reader.read(config)
        } catch (_: Exception) {
            throw Exception("invalid config")
        }
    }
}
