package io.owndev.ktnuggets.nuggets.priority

import java.util.UUID

interface Prioritized: Comparator<Long> {
    val id: UUID
    val priority: Long
}