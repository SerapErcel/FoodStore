package com.serapercel.foodstore.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.serapercel.foodstore.R
import com.serapercel.foodstore.data.entity.CartFood
import com.serapercel.foodstore.databinding.CartCardBinding
import com.serapercel.foodstore.showImage
import com.serapercel.foodstore.ui.viewmodel.CartViewModel

class CartAdapter(
    var mContext: Context,
    var cartList: List<CartFood>,
    var viewModel: CartViewModel
) :
    RecyclerView.Adapter<CartAdapter.CartCardViewHolder>() {
    inner class CartCardViewHolder(binding: CartCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: CartCardBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartCardViewHolder {
        val binding: CartCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.cart_card, parent, false
        )
        return CartCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartCardViewHolder, position: Int) {
        val food = cartList[position]
        val binding = holder.binding
        binding.cartFood = food

        showImage(food.yemek_resim_adi, mContext, binding.ivCartFood)

        binding.deleteCartFood.setOnClickListener {
            Snackbar.make(it, "Do you want remove ${food.yemek_adi}?", Snackbar.LENGTH_LONG)
                .setAction("Yes") {
                    removeFood(food.sepet_yemek_id, food.kullanici_adi)
                }.show()
        }

    }
    // TODO transfer to view model
    fun calculatePrice(): Int{
        var price = 0
        for( f in cartList){
            price += f.yemek_fiyat!! * f.yemek_siparis_adet!!
        }
        return price
    }

    fun removeFood(sepet_yemek_id: Int, kullanici_adi: String) {
        viewModel.removeFood(sepet_yemek_id, kullanici_adi)
    }

    override fun getItemCount(): Int = cartList.size
}