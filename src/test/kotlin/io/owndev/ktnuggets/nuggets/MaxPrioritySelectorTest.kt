package io.owndev.ktnuggets.nuggets

import io.owndev.ktnuggets.nuggets.priority.MaxPrioritySelector
import io.owndev.ktnuggets.nuggets.priority.Prioritized
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.*
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
                override val id: UUID
                    get() = UUID.randomUUID()
                override val priority: Long = 58
                override fun compare(o1: Long?, o2: Long?): Int = compareInternal(o1, o2)
            },
            object : Prioritized {
                override val id: UUID
                    get() = UUID.randomUUID()
                override val priority: Long = 56
                override fun compare(o1: Long?, o2: Long?): Int = compareInternal(o1, o2)
            },
            object : Prioritized {
                override val id: UUID
                    get() = UUID.randomUUID()
                override val priority: Long = 76
                override fun compare(o1: Long?, o2: Long?): Int = compareInternal(o1, o2)
            },
            object : Prioritized {
                override val id: UUID
                    get() = UUID.randomUUID()
                override val priority: Long = 56
                override fun compare(o1: Long?, o2: Long?): Int = compareInternal(o1, o2)
            }
        )

        fun compareInternal(o1: Long?, o2: Long?): Int {
            return if (o1 == null) {
                -1
            } else if (o2 == null) {
                1
            }  else if (o1 > o2) {
                1
            } else {
                0
            }
        }
    }
}