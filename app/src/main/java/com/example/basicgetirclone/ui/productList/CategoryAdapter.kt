package com.example.basicgetirclone.ui.productList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        val design = CategoryViewBinding.inflate(layoutInflater,parent,false)
        return  CategoryDesingKeeper(design)

    }

    override fun getItemCount(): Int {
        return  list.count()
    }

    override fun onBindViewHolder(holder: CategoryDesingKeeper, position: Int) {
        val category = list.get(position)
        holder.design.categoryTxt.text = category.name
    }
}