package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.serapercel.foodstore.R
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.databinding.FragmentHomeBinding
import com.serapercel.foodstore.ui.adapter.FoodAdapter
import com.serapercel.foodstore.userSerap

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.id.homeFragment, container, false)

        binding.homeFragment= this
        binding.toolbarHomeTitle = "Foods"

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)

        val foodList = ArrayList<Food>()
        val food = Food("1", "Izgara Somon", "blabla", "35tl")
        val food1 = Food("1", "Izgara Köfte", "blabla", "45tl")
        val food2 = Food("1", "Ayran", "blabla", "55tl")
        foodList.add(food)
        foodList.add(food1)
        foodList.add(food2)

        binding.rvHome.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val adapter = FoodAdapter(requireContext(), foodList, userSerap)
        binding.rvHome.adapter = adapter

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

    override fun onQueryTextSubmit(query: String): Boolean {
        Toast.makeText(requireContext(), "click on onQueryTextSubmit", Toast.LENGTH_SHORT).show()

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Toast.makeText(requireContext(), "click on onQueryTextChange", Toast.LENGTH_SHORT).show()

        return true
    }


}