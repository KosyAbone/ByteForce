package com.byteforce.kickash.ui.main.reward

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentDashboardBinding
import com.byteforce.kickash.databinding.FragmentRewardsBinding
import com.byteforce.kickash.ui.main.social.SocialViewModel

class RewardFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rewardViewModel =
            ViewModelProvider(this).get(RewardViewModel::class.java)

        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        socialViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val pointsText : TextView = root.findViewById<TextView>(R.id.points)
        pointsText.text = "Your Points : " + rewardsPoints.toString()

        initUI()

        return root
    }

    fun initUI(){
        binding.rewardsRecyclerView.adapter = RewardAdapter(RewardData.rewards, binding.rewardsRecyclerView, binding)
        binding.rewardsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var rewardsPoints : Int = 4000
    }
}