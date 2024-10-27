package com.example.basicgetirclone.ui.productList.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.ProductRowDesignBinding
import com.example.basicgetirclone.R
import com.example.basicgetirclone.ui.productList.ProductListFragmentDirections
import com.example.basicgetirclone.ui.productList.ProductListViewModel
import com.example.basicgetirclone.utils.Utils
import com.squareup.picasso.Picasso
import okhttp3.internal.Util

class ProductAdapter(var mContext:Context,var viewModel: ProductListViewModel)
    : RecyclerView.Adapter<ProductAdapter.ProductDesignKeeper>() {
        inner class ProductDesignKeeper(design:ProductRowDesignBinding)
            :RecyclerView.ViewHolder(design.root){
                var design:ProductRowDesignBinding
                init {
                    this.design = design
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design:ProductRowDesignBinding = DataBindingUtil.inflate(layoutInflater,R.layout.product_row_design,parent,false)
        return ProductDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return  viewModel.getItemCountProductAdapter()
    }

    override fun onBindViewHolder(holder: ProductDesignKeeper, position: Int) {
        val product = viewModel.onBindViewHolderProductAdapter(position)
        holder.design.name.text = product.name
        holder.design.price.text = "${product.price}"
        holder.design.desc.text = product.aboutProduct

        Utils.covertToPicasso(product.imageURL,holder.design.imageView)


        holder.design.constraint.setOnClickListener {
            val nav = ProductListFragmentDirections.toDetailFragment(product)
            Navigation.findNavController(it).navigate(nav)
        }
        
    }
}