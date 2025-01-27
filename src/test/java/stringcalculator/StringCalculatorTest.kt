package stringcalculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class StringCalculatorTest {

    @DisplayName("쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환")
    @ParameterizedTest
    @CsvSource(
        "'', 0",
        "'1,2', 3",
        "'1,2:3', 6",
        "'//;\n1;2;3', 6"
    )
    fun requirements_1(input: String, expected: Int) {
        StringCalculator().calculate(input) shouldBe expected
    }

    @DisplayName("앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다.")
    @ParameterizedTest
    @CsvSource(
        "'//;\n1;2;3', 6"
    )
    fun requirements_2(input: String, expected: Int) {
        StringCalculator().calculate(input) shouldBe expected
    }

    @DisplayName("문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw 한다.")
    @Test
    fun requirements_3() {
        assertThrows<RuntimeException> {
            StringCalculator().calculate("-1")
        }
    }

    @DisplayName(value = "빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.")
    @ParameterizedTest
    @NullAndEmptySource
    fun hint_1(input: String?) {
        StringCalculator().calculate(input) shouldBe 0
    }

    @DisplayName(value = "숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = ["1"])
    fun hint_2(input: String) {
        StringCalculator().calculate(input) shouldBe input.toInt()
    }

    @DisplayName(value = "숫자 두개를 쉼표(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.")
    @ParameterizedTest
    @CsvSource("'1,2', 3")
    fun hint_3(input: String, expected: Int) {
        StringCalculator().calculate(input) shouldBe expected
    }

    @DisplayName(value = "구분자를 쉼표(,) 이외에 콜론(:)을 사용할 수 있다.")
    @ParameterizedTest
    @CsvSource("'1,2:3', 6")
    fun hint_4(input: String, expected: Int) {
        StringCalculator().calculate(input) shouldBe expected
    }

    @DisplayName(value = "//와 \\n 문자 사이에 커스텀 구분자를 지정할 수 있다.")
    @ParameterizedTest
    @CsvSource("'//;\n1;2;3', 6")
    fun hint_5(input: String, expected: Int) {
        StringCalculator().calculate(input) shouldBe expected
    }

    @DisplayName(value = "문자열 계산기에 음수를 전달하는 경우 RuntimeException 예외 처리를 한다.")
    @Test
    fun hint_6() {
        assertThrows<RuntimeException> {
            StringCalculator().calculate("-1")
        }

    }
}