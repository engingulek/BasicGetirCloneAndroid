package com.example.basicgetirclone.ui.productList.adapters

import android.annotation.SuppressLint
import android.content.Context
import com.example.basicgetirclone.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.SubcategoryViewBinding
import com.example.basicgetirclone.ui.productList.ProductListViewModel
import com.example.basicgetirclone.ui.productList.models.SubCategory

class SubCategoryAdapter(var mContext:Context, var list:List<SubCategory>, var viewModel: ProductListViewModel)
    : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryDesignKeeper>()  {
        inner  class SubCategoryDesignKeeper(design:SubcategoryViewBinding) : RecyclerView.ViewHolder(design.root){
            var design:SubcategoryViewBinding
            init {
                this.design = design
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design:SubcategoryViewBinding = DataBindingUtil.inflate(layoutInflater,R.layout.subcategory_view,parent,false)
        return  SubCategoryDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return viewModel.getItemCountSubCategoryAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: SubCategoryDesignKeeper, position: Int) {
        val item = viewModel.onBindViewHolderSubCategoryAdapter(position)
        holder.design.subCategory = item.first.name
        holder.design.selectedSubCategoryState = item.second
        holder.design.subCategoryText.setOnClickListener {
            viewModel.onClickSubCategory(item.first.id)
            notifyDataSetChanged()
        }

    }
}