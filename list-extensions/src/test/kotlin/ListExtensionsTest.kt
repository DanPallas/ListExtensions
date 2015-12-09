package dpallas.kotlin.listextensions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class ListExtensionTest() {
    @Test fun whenSplitIsCalledOnEmptyListItReturnsEmptyList() {
        val input = emptyList<Int>()
        val result = input.split(5)
        assertEquals(listOf<List<Int>>(), result)
    }

    @Test fun whenSplitIsCalledWithPartitionSizeOfOne() {
        val input = listOf(1,2)
        val result = input.split(1)
        assertEquals(listOf(listOf(1), listOf(2)), result)
    }

    @Test fun whenSplitIsCalledOnListOfSizeOne() {
        val input = listOf(1)
        val result = input.split(10)
        assertEquals(listOf(listOf(1)), result)
    }

    @Test fun whenSplitIsCalledOnListOfSizeThreeWithPartitionOfTow() {
        val input = listOf(1, 2, 3)
        val result = input.split(2)
        assertEquals(listOf(listOf(1,2), listOf(3)), result)
    }

    @Test fun whenSplitIscalledWithPartitionSizeLessThanOne() {
        val input = listOf(1, 2, 3)
        assertFailsWith(IllegalArgumentException::class) { input.split(0) }
    }

    @Test fun whenPmapIsCalledOnEmptyList() {
        val input = listOf<Int>()
        val result = input.pmap {it + 1}
        assertEquals(listOf<Int>(), result)
    }

    @Test fun whenPmapIsCalledOnListWithOneElement() {
        val input = listOf(1)
        val result = input.pmap {it + 1}
        assertEquals(listOf(2), result)
    }

    @Test fun whenPmapIsCalledOnListWithThreeElements() {
        val input = listOf(1,2,3)
        val result = input.pmap {it + 1}
        assertEquals(listOf(2,3,4), result)
    }
}

