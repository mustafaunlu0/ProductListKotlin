package com.mustafaunlu.productlistkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mustafaunlu.productlistkotlin.model.Product
import com.mustafaunlu.productlistkotlin.service.ProductDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val productLiveData = MutableLiveData<Product>()


    fun getDataFromRoom(uuid : Int){
        launch {
            val dao=ProductDatabase(getApplication()).productDao()
            val product=dao.getProduct(uuid)
            productLiveData.value=product
        }
    }
}