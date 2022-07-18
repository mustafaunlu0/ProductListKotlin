package com.mustafaunlu.productlistkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mustafaunlu.productlistkotlin.R
import com.mustafaunlu.productlistkotlin.databinding.FragmentDetailBinding
import com.mustafaunlu.productlistkotlin.viewmodel.DetailViewModel


class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private var productUuid=0
    private  lateinit var dataBinding :FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            productUuid=DetailFragmentArgs.fromBundle(it).uuid

        }

        viewModel.getDataFromRoom(productUuid)

        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.productLiveData.observe(viewLifecycleOwner, Observer { product ->

            product?.let{
                dataBinding.selectedProduct=product
            }

        })
    }


}