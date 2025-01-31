package sorting


data class Arguments(
    val dataType: DataType,
    val sortingType: SortingType,
    val inputFile: String? = null,
    val outputFile: String? = null
)

object ArgumentProcessor {
    private val validArgs = listOf("-dataType", "-sortingType", "-inputFile", "-outputFile")

    fun processArguments(args: Array<String>): Arguments {
        checkUnknownParameters(args)

        return Arguments(
            dataType = processDataType(args),
            sortingType = processSortingType(args),
            inputFile = processFile(args, "-inputFile"),
            outputFile = processFile(args, "-outputFile")
        )
    }

    private fun checkUnknownParameters(args: Array<String>) {
        args.filter { it.startsWith("-") }
            .filter { it !in validArgs }
            .forEach { ErrorHandler.printInvalidParameter(it) }
    }

    private fun processFile(args: Array<String>, paramName: String): String? {
        if (!args.contains(paramName)) return null

        val index = args.indexOf(paramName)
        if (index + 1 >= args.size || args[index + 1].startsWith("-")) {
            ErrorHandler.printNoFilename(paramName)
            return null
        }

        return args[index + 1]
    }

    private fun processDataType(args: Array<String>): DataType {
        if (!args.contains("-dataType")) return DataType.WORD

        val dataTypeIndex = args.indexOf("-dataType")
        if (dataTypeIndex + 1 >= args.size || args[dataTypeIndex + 1].startsWith("-")) {
            ErrorHandler.printNoDataType()
            return DataType.WORD
        }

        return try {
            DataType.valueOf(args[dataTypeIndex + 1].uppercase())
        } catch (e: IllegalArgumentException) {
            DataType.WORD
        }
    }

    private fun processSortingType(args: Array<String>): SortingType {
        if (!args.contains("-sortingType")) return SortingType.NATURAL

        val sortingTypeIndex = args.indexOf("-sortingType")
        if (sortingTypeIndex + 1 >= args.size || args[sortingTypeIndex + 1].startsWith("-")) {
            ErrorHandler.printNoSortingType()
            return SortingType.NATURAL
        }

        return try {
            SortingType.valueOf(args[sortingTypeIndex + 1].uppercase())
        } catch (e: IllegalArgumentException) {
            SortingType.NATURAL
        }
    }
}
