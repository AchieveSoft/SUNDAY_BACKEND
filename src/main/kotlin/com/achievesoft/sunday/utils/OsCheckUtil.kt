package com.achievesoft.sunday.utils

import java.util.Locale

enum class OsType {
    Windows,
    Linux,
    Unix,
    Unknown
}

object OsCheckUtil {
    fun getOsType(): OsType {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())

        return when  {
            osName.contains("win") -> OsType.Windows
            osName.contains("linux") -> OsType.Linux
            osName.contains("nix") || osName.contains("nux") || osName.contains("mac") -> OsType.Unix
            else -> OsType.Unknown
        }
    }
}