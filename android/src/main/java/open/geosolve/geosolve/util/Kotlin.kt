package open.geosolve.geosolve.util

inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}