package ardents.alexpolo.Model

data class ProductModel(
	val productModel: List<ProductModelItem?>? = null
)

data class ProductModelItem(
	val discount: Int? = null,
	val details: String? = null,
	val id: Int? = null,
	//val categoryIds: List<CategoryIdsItem?>? = null,
	val slug: String? = null,
	val images: List<String?>? = null,
	val thumbnail: String? = null,
	val minQty: Int? = null,
	val unit: String? = null,
	val userId: Int? = null,
	val taxType: String? = null,
	val name: String? = null,
	val purchase_price: Double? = null,
	val status: Int? = null,
	val colors: List<Any?>? = null,
	val shippingCost: Int? = null,
	val unit_price: Double? = null

)

//data class CategoryIdsItem(
//	val id: String? = null,
//	val position: Int? = null
//)

