package io.owndev.ktnuggets.designpattern

import io.kotest.extensions.system.captureStandardOut
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

internal class CommandTest {

    @Test
    fun `should execute commands in list`() {
        val command1 = PrintCommand(object : PrintCommand.Printable {
            override fun toString(): String = "{ id: ${UUID.randomUUID()} }"
        })
        val command2 = SumCommand(1900, 6554534)

        val processor = CommandProcessor()

        val stdOut = captureStandardOut {
            processor.addCommand(command1)
            processor.addCommand(command2)
            processor.process()
        }
        assertNotEquals(stdOut, "")
    }
}