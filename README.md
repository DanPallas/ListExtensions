# Kotlin ListExtensions

Kotlin was missing a couple of useful list functions that I grew used to in clojure, 
so I wrote them here. 

### pmap

Works like List.map, but is multithreaded. It uses (<number of cores> + 2) threads.

ex:

```kotlin
val list = listOf(1, 2, 3)
val newList = list.pmap { it + 1}
assertEquals(listOf(2, 3, 4), newList)
```

### split

I would have called it partition, but htat name was already taken. It divides a list into sublists. I would have called it partition, but that name was already taken. 

```kotlin
val list = listOf(1, 2, 3, 4, 5)
val splitList = list.split(2)
assertEquals(listOf(listOf(1, 2), listOf(3, 4), listOf(5)), splitList)
```
