package com.marketsvrn.model

data class Product (
    val id: Int,
    val placeId: Int,
    val description: String,
    val images: List<String>,
    val name: String,
    val price: Float,
    val weight: Int,
    val category: ProductCategory,
    val estimate: Float?,
    val attributes: List<Attribute>,
    val manufacturer: String
) {
    companion object {
        fun getStub(): Product {
            return Product(
                id = 0,
                placeId = 0,
                description = "Кюфта — род фрикаделек. Традиционное блюдо стран Ближнего Востока, Балкан, Кавказа, а также Центральной и Южной Азии. ",
                images = listOf(
                    "https://buy.am/media/image/9e/2e/b9/Carrefour_0019_86083_01.jpg",
                    "https://жар-мясо.рф/uploads/catalog/f2f1dc238da11746820f2a79569f590f.jpg"
                ),
                name = "Армянская кюфта",
                price = 1.0f,
                estimate = 5.0f,
                weight = 100,
                attributes = listOf(
                    Attribute.getStub(),
                    Attribute.getStub(),
                    Attribute.getStub(),
                    Attribute.getStub()
                ),
                category = ProductCategory.getStub(),
                manufacturer = "ООО \"АРМЯНСОЮЗ\""
            )
        }
    }
}