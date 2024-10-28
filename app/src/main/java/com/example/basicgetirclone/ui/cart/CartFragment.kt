package com.example.basicgetirclone.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicgetirclone.R
import com.example.basicgetirclone.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var design: FragmentCartBinding
    private lateinit var viewModel:CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.onCreate()

        viewModel.cartProducts.observe(viewLifecycleOwner){
            val adapter = CartAdapter(requireContext(),viewModel)
            design.cartAdapter = adapter
        }
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        design.cartToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        design.cartToolbar.setNavigationOnClickListener {findNavController().navigateUp() }
        design.cartFragment = this

        design.cartrcv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : CartViewModel by viewModels()
        viewModel = tempViewModel
    }
}