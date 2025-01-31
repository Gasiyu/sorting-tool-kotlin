package sorting

fun main(args: Array<String>) {
    val (dataType, sortingType, inputFile, outputFile) = ArgumentProcessor.processArguments(args)

    val processor = DataProcessor()
    processor.initializeInput(inputFile)

    val printer = ResultPrinter()
    val result = processor.processInput(dataType, sortingType)
    printer.printResult(result, outputFile)
}
