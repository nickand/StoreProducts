package meli.com.meliproducts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * RoomDatabase
 */
@Database(entities = [ProductCache::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO
}

object DbConstants {
    const val DB = "meliproducts"
    const val TABLE_PRODUCTS = "product"
}