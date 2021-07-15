package meli.com.meliproducts.ui.details.adapter.model

/**
 * @see ViewType to hold an @see com.jvquiroz.mercadolibreproducts.data.domain.model.Product.Attribute
 */
data class AttributesItemView(
    val name: String,
    val value: String?
): ViewType {
    override fun getViewType(): Int {
        return AdapterConstants.ATTRIBUTES
    }
}