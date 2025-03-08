package com.achievesoft.sunday.services

import org.springframework.stereotype.Service
import java.io.File

@Service
class AgentService {
    fun executeTask(exec: String) {
        runCatching {
            val processBuilder = ProcessBuilder(exec.split(" "))
            val process = processBuilder.start()

            process.waitFor()
        }.onFailure {

        }
    }
}