package io.owndev.ktnuggets.nuggets.priority

class PrioritizedReorderHandler(
    private val prioritizedItems: List<Prioritized>
) {

    fun reorder(prioritized: Prioritized): List<Prioritized> {
        val prio = prioritizedItems.firstOrNull { it.id == prioritized.id }
        requireNotNull(prio) {"Element does not exist in the current collection! No action required"}

        if (prio.priority > prioritized.priority) {
            prioritizedItems.map {
                it.priority
            }
        }

        return prioritizedItems
    }
}