package io.owndev.ktnuggets.designpattern

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

internal class ChainOfResponsibilityTest {

    @Test
    fun `should log according level`() {
        val logger: Logger = KLog()

        logger.log(DEBUG, "Value after calculation was ${Long.MAX_VALUE}")
        logger.log(INFO, "Calculation started")
        logger.log(WARN, "Value was not calculated setting defaults: ${Int.SIZE_BITS}")
        logger.log(ERROR, "System error: operation was not successful")
        assertEquals(logger.level, Int.MIN_VALUE)
        assertIs<Debug>(logger.next)
        assertIs<Info>(logger.next!!.next)
        assertIs<Warn>(logger.next!!.next!!.next)
        assertIs<Error>(logger.next!!.next!!.next!!.next)
        assertEquals(logger.next!!.next!!.next!!.next!!.next, null)
    }
}