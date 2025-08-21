package org.filomilo.AiTestGenerator.Tools

object System {
    var isWindows: Boolean = java.lang.System.getProperty("os.name")
        .lowercase().startsWith("windows")
}