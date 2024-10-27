package com.example.basicgetirclone.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicgetirclone.R
import com.example.basicgetirclone.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var design: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)


        val adapter = CartAdapter(requireContext())
        design.cartAdapter = adapter


        design.cartFragment = this


        design.cartrcv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        return design.root
    }
}