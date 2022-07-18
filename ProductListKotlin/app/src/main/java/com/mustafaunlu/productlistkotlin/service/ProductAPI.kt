package com.mustafaunlu.productlistkotlin.service

import com.mustafaunlu.productlistkotlin.model.Product
import io.reactivex.Single
import retrofit2.http.GET

interface ProductAPI {

    //GET , POST


    // https://raw.githubusercontent.com/dotnet-presentations/ContosoCrafts/master/src/wwwroot/data/products.json

    @GET("dotnet-presentations/ContosoCrafts/master/src/wwwroot/data/products.json")
    fun getProducts() : Single<List<Product>>


}