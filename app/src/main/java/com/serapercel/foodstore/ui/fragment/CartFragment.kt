package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.serapercel.foodstore.R
import com.serapercel.foodstore.databinding.FragmentCartBinding
import com.serapercel.foodstore.ui.adapter.CartAdapter
import com.serapercel.foodstore.ui.viewmodel.CartViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,  container, false)

        binding.toolbarCartTitle = "Shopping Cart"
        binding.cartFragment = this

        val bundle: CartFragmentArgs by navArgs()
        val user = bundle.user

        viewModel.cartList.observe(viewLifecycleOwner){
            val adapter = CartAdapter(requireContext(), it , viewModel, user)
            binding.rvCart.adapter= adapter
        }
/*
        val cartList = ArrayList<CartFood>()
        val cartFood1 = CartFood(1, "Somon", "blabla",10,2,"serap")
        val cartFood2 = CartFood(2, "Köfte", "blabla",20,2,"serap")
        val cartFood3 = CartFood(3, "Balık", "blabla",30,2,"serap")
        cartList.add(cartFood1)
        cartList.add(cartFood2)
        cartList.add(cartFood3)*/

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }
    fun confirmCartButton(){
        Log.e("Cart", "confirm")
    }

}