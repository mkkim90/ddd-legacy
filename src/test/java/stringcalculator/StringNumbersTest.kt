package stringcalculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StringNumbersTest {

    @Test
    fun `숫자 문자열 추출 및 더하기`() {
        val numbers = listOf("1", "2", "3", "4")
        StringNumbers.from(parsedNumbers = numbers).sum() shouldBe 10
    }

    @Test
    fun `숫자가 아닌 문자열 추출 시 예외 발생`() {
        val negativeNumbersOrTexts = listOf("-1", "가나다", "라", "-4")
        assertThrows<RuntimeException> {
            StringNumbers.from(parsedNumbers = negativeNumbersOrTexts)
        }
    }

}
