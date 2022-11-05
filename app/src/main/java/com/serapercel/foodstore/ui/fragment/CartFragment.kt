package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.serapercel.foodstore.R
import com.serapercel.foodstore.databinding.FragmentCartBinding
import com.serapercel.foodstore.ui.adapter.CartAdapter
import com.serapercel.foodstore.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,  container, false)

        val bundle: CartFragmentArgs by navArgs()
        val user = bundle.user

        binding.toolbarCartTitle = "Shopping Cart"
        binding.cartFragment = this

        viewModel.cartList.observe(viewLifecycleOwner){
            if (it == null){
                Toast.makeText(requireContext(), "Your Shopping Cart is Empty!", Toast.LENGTH_LONG).show()
            }else {
                val adapter = CartAdapter(requireContext(), it, viewModel)
                binding.rvCart.adapter = adapter
                binding.totalPrice = viewModel.calculatePrice()
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onResume() {
        super.onResume()
        viewModel.getCartFoods()
    }

    fun confirmCartButton(){
        Toast.makeText(requireContext(), "Your order is preparing!", Toast.LENGTH_LONG).show()
    }

}