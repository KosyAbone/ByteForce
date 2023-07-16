package com.byteforce.kickash.ui.main.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentSocialOnboardBinding

class SocialOnboard1Fragment: Fragment(R.layout.fragment_social_onboard) {

    private var _binding: FragmentSocialOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSocialOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnJoinNow.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_social_to_socialOnboard2Fragment2)
        }
    }
}