package meli.com.meliproducts.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import meli.com.meliproducts.ui.App
import meli.com.meliproducts.data.database.DbConstants
import meli.com.meliproducts.data.database.ProductDAO
import meli.com.meliproducts.data.database.ProductDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductDatabase(app: App): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            DbConstants.DB
        ).build()
    }

    @Provides
    fun provideSearchResultsDAO(appDatabase: ProductDatabase): ProductDAO {
        return appDatabase.productDao()
    }
}