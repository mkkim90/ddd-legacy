package kitchenpos.application

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kitchenpos.domain.MenuRepository
import kitchenpos.domain.ProductRepository
import kitchenpos.infra.PurgomalumClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import random.randomProduct
import random.randomProductId
import java.math.BigDecimal
import java.util.*

class ProductServiceTest {
    private val productRepository: ProductRepository = mockk()
    private val menuRepository: MenuRepository = mockk()
    private val purgomalumClient: PurgomalumClient = mockk()
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productService = ProductService(productRepository, menuRepository, purgomalumClient)
    }

    @Nested
    inner class `상품 생성시` {
        @Test
        fun `가격이 널인 경우 예외 발생`() {
            // given
            val product = randomProduct(price = null)
            // when then
            assertThrows<IllegalArgumentException> {
                productService.create(product)
            }
        }

        @Test
        fun `가격이 음수인 경우 예외 발생`() {
            // given
            val product = randomProduct(price = BigDecimal.valueOf(-1))
            // when then
            assertThrows<IllegalArgumentException> {
                productService.create(product)
            }
        }

        @Test
        fun `상품명이 널인 경우 예외 발생`() {
            // given
            val product = randomProduct(name = null)
            // when then
            assertThrows<IllegalArgumentException> {
                productService.create(product)
            }
        }

        @Test
        fun `상품명이 비속어가 포함될 경우 예외 발생`() {
            // given
            val product = randomProduct()
            every { purgomalumClient.containsProfanity(product.name) } returns true

            // when then
            assertThrows<IllegalArgumentException> {
                productService.create(product)
            }
        }

        @Test
        fun `정상 생성`() {
            // given
            val product = randomProduct()
            every { purgomalumClient.containsProfanity(product.name) } returns false
            every { productRepository.save(any()) } returns product
            // when
            val expected = productService.create(product)

            // then
            expected shouldBe product
        }
    }

    @Nested
    inner class `상품 가격 변경시` {
        @Test
        fun `변경 요청 가격이 널인 경우 예외 발생`() {
            // given
            val product = randomProduct(price = null)
            val productId = randomProductId()

            // when then
            assertThrows<IllegalArgumentException> {
                productService.changePrice(productId, product)
            }
        }

        @Test
        fun `변경 요청 가격이 음수인 경우 예외 발생`() {
            // given
            val product = randomProduct(price = BigDecimal.valueOf(-1))
            val productId = randomProductId()

            // when then
            assertThrows<IllegalArgumentException> {
                productService.changePrice(productId, product)
            }
        }

        @Test
        fun `존재하지 않는 상품인 경우 예외 발생`() {
            // given
            val product = randomProduct()
            val productId = randomProductId()
            every { productRepository.findById(productId) } returns Optional.empty()
            // when then
            assertThrows<NoSuchElementException> {
                productService.changePrice(productId, product)
            }
        }
    }
}
