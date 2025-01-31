# Sorting Tool

A command-line utility for sorting various types of data (numbers, words, and lines) with different sorting strategies and flexible input/output options.

## Description

Sorting Tool is a versatile command-line application that can process and sort different types of data:
- Long integers
- Words (space-separated strings)
- Lines of text

The tool supports two sorting methods:
- Natural order (numeric for numbers, lexicographic for text)
- Frequency-based sorting (by count of occurrences)

## Command-Line Arguments

### Required Arguments
None - all arguments are optional and have default values.

### Optional Arguments

| Argument | Description | Valid Parameters | Default |
|----------|-------------|------------------|---------|
| `-dataType` | Specifies the type of input data | `long`, `word`, `line` | `word` |
| `-sortingType` | Defines how the data should be sorted | `natural`, `byCount` | `natural` |
| `-inputFile` | Specifies input file path | Any valid file path | Standard input |
| `-outputFile` | Specifies output file path | Any valid file path | Standard output |

## Examples

### Natural Sorting of Numbers

```bash
java SortingTool -dataType long -sortingType natural

> 1 -2 33 4
> 42
> 1 1
Total numbers: 7.
Sorted data: -2 1 1 1 4 33 42
```

### Sorting Words by Count

```bash
java SortingTool -dataType word -sortingType byCount

> apple banana apple cherry banana apple
Total words: 6.
cherry: 1 time(s), 17%
banana: 2 time(s), 33%
apple: 3 time(s), 50%
```

### Reading from File and Writing to File

```bash
java SortingTool -sortingType byCount -inputFile input.txt -outputFile output.txt
```

## Error Handling

The tool provides clear error messages for various scenarios:

- Missing sorting type: `No sorting type defined!`
- Missing data type: `No data type defined!`
- Invalid parameters: `"-abc" is not a valid parameter. It will be skipped.`
- Invalid number format: `"abc" is not a long. It will be skipped.`
- File access errors: `Error reading/writing file '{filename}': {error message}`

## Technical Details

- Written in Kotlin
- Uses command-line argument processing
- Supports file I/O operations
- Handles various data types and sorting strategies
- Provides detailed error handling and user feedback
- Calculates and displays statistics about the processed data

## Input Format

- For numbers: Space-separated integers
- For words: Space-separated strings
- For lines: Each line is treated as a single element
- Input can be provided through standard input or a file
- End input with EOF (Ctrl+D on Unix/Mac, Ctrl+Z on Windows)
