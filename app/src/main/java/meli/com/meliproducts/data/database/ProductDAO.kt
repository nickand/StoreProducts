package meli.com.meliproducts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

/**
 * Interface that define database interactions.
 */
@Dao
interface ProductDAO {
    @Insert(onConflict = REPLACE)
    fun save(vararg user: ProductCache)

    @Update
    fun update(product: ProductCache)

    @Query("DELETE FROM ${DbConstants.TABLE_PRODUCTS}")
    fun deleteAll()

    @Query("SELECT * FROM ${DbConstants.TABLE_PRODUCTS}")
    fun load(): List<ProductCache>

    @Query("SELECT * FROM ${DbConstants.TABLE_PRODUCTS} WHERE id=:id ")
    fun loadResult(id: String): ProductCache
}