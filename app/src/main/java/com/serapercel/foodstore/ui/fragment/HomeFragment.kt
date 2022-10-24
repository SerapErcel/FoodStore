package com.serapercel.foodstore.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.serapercel.foodstore.R
import com.serapercel.foodstore.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        binding.toolbarHome.title = "Foods"

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
                        Navigation.findNavController(binding.toolbarHome).navigate(R.id.goToCart)
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