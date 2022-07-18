package com.mustafaunlu.productlistkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.productlistkotlin.R
import com.mustafaunlu.productlistkotlin.databinding.RecyclerRowBinding
import com.mustafaunlu.productlistkotlin.model.Product
import com.mustafaunlu.productlistkotlin.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*

class ProductAdapter(private val productList: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), ProductClickListener {



class ProductViewHolder(val view : RecyclerRowBinding) : RecyclerView.ViewHolder(view.root){

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=DataBindingUtil.inflate<RecyclerRowBinding>(LayoutInflater.from(parent.context), R.layout.recycler_row,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.view.product=productList[position]
        holder.view.listener=this
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList : ArrayList<Product>){

        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()

    }

    override fun onProductClicked(v: View) {
        val uuid=v.uuidTextView.text.toString().toInt()
        val action=FeedFragmentDirections.actionFeedFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }


}