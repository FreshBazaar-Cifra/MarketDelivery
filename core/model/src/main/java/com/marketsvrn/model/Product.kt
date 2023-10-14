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
    val estimate: Float,
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
                    "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL",
                    "https://avatars.mds.yandex.net/get-altay/1335362/2a00000185f37df673c70d2e2dc23f45a08d/XXXL"
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