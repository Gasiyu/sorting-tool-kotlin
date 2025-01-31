package sorting

import java.io.File
import java.util.*

class DataProcessor(private var scanner: Scanner = Scanner(System.`in`)) {
    sealed class ProcessingResult {
        data class SortedNumbersResult(
            val total: Int,
            val sortedData: List<Long>,
            val countMap: Map<Long, Int>? = null
        ) : ProcessingResult()

        data class SortedTextResult(
            val total: Int,
            val sortedData: List<String>,
            val countMap: Map<String, Int>? = null,
            val isLine: Boolean
        ) : ProcessingResult()
    }

    fun initializeInput(inputFile: String?) {
        if (inputFile == null) return

        try {
            scanner = Scanner(File(inputFile))
        } catch (e: Exception) {
            ErrorHandler.printFileError(inputFile, e.message ?: "Unknown error")
            scanner = Scanner(System.`in`)
        }
    }

    fun processInput(type: DataType, sortingType: SortingType): ProcessingResult {
        return when (type) {
            DataType.LONG -> processNumbers(sortingType)
            DataType.LINE -> processTexts(isLine = true, sortingType)
            DataType.WORD -> processTexts(isLine = false, sortingType)
        }
    }

    private fun processTexts(isLine: Boolean, sortingType: SortingType): ProcessingResult.SortedTextResult {
        val items = buildList {
            if (isLine) while (scanner.hasNextLine()) {
                add(scanner.nextLine())
            } else while (scanner.hasNext()) {
                add(scanner.next())
            }
        }

        return when (sortingType) {
            SortingType.NATURAL -> ProcessingResult.SortedTextResult(
                total = items.size,
                sortedData = items.sorted(),
                countMap = null,
                isLine = isLine
            )

            SortingType.BYCOUNT -> {
                val countMap = items.groupingBy { it }.eachCount()
                ProcessingResult.SortedTextResult(
                    total = items.size,
                    sortedData = items.distinct().sortedWith(compareBy<String> { countMap[it] }.thenBy { it }),
                    countMap = countMap,
                    isLine = isLine
                )
            }
        }
    }

    private fun processNumbers(sortingType: SortingType): ProcessingResult.SortedNumbersResult {
        val numbers = buildList {
            while (scanner.hasNextLong()) {
                add(scanner.nextLong())
            }
        }

        return when (sortingType) {
            SortingType.NATURAL -> ProcessingResult.SortedNumbersResult(
                total = numbers.size,
                sortedData = numbers.sorted(),
                countMap = null
            )

            SortingType.BYCOUNT -> {
                val countMap = numbers.groupingBy { it }.eachCount()
                ProcessingResult.SortedNumbersResult(
                    total = numbers.size,
                    sortedData = numbers.distinct().sortedWith(compareBy<Long> { countMap[it] }.thenBy { it }),
                    countMap = countMap
                )
            }
        }
    }
}
