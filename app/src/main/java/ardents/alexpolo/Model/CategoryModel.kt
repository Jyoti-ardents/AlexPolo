package ardents.alexpolo.Model

data class CategoryModel(
	val categoryModel: List<CategoryModelItem?>? = null
)

data class CategoryModelItem(
	val childes: List<ChildesItem?>? = null,
	val updatedAt: String? = null,
	val parentId: Int? = null,
	val translations: List<Any?>? = null,
	val name: String? = null,
	val icon: String? = null,
	val createdAt: String? = null,
	val homeStatus: Int? = null,
	val id: Int? = null,
	val position: Int? = null,
	val priority: Int? = null,
	val slug: String? = null,
	val image_path: String? = null
)

data class ChildesItem(
	val name: String? = null,
	val id: Int? = null,
)

