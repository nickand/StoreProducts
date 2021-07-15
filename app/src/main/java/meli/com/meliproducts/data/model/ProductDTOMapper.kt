package meli.com.meliproducts.data.model

import android.text.TextUtils
import meli.com.meliproducts.util.ModelMapper

/**
 * Transform @see ProductDTO to @see Product and the other way around.
 */
class ProductDTOMapper : ModelMapper<ProductDTO, Product> {

    override fun mapToModel(model: ProductDTO): Product {
        return Product(
            id = model.id,
            title = model.title,
            currency = model.currency,
            permalink = model.permalink,
            price = model.price,
            thumbnail = model.thumbnail,
            pictures = if (model.pictures != null) {
                model.pictures.map { it.url }
            } else {
                listOf()
            },
            lastUpdated = model.lastUpdated,
            attributes = model.attributes?.filter { !TextUtils.isEmpty(it.value) }
                ?.map { Attribute(it.name, it.value) }
        )
    }

    override fun mapFromModel(domainModel: Product): ProductDTO {
        return ProductDTO(
            id = domainModel.id,
            title = domainModel.title,
            currency = domainModel.currency,
            permalink = domainModel.permalink,
            price = domainModel.price,
            attributes = domainModel.attributes?.map { AttributeDTO(it.name, it.value) },
            lastUpdated = domainModel.lastUpdated,
            pictures = domainModel.pictures.map { PictureDTO(it) },
            thumbnail = domainModel.thumbnail
        )
    }
}
