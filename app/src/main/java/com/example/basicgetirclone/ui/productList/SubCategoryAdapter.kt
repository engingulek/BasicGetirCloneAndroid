package com.example.basicgetirclone.ui.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicgetirclone.databinding.SubcategoryViewBinding

class SubCategoryAdapter(var mContext:Context)
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
        return 10
    }

    override fun onBindViewHolder(holder: SubCategoryDesignKeeper, position: Int) {
        holder.design.subCategoryText.text = "test1"
    }
}