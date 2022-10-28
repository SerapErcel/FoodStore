package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.serapercel.foodstore.R
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle:DetailFragmentArgs by navArgs()
        val tFood = bundle.food
        val user = bundle.user
        var count = 1

        binding.tvFoodName.text = tFood.yemek_adi
        binding.tvFoodPrice.text = tFood.yemek_fiyat
        binding.tvFoodCount.text = count.toString()

        binding.toolbarDetail.title = tFood.yemek_adi

        binding.buttonAdd.setOnClickListener {
            addCartList(user, binding.tvFoodCount.text.toString().toInt(), tFood)

        }
        binding.ivAdd.setOnClickListener {
            updateCount(binding.tvFoodCount, count, '+' )

        }
        binding.ivRemove.setOnClickListener {
            updateCount(binding.tvFoodCount, count, '-' )
        }

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDetail)

        requireActivity().addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_cart_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.action_detail_cart -> {
                        Navigation.findNavController(binding.toolbarDetail).navigate(R.id.goToCartFromDetail)
                        Toast.makeText(requireContext(), "click on cartFragment", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }

    fun addCartList(kullanici_ad:String, yemek_siparis_adet: Int, food: Food){
        Log.e("yemek", " sepete yemek ekle $yemek_siparis_adet ${food.yemek_adi} $kullanici_ad")
    }

    fun updateCount(view: TextView, number: Int, char:Char){
        var count = number
        when(char){
            '-' -> count -= 1
            '+' -> count += 1
            else -> count = 1
        }
        view.text = count.toString()
    }
}