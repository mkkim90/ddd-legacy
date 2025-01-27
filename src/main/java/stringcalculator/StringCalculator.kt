package stringcalculator

class StringCalculator {
    fun calculate(text: String?): Int {
        if (text.isNullOrBlank()) return 0
        return StringNumbers.from(parsedNumbers = ParseDelimiterType.parseFrom(text = text)).sum()
    }
}
