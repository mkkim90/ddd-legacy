package random

import kitchenpos.domain.Product
import java.math.BigDecimal
import java.util.*


fun randomProduct(
    id: UUID = randomProductId(),
    name: String? = randomProductName(),
    price: BigDecimal? = randomPrice(),
): Product {
    val product = Product()
    product.id = id
    product.name = name
    product.price = price
    return product
}

fun randomProductId() = UUID.randomUUID()

fun randomProductName(): String {
    return listOf("후라이드치킨", "양념치킨", "후라이드순살치킨", "양념순살치킨").random()
}
