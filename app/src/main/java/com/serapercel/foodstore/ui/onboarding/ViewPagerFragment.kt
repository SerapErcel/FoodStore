package com.serapercel.foodstore.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.serapercel.foodstore.R
import com.serapercel.foodstore.databinding.FragmentViewPagerBinding
import com.serapercel.foodstore.ui.adapter.ViewPagerAdapter
import com.serapercel.foodstore.ui.onboarding.screens.FirstFragment
import com.serapercel.foodstore.ui.onboarding.screens.SecondFragment
import com.serapercel.foodstore.ui.onboarding.screens.ThirdFragment


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter= adapter

        return binding.root
    }
}