package io.owndev.ktnuggets.nuggets

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.reduce

class MaxPrioritySelector(
    private val prioritized: Flow<Prioritized>
) {

    suspend fun maxBy(predicate: (item: Prioritized) -> Boolean): Prioritized = coroutineScope {
        prioritized
            .filter { predicate.invoke(it) }
            .reduce { accumulator, value ->
            if (accumulator.priority > value.priority) accumulator
            else value
        }
    }
}

interface Prioritized {
    val priority: Long
}
