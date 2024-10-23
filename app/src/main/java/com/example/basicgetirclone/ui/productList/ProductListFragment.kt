package com.example.basicgetirclone.ui.productList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.basicgetirclone.R
import com.example.basicgetirclone.databinding.FragmentProductListBinding


class ProductListFragment : Fragment() {
    private  lateinit var  design : FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_product_list,container,false)
        design.productListFragment = this
        design.toolbarTitle = getString(R.string.productListTitle)
        design.categoryRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        design.subCategoryRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val categoryAdapter = CategoryAdapter(requireContext())
        design.categoryAdapter = categoryAdapter
        val subCategoryAdapter = SubCategoryAdapter(requireContext())
        design.subCategoryAdapter = subCategoryAdapter
        return  design.root



    }





}