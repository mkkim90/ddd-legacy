package stringcalculator

data class StringNumbers(
    private val numbers: List<StringNumber>
) {
    companion object {
        fun from(parsedNumbers: List<String>): StringNumbers {
            return StringNumbers(numbers = parsedNumbers.map {
                StringNumber.from(parsedText = it)
            })
        }
    }

    fun sum(): Int = numbers.sumOf { it.number }
}

data class StringNumber(
    val number: Int
) {
    companion object {
        fun from(parsedText: String): StringNumber {
            val number = parsedText.toIntOrNull() ?: throw RuntimeException("숫자가 아닌 문자열 $parsedText")
            if (number < 0) throw RuntimeException("음수 허용하지 않습니다 $parsedText")
            return StringNumber(number = number)
        }
    }
}
