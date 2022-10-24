package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.serapercel.foodstore.R
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.databinding.FragmentCartBinding
import com.serapercel.foodstore.ui.adapter.CartAdapter

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.toolbarCart.title = "Shopping Cart"

        val cartList = ArrayList<CartFood>()
        val cartFood1 = CartFood(1, "Somon", "blabla",10,2,"serap")
        val cartFood2 = CartFood(2, "Köfte", "blabla",20,2,"serap")
        val cartFood3 = CartFood(3, "Balık", "blabla",30,2,"serap")
        cartList.add(cartFood1)
        cartList.add(cartFood2)
        cartList.add(cartFood3)

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CartAdapter(requireContext(), cartList = cartList)
        binding.rvCart.adapter = adapter

        return binding.root
    }

}