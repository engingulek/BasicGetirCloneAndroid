package com.example.basicgetirclone.ui.productList

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.R
import com.example.basicgetirclone.databinding.CategoryViewBinding

class CategoryAdapter(var mContext: Context,var list:List<Category>,var viewModel:ProductListViewModel)
    : RecyclerView.Adapter<CategoryAdapter.CategoryDesingKeeper>()
{
    inner class CategoryDesingKeeper(design: CategoryViewBinding)
        : RecyclerView.ViewHolder(design.root) {
            var design:CategoryViewBinding
            init {
                this.design = design
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesingKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design:CategoryViewBinding = DataBindingUtil.inflate(layoutInflater,R.layout.category_view,parent,false)
        return  CategoryDesingKeeper(design)

    }

    override fun getItemCount(): Int {
        return  viewModel.getItemCountCategoryAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoryDesingKeeper, position: Int) {
        val  item = viewModel.onBindViewHolderCategoryAdapter(position)
        holder.design.categoryTitle = item.first.name
        holder.design.selectedCategoryState = item.second
        holder.design.categoryCard.setOnClickListener{
            viewModel.onClickCategory(item.first.id)
            notifyDataSetChanged()
        }


    }
}