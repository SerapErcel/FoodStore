package com.serapercel.foodstore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serapercel.foodstore.data.entity.Food
import com.serapercel.foodstore.databinding.HomeCardBinding
import com.serapercel.foodstore.ui.fragment.HomeFragmentDirections

class FoodAdapter (var mContext: Context, var foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.HomeCardViewHolder>(){
    inner class HomeCardViewHolder(binding: HomeCardBinding) : RecyclerView.ViewHolder(binding.root){
        var binding:HomeCardBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        val binding: HomeCardBinding = HomeCardBinding.inflate(LayoutInflater.from(mContext), parent,false)
        return HomeCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCardViewHolder, position: Int) {
        val food = foodList[position]
        val binding = holder.binding

        binding.tvCardFoodName.text = food.yemek_adi
        binding.tvCardFoodPrice.text = food.yemek_fiyat

        binding.homeFoodCard.setOnClickListener {
            val transfer = HomeFragmentDirections.goToDetail(food = food)
            Navigation.findNavController(it).navigate(transfer)
        }
    }

    override fun getItemCount(): Int = foodList.size
}