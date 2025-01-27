package stringcalculator

class StringCalculator {
    fun calculate(text: String?): Int {
        if (text.isNullOrBlank()) return 0
        return text.parseDelimiter().sumOf { it ->
            toInt(parsedText = it)
        }
    }

    private fun String.parseDelimiter(): List<String> {
        val regex = Regex(CUSTOM_DELIMITER_PATTERN)
        val matchResult = regex.find(this)
        return matchResult?.let {
            val (customDelimiter, numbers) = it.destructured
            numbers.split(customDelimiter)
        } ?: this.split(*DEFAULT_DELIMITERS.toCharArray())
    }

    private fun toInt(parsedText: String): Int {
        val number = parsedText.toIntOrNull() ?: throw RuntimeException("숫자가 아닌 문자열 $parsedText")
        if (number < 0) throw RuntimeException("음수 허용하지 않습니다 $parsedText")
        return number
    }

    companion object {
        private const val CUSTOM_DELIMITER_PATTERN = "//(.)\n(.*)"
        private const val DEFAULT_DELIMITERS = ",:"
    }
}
