package stringcalculator

class StringCalculator {
    fun calculate(text: String?): Int {
        if (text.isNullOrBlank()) return 0
        return text.parseDelimiter().sumOf {
            val number= it.toIntOrNull() ?: throw RuntimeException("숫자가 아닌 문자열 $it")
            if (number < 0) throw RuntimeException("음수 허용하지 않습니다 $it")
            number
        }
    }

    private fun String.parseDelimiter(): List<String> {
        val regex = Regex("//(.)\n(.*)")
        val matchResult = regex.find(this)
        return matchResult?.let {
            val (customDelimiter, numbers) = it.destructured
            numbers.split(customDelimiter)
        } ?: this.split(",", ":")
    }
}