package com.example.basicgetirclone.ui.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.ProductRowDesignBinding
import com.example.basicgetirclone.R
import com.squareup.picasso.Picasso

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
        return  viewModel.products.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ProductDesignKeeper, position: Int) {
        val product = viewModel.products.value?.get(position)
        holder.design.name.text = product?.name
        holder.design.price.text = "${product?.price}"
        holder.design.desc.text = product?.aboutProduct

        Picasso.get()
            .load(product?.imageURL ?: "")
            .placeholder(R.drawable.selected_subcategory)
            .error(R.drawable.ic_launcher_background)
            .into(holder.design.imageView)


    }
}