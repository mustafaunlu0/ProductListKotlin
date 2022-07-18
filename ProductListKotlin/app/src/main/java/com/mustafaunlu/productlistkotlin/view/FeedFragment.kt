package com.mustafaunlu.productlistkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaunlu.productlistkotlin.R
import com.mustafaunlu.productlistkotlin.adapter.ProductAdapter
import com.mustafaunlu.productlistkotlin.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private val viewModel : FeedViewModel by viewModels()
    private var productAdapter = ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=productAdapter


        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility=View.GONE
            errorTextView.visibility=View.GONE
            productLoading.visibility=View.VISIBLE

            viewModel.refreshFromAPI()

            //Yukarıdan gelen kendi refresh sembolu yerine progress barı kullanacağız
            swipeRefreshLayout.isRefreshing=false

        }

        observeLiveData()


    }

    private fun observeLiveData() {

        viewModel.products.observe(viewLifecycleOwner, Observer {  products->
            products?.let {

                recyclerView.visibility=View.VISIBLE
                val array=ArrayList(products)
                productAdapter.updateProductList(array)
            }

        })

        viewModel.productError.observe(viewLifecycleOwner, Observer {  error->

            error?.let{
                if(error){
                    errorTextView.visibility=View.VISIBLE
                }else{
                    errorTextView.visibility=View.GONE
                }

            }

        })

        viewModel.productLoading.observe(viewLifecycleOwner, Observer {  loading->
            loading?.let {
                if(it){
                    productLoading.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                    errorTextView.visibility=View.GONE
                }
                else{
                    productLoading.visibility=View.GONE
                }
            }


        })



    }


}

