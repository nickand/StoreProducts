package meli.com.meliproducts.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a Product stored at the database
 */
@Entity(tableName = DbConstants.TABLE_PRODUCTS)
data class ProductCache(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var title: String,
    var price: Double,
    var currency: String,
    var permalink: String,
    var thumbnail: String,
    var pictures: String,
    val lastUpdated: String?,
    val attributes: String?,
)
