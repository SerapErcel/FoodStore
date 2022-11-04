package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.serapercel.foodstore.R
import com.serapercel.foodstore.utils.user
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.databinding.FragmentDetailBinding
import com.serapercel.foodstore.ui.viewmodel.DetailViewModel
import com.serapercel.foodstore.utils.showImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.detailFragment = this

        val bundle: DetailFragmentArgs by navArgs()
        val tFood = bundle.food

        binding.detailFood = tFood
        binding.user = user
        binding.count = 1

        binding.imageViewFood.showImage(tFood.yemek_resim_adi, requireContext())

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDetail)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_cart_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_detail_cart -> {
                        val transfer = DetailFragmentDirections.goToCartFromDetail(user = user)
                        Navigation.findNavController(binding.toolbarDetail).navigate(transfer)
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun addCartList(user: User, yemek_siparis_adet: String, food: Food) {
        viewModel.addCartList(user, yemek_siparis_adet.toInt(), food)
    }


    fun updateCount( number: String, char: Char) {
        var count = number.toInt()
        when (char) {
            '-' -> count -= 1
            '+' -> count += 1
            else -> count = 1
        }
        binding.count = count
    }
}