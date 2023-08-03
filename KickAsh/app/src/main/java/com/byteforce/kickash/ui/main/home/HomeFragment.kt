package com.byteforce.kickash.ui.main.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initUI()

        return root
    }
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.HistoryText.setOnClickListener {

            findNavController().navigate(R.id.navigation_history)
        }
    }*/


    fun initUI() {

        binding.mcvHistory.setOnClickListener {
            findNavController().navigate(R.id.navigation_history)
        }

        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.navigation_history)
        }

        binding.btnReadArticles.setOnClickListener {
            findNavController().navigate(R.id.articlesFragment)
        }

        binding.mcvArticles.setOnClickListener {
            findNavController().navigate(R.id.articlesFragment)
        }

        binding.smokeCounterButton.setOnClickListener {
            var cigaretteNumber = Integer.parseInt(binding.smokeCounterNumber.text.toString())
            cigaretteNumber += 1

            binding.smokeCounterNumber.text = cigaretteNumber.toString()

            var progressBarNumber = binding.progressBar.progress
            progressBarNumber += 20
            binding.progressBar.progress = progressBarNumber

            if(cigaretteNumber >= 5){
                binding.goalText.text = "You have smoked more than 5 cigarettes!"
                binding.goalText.setTextColor(Color.RED)

                binding.progressBar.setIndicatorColor(Color.RED)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}