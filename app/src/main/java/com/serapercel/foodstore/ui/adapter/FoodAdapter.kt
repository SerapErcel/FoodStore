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
import com.serapercel.foodstore.ui.viewmodel.HomeViewModel
import com.serapercel.foodstore.utils.showImage

class FoodAdapter(
    var mContext: Context,
    var user: User,
    var viewModel: HomeViewModel

) : RecyclerView.Adapter<FoodAdapter.HomeCardViewHolder>() {

    var foodList = listOf<Food>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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

        binding.ivCardFood.showImage(food.yemek_resim_adi, mContext)

        binding.homeFoodCard.setOnClickListener {
            val transfer = HomeFragmentDirections.goToDetail(food = food, user = user)
            Navigation.findNavController(it).navigate(transfer)
        }
    }

    override fun getItemCount(): Int = foodList.size
}