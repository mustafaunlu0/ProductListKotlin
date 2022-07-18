package com.mustafaunlu.productlistkotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mustafaunlu.productlistkotlin.model.Product
import com.mustafaunlu.productlistkotlin.service.ProductAPIService
import com.mustafaunlu.productlistkotlin.service.ProductDatabase
import com.mustafaunlu.productlistkotlin.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val productAPIService = ProductAPIService()
    private val disposable=CompositeDisposable()
    private var customSharedPreferences=CustomSharedPreferences(getApplication())
    private var refreshTime =10*60*1000*1000*1000L


    var products=MutableLiveData<List<Product>>()
    var productError=MutableLiveData<Boolean>()
    var productLoading=MutableLiveData<Boolean>()


    fun refreshData(){
        val updateTime=customSharedPreferences.getTime()

        if(updateTime !=null && updateTime!=0L && System.nanoTime() - updateTime<refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }
    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        productLoading.value=true

        disposable.add(
            productAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith( object : DisposableSingleObserver<List<Product>>(){
                    override fun onSuccess(t: List<Product>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Products from API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        productLoading.value=false
                        productError.value=true
                        e.printStackTrace()
                    }

                })

        )


    }
    private fun showProducts(productList: List<Product>){
        products.value=productList
        productError.value=false
        productLoading.value=false
    }

    private fun storeInSQLite(products: List<Product>) {
        launch {
            val dao=ProductDatabase(getApplication()).productDao()
            dao.deleteALlProducts()
            val listLong=dao.insertAllCountries(*products.toTypedArray())
            var i = 0

            while(i<products.size){
                products[i].uuid=listLong[i].toInt()
                i++
            }
            showProducts(products)
        }
    }

    private fun getDataFromSQLite() {
        productLoading.value=false

        launch{
            val products=ProductDatabase(getApplication()).productDao().getAllProducts()
            showProducts(products)
            Toast.makeText(getApplication(),"Products from SQLite",Toast.LENGTH_LONG).show()
        }

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}