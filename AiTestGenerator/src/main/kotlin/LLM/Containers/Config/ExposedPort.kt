package org.filomilo.AiTestGenerator.LLM.Containers.Config

data class ExposedPort(
    val internalPort: Int, val exposedPort: Int
)