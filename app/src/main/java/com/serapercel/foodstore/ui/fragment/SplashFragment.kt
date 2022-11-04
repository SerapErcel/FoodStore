package com.serapercel.foodstore.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.serapercel.foodstore.MainActivity
import com.serapercel.foodstore.R


class SplashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.myLooper()!!).postDelayed(
            {
                if (onBoardingFinished()) {
                    if (auth.currentUser != null) {
                        val currentUserEmail = auth.currentUser!!.email.toString()
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        intent.putExtra("user_name", currentUserEmail)
                        requireActivity().startActivity(intent)
                        requireActivity().finish()
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    }
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                }
            }, 3000
        )
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}