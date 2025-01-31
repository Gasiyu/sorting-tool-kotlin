package sorting

object ErrorHandler {
    fun printInvalidParameter(parameter: String) {
        println("\"$parameter\" is not a valid parameter. It will be skipped.")
    }

    fun printInvalidLongValue(value: String) {
        println("\"$value\" is not a long. It will be skipped.")
    }

    fun printNoSortingType() {
        println("No sorting type defined!")
    }

    fun printNoDataType() {
        println("No data type defined!")
    }

    fun printNoFilename(paramName: String) {
        println("No filename defined for $paramName!")
    }

    fun printFileError(filename: String, error: String) {
        println("Error reading/writing file '$filename': $error")
    }
}
