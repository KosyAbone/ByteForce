package com.byteforce.kickash.ui.main.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentSocialBinding
import com.byteforce.kickash.databinding.FragmentSocialOnboard2Binding
import com.byteforce.kickash.databinding.FragmentSocialOnboard3Binding
import com.byteforce.kickash.databinding.FragmentSocialOnboardBinding

class SocialOnboard3Fragment: Fragment(R.layout.fragment_social_onboard_3) {

    private var _binding: FragmentSocialOnboard3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSocialOnboard3Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnJoinNow.setOnClickListener {

            findNavController().navigate(R.id.action_socialOnboard3Fragment2_to_navigation_social2)
        }
    }
}