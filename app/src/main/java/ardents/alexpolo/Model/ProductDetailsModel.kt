package ardents.alexpolo.Model

data class ProductDetailsModel(
	val discount: Int? = null,
	val details: String? = null,
	val id: Int? = null,
	//val categoryIds: List<CategoryIdsItem?>? = null,
	val slug: String? = null,
	val images: List<String?>? = null,
	val minQty: Int? = null,
	val published: Int? = null,
	val purchase_price: Double? = null,
	val reviewsCount: Int? = null,
	val name: String? = null,
	val multiplyQty: Int? = null,
	//val colors: List<String?>? = null,
	val minimumOrderQty: Int? = null,
	val shippingCost: Int? = null,
	val unit_price: Double? = null,
	val currentStock: Int? = null,

)

//data class CategoryIdsItem(
//	val id: String? = null,
//	val position: Int? = null
//)

