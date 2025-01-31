package sorting

import java.io.File
import java.io.PrintWriter

class ResultPrinter {
    fun printResult(result: DataProcessor.ProcessingResult, outputFile: String?) {
        val output = buildString {
            when (result) {
                is DataProcessor.ProcessingResult.SortedTextResult -> appendTextResult(result)
                is DataProcessor.ProcessingResult.SortedNumbersResult -> appendNumberResult(result)
            }
        }

        if (outputFile != null) {
            try {
                PrintWriter(File(outputFile)).use { it.print(output) }
            } catch (e: Exception) {
                ErrorHandler.printFileError(outputFile, e.message ?: "Unknown error")
                println(output)
            }
        } else {
            println(output)
        }
    }

    private fun StringBuilder.appendNumberResult(result: DataProcessor.ProcessingResult.SortedNumbersResult) {
        appendLine("Total numbers: ${result.total}.")
        if (result.countMap.isNullOrEmpty()) {
            appendLine("Sorted data: ${result.sortedData.joinToString(" ")}")
        } else {
            result.sortedData.forEach { number ->
                val count = result.countMap[number] ?: 0
                val percentage = (count * 100) / result.total
                appendLine("$number: $count time(s), $percentage%")
            }
        }
    }

    private fun StringBuilder.appendTextResult(result: DataProcessor.ProcessingResult.SortedTextResult) {
        val itemType = if (result.isLine) "lines" else "words"
        appendLine("Total $itemType: ${result.total}.")
        if (result.countMap.isNullOrEmpty()) {
            append("Sorted data: ")
            if (result.isLine) {
                appendLine()
                appendLine(result.sortedData.joinToString("\n"))
            } else {
                appendLine(result.sortedData.joinToString(" "))
            }
        } else {
            result.sortedData.forEach { item ->
                val count = result.countMap[item] ?: 0
                val percentage = (count * 100) / result.total
                appendLine("$item: $count time(s), $percentage%")
            }
        }
    }
}
