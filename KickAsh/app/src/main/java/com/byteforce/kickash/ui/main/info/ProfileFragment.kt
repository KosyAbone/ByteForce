package com.byteforce.kickash.ui.main.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.byteforce.kickash.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val infoViewModel =
            ViewModelProvider(this).get(InfoViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initUI()

        return root
    }


    fun initUI() {


//        binding.rvArticles.adapter = ArticleAdapter(DataHub.articles) {
//
//        }
//
//        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}