package com.serapercel.foodstore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serapercel.foodstore.R
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.data.entity.User
import com.serapercel.foodstore.databinding.HomeCardBinding
import com.serapercel.foodstore.ui.fragment.HomeFragmentDirections

class FoodAdapter(var mContext: Context,
                  var foodList: List<Food>,
                  var user: User) :
    RecyclerView.Adapter<FoodAdapter.HomeCardViewHolder>() {
    inner class HomeCardViewHolder(binding: HomeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: HomeCardBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        val binding: HomeCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.home_card, parent, false
        )
        return HomeCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCardViewHolder, position: Int) {
        val food = foodList[position]
        val binding = holder.binding
        binding.homeFood = food

        binding.homeFoodCard.setOnClickListener {
            val transfer = HomeFragmentDirections.goToDetail(food = food, user = user)
            Navigation.findNavController(it).navigate(transfer)
        }
    }

    override fun getItemCount(): Int = foodList.size
}