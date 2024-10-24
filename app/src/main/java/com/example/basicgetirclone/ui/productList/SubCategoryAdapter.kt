package com.example.basicgetirclone.ui.productList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.SubcategoryViewBinding

class SubCategoryAdapter(var mContext:Context,var list:List<SubCategory>)
    : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryDesignKeeper>()  {
        inner  class SubCategoryDesignKeeper(design:SubcategoryViewBinding) : RecyclerView.ViewHolder(design.root){
            var design:SubcategoryViewBinding
            init {
                this.design = design
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = SubcategoryViewBinding.inflate(layoutInflater,parent,false)
        return  SubCategoryDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: SubCategoryDesignKeeper, position: Int) {
        val subCategory = list.get(position)

        holder.design.subCategoryText.text = subCategory.name
    }
}