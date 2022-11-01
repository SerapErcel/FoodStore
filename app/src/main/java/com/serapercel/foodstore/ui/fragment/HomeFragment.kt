package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.serapercel.foodstore.R
import com.serapercel.foodstore.databinding.FragmentHomeBinding
import com.serapercel.foodstore.ui.adapter.FoodAdapter
import com.serapercel.foodstore.ui.viewmodel.HomeViewModel
import com.serapercel.foodstore.userSerap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.homeFragment= this
        binding.toolbarHomeTitle = "Foods"

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)

        viewModel.foodList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(requireContext(), it, userSerap )
            binding.rvHome.adapter= adapter
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_search_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.action_cart -> {
                        val transfer = HomeFragmentDirections.goToCart( user = userSerap)
                        Navigation.findNavController(binding.toolbarHome).navigate(transfer)
                        Toast.makeText(requireContext(), "click on cartFragment", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_search -> {
                        Toast.makeText(requireContext(), "click on search", Toast.LENGTH_SHORT).show()
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
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.searchFood(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.searchFood(newText)
        return true
    }

}