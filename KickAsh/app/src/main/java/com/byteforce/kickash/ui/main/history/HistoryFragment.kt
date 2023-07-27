package com.byteforce.kickash.ui.main.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentHistoryBinding
import com.byteforce.kickash.databinding.FragmentSocialOnboardBinding

class HistoryFragment : Fragment(R.layout.fragment_history) {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }


}
