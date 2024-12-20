package com.example.basicgetirclone.ui.productList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.basicgetirclone.R
import com.example.basicgetirclone.databinding.FragmentProductListBinding
import com.example.basicgetirclone.ui.productList.adapters.CategoryAdapter
import com.example.basicgetirclone.ui.productList.adapters.ProductAdapter
import com.example.basicgetirclone.ui.productList.adapters.SubCategoryAdapter

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private  lateinit var  design : FragmentProductListBinding
    private  lateinit var viewModel: ProductListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.onCreate()

        viewModel.categories.observe(viewLifecycleOwner){
            val categoryAdapter = CategoryAdapter(requireContext(),it,viewModel)
            design.categoryAdapter = categoryAdapter
        }

        viewModel.subCategory.observe(viewLifecycleOwner){
            val subCategoryAdapter = SubCategoryAdapter(requireContext(),it,viewModel)
            design.subCategoryAdapter = subCategoryAdapter


        }

        viewModel.products.observe(viewLifecycleOwner){
            val productAdapter = ProductAdapter(requireContext(),viewModel)
            design.productAdapter = productAdapter
        }

        viewModel.total.observe(viewLifecycleOwner){
            design.totalAmount = it
        }

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_product_list,container,false)

        design.productListFragment = this
        design.toolbarTitle = getString(R.string.productListTitle)
        design.categoryRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        design.subCategoryRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        design.productRv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        design.constraintcart.setOnClickListener {
            val nav = ProductListFragmentDirections.toCart()
            Navigation.findNavController(it).navigate(nav)
        }


        return  design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : ProductListViewModel by viewModels()
        viewModel = tempViewModel

    }
}