package dpallas.kotlin.listextensions

import java.util.*

/**
 * splits a list into sublists
 * @param partitionSize the max size of each sublist. The last sub list may be shorter.
 * @return a List of Lists of T
 */
fun <T> List<T>.split(partitionSize: Int): List<List<T>> {
    if(this.isEmpty()) return emptyList()
    if(partitionSize < 1) throw IllegalArgumentException("partitionSize must be positive")

    val result = ArrayList<List<T>>()
    var entry = ArrayList<T>(partitionSize)
    for (item in this) {
        if(entry.size == partitionSize) {
            result.add(entry)
            entry = ArrayList<T>()
        }
        entry.add(item)
    }
    result.add(entry)
    return result
}

/**
 * like map, but multithreaded. It uses the number of cores + 2 threads.
 */
inline fun <T,V> List<T>.pmap(operation: (T) -> V): List<V> {
    val threads = ArrayList<Thread>()
    val cores = Runtime.getRuntime().availableProcessors();
    // run each thread on a partitioned block to minimize thread setup/teardown
    val partitioned = this.split(cores + 2);
    val partitionedResult = partitioned.map { partition ->
        val thread = Thread()
        threads.add(thread)
        thread.run {
            partition.map(operation)
        }
    }
    // wait for threads to finish
    for (thread in threads) {
        thread.join()
    }

    return partitionedResult.flatten()
}