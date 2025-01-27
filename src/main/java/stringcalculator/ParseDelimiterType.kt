package stringcalculator

enum class ParseDelimiterType : ParseDelimiter {
    CUSTOM_DELIMITER {
        override fun parse(text: String): List<String> {
            return Regex(CUSTOM_DELIMITER_PATTERN).find(text)?.let {
                val (customDelimiter, numbers) = it.destructured
                numbers.split(customDelimiter)
            } ?: listOf(text)
        }
    },
    DEFAULT_DELIMITER {
        override fun parse(text: String): List<String> {
            return text.split(*DEFAULT_DELIMITERS.toCharArray())
        }
    };

    companion object {
        fun parseFrom(text: String): List<String> {
            return when {
                Regex(CUSTOM_DELIMITER_PATTERN).matches(text) -> CUSTOM_DELIMITER.parse(text = text)
                else -> DEFAULT_DELIMITER.parse(text = text)
            }
        }

        private const val CUSTOM_DELIMITER_PATTERN = "//(.)\n(.*)"
        private const val DEFAULT_DELIMITERS = ",:"
    }
}

interface ParseDelimiter {
    fun parse(text: String): List<String>
}
