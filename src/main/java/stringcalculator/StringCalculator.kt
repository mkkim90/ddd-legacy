package stringcalculator

class StringCalculator {
    fun calculate(text: String?): Int {
        if (text.isNullOrBlank()) return 0
        return text.parseDelimiter().sumOf {
            toInt(it)
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

    private fun toInt(it: String): Int {
        val number = it.toIntOrNull() ?: throw RuntimeException("숫자가 아닌 문자열 $it")
        if (number < 0) throw RuntimeException("음수 허용하지 않습니다 $it")
        return number
    }

    companion object {
        private const val CUSTOM_DELIMITER_PATTERN = "//(.)\n(.*)"
        private const val DEFAULT_DELIMITERS = ";:"
    }
}