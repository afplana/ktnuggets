package io.owndev.ktnuggets.extension

fun <T> Collection<T>.filterOrNull(predicate: (item: T) -> Boolean): Collection<T>? = filter { predicate.invoke(it) }
    .takeIf { it.isNotEmpty() }