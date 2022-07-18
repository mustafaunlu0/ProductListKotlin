package com.mustafaunlu.productlistkotlin.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafaunlu.productlistkotlin.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    suspend fun getAllProducts() : List<Product>

    @Query("SELECT * FROM product WHERE uuid =:id ")
    suspend fun getProduct(id : Int) : Product

    @Query("DELETE FROM  product")
    suspend fun deleteALlProducts()

    @Insert
    suspend fun insertAllCountries(vararg products : Product) : List<Long>

}