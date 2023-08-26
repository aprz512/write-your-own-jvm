package write.your.own.jvm.test

class SortTest {

    fun main(args: Array<String>) {
        sort(1, 2) { a: Int, b: Int ->
            return@sort a < b
        }
    }

    fun sort(a: Int, b: Int, sortFunc: (Int, Int) -> Boolean) {
        val result = sortFunc(a, b)
    }

}