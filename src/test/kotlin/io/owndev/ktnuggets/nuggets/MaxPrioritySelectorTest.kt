package io.owndev.ktnuggets.nuggets

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class MaxPrioritySelectorTest {

    @Test
    fun `should return element with highest priority`() = runBlocking {
        val response = MaxPrioritySelector(TestData.prioritizedElementsSupplier()).maxBy { true }
        assertEquals(response.priority, 76)
    }

    object TestData {
        fun prioritizedElementsSupplier() = flowOf(
            object : Prioritized {
                override val priority: Long = 58
            },
            object : Prioritized {
                override val priority: Long = 56
            },
            object : Prioritized {
                override val priority: Long = 76
            },
            object : Prioritized {
                override val priority: Long = 56
            }
        )
    }
}