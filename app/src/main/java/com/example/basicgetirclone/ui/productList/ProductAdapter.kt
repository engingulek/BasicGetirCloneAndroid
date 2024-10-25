package com.example.basicgetirclone.ui.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.ProductRowDesignBinding
import com.example.basicgetirclone.R

class ProductAdapter(var mContext:Context)
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
        return  10
    }

    override fun onBindViewHolder(holder: ProductDesignKeeper, position: Int) {
        holder.design.Name.text = "name"
        holder.design.textView2.text = "test"
    }
}