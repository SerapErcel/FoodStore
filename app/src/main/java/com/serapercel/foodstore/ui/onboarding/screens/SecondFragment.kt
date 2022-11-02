package com.serapercel.foodstore.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.serapercel.foodstore.R
import com.serapercel.foodstore.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.ContinueButton.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}