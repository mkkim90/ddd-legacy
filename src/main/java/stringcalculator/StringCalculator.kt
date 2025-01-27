package stringcalculator

class StringCalculator {
    fun calculate(text: String?): Int {
        if (text.isNullOrBlank()) return 0
        return StringNumbers.from(parsedNumbers = text.parseDelimiter()).sum()
    }

    private fun String.parseDelimiter(): List<String> {
        val regex = Regex(CUSTOM_DELIMITER_PATTERN)
        val matchResult = regex.find(this)
        return matchResult?.let {
            val (customDelimiter, numbers) = it.destructured
            numbers.split(customDelimiter)
        } ?: this.split(*DEFAULT_DELIMITERS.toCharArray())
    }

    companion object {
        private const val CUSTOM_DELIMITER_PATTERN = "//(.)\n(.*)"
        private const val DEFAULT_DELIMITERS = ",:"
    }
}
