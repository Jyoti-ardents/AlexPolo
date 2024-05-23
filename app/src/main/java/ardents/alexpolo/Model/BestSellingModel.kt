package ardents.alexpolo.Model

data class BestSellingModel(
	val offset: Int? = null,
	val totalSize: Int? = null,
	val limit: Int? = null,
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(
	val metaImage: String? = null,
	//val featured: Any? = null,
	val freeShipping: Int? = null,
	//val rating: List<Any?>? = null,
	val discount: Int? = null,
	val variantProduct: Int? = null,
	//val variation: List<Any?>? = null,
	val isShippingCostUpdated: Any? = null,
	//val reviews: List<Any?>? = null,
	//val translations: List<Any?>? = null,
	//val flashDeal: Any? = null,
	val details: String? = null,
	val id: Int? = null,
	val categoryIds: List<CategoryIdsItem?>? = null,
	val slug: String? = null,
	val images: List<String?>? = null,
	val thumbnail: String? = null,
	//val metaTitle: Any? = null,
	val minQty: Int? = null,
	val tax: Int? = null,
	val published: Int? = null,
	val brandId: Int? = null,
	val metaDescription: Any? = null,
	val unit: String? = null,
	val userId: Int? = null,
	val taxType: String? = null,
	val name: String? = null,
	val purchase_price: Double? = null,
	val status: Int? = null,
	val reviewsCount: Int? = null,
	val requestStatus: Int? = null,
	val code: String? = null,
	val multiplyQty: Int? = null,
	val createdAt: String? = null,
	val videoProvider: String? = null,
	//val colors: List<Any?>? = null,
	val choiceOptions: List<Any?>? = null,
	//val videoUrl: Any? = null,
//	val attachment: Any? = null,
	val updatedAt: String? = null,
	val addedBy: String? = null,
	val refundable: Int? = null,
	val featuredStatus: Int? = null,
	val minimumOrderQty: Int? = null,
	val shippingCost: Int? = null,
	val unit_price: Double? = null,
	val discountType: String? = null,
	//val deniedNote: Any? = null,
	//val tempShippingCost: Any? = null,
	val currentStock: Int? = null,
	//val attributes: List<Any?>? = null
)

data class ChoiceOptionsItem(
	val name: String? = null,
	val options: List<String?>? = null,
	val title: String? = null
)

data class ColorsItem(
	val code: String? = null,
	val name: String? = null
)

data class VariationItem(
	val price: Any? = null,
	val qty: Int? = null,
	val type: String? = null,
	val sku: String? = null
)

data class CategoryIdsItem(
	val id: String? = null,
	val position: Int? = null
)

