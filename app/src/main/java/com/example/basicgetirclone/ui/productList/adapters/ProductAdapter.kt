package com.example.basicgetirclone.ui.productList.adapters

import android.annotation.SuppressLint
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ProductDesignKeeper, position: Int) {
        holder.design.visibilityStatus = false
        val item = viewModel.onBindViewHolderProductAdapter(position)
        holder.design.product = item.first
        holder.design.visibilityStatus = item.second
        Utils.covertToPicasso(item.first.imageURL,holder.design.imageView)
        holder.design.addTxv.setOnClickListener {
            val productId = item.first.id
            val userId = 1
            viewModel.onClickAdd(productId,userId)
            notifyDataSetChanged()
        }

        holder.design.decreaseTxv.setOnClickListener {
            viewModel.decreaseProduct(item.first.id)
            notifyDataSetChanged()
        }


        holder.design.constraint.setOnClickListener {
            val nav = ProductListFragmentDirections.toDetailFragment(item.first)
            Navigation.findNavController(it).navigate(nav)
            notifyDataSetChanged()
        }
        
    }
}