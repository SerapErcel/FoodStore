package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
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
import com.serapercel.foodstore.utils.user
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.homeFragment = this
        binding.toolbarHomeTitle = getString(R.string.home)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)

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
                        val transfer = HomeFragmentDirections.goToCart(user = user)
                        Navigation.findNavController(binding.toolbarHome).navigate(transfer)
                        true
                    }
                    R.id.action_search -> {
                        binding.filter.visibility = View.INVISIBLE
                        false
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val popupMenu = PopupMenu(requireContext(), binding.filter)

        popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            return@setOnMenuItemClickListener when (menuItem.itemId) {
                R.id.rising -> {
                    viewModel.foodList.value?.let {
                        adapter.foodList = viewModel.sortFoodRising(it)
                    }
                    true
                }
                R.id.decreasing -> {
                    viewModel.foodList.value?.let {
                        adapter.foodList = viewModel.sortFoodDecRising(it)
                    }
                    true
                }
                else -> false
            }
        }

        binding.filter.setOnClickListener {
            popupMenu.show()
        }

        viewModel.foodList.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter = FoodAdapter(requireContext(), user, viewModel)
                adapter.foodList = it
                adapter.user = user
                binding.rvHome.adapter = adapter
            } else {
                Toast.makeText(requireContext(), getString(R.string.list_not_found), Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeViewModel by viewModels()
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