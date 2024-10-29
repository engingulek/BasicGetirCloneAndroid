package com.example.basicgetirclone.ui.productDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.basicgetirclone.R
import com.example.basicgetirclone.databinding.FragmentProductDetailBinding
import com.example.basicgetirclone.utils.Utils
import com.squareup.picasso.Picasso.LoadedFrom

class ProductDetailFragment : Fragment() {
    private lateinit var design : FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail,container,false)
       val bundle:ProductDetailFragmentArgs by navArgs()

        val product = bundle.Product
        design.product = product
        design.toolbarTitle = getString(R.string.productDetail)
        Utils.covertToPicasso(product.imageURL,design.productImageView)
        design.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        return design.root
    }
}