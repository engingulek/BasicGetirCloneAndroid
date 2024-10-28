package com.example.basicgetirclone.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.CartViewDesignBinding
import com.example.basicgetirclone.R
import com.example.basicgetirclone.utils.Utils

class CartAdapter(var mContext: Context,private val viewModel:CartViewModel)
    : RecyclerView.Adapter<CartAdapter.CartDesingKeeper>() {
        inner  class CartDesingKeeper(design:CartViewDesignBinding)
            :RecyclerView.ViewHolder(design.root){
                var design:CartViewDesignBinding
                init {
                    this.design = design
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartDesingKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design:CartViewDesignBinding = DataBindingUtil.inflate(layoutInflater,R.layout.cart_view_design,parent,false)
        return  CartDesingKeeper(design)
    }

    override fun getItemCount(): Int {
        return  viewModel.getItemCount()
    }

    override fun onBindViewHolder(holder: CartDesingKeeper, position: Int) {
        val cartProduct = viewModel.onBindViewHolder(position)
        holder.design.cartProduct = cartProduct
        Utils.covertToPicasso(cartProduct.imageURL,holder.design.productImv)
        holder.design.decrease.setOnClickListener {
            viewModel.decreamentOnCLick(cartProduct.id)
            notifyDataSetChanged()
        }

        holder.design.increase.setOnClickListener {
            viewModel.increamentOnClick(cartProduct.id)
            notifyDataSetChanged()
        }

    }
}