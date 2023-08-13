package com.byteforce.kickash.ui.main.articles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byteforce.kickash.data.api.KickAshApi
import com.byteforce.kickash.databinding.FragmentNotificationsBinding
import com.byteforce.kickash.ui.main.info.DataHub
import com.byteforce.kickash.ui.main.info.InfoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

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

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initUI()

        return root
    }


    fun initUI() {


        binding.rvArticles.adapter = ArticleAdapter(DataHub.articles) {
            val i = Intent(requireActivity(),ArticlesDetailActivity::class.java)
            requireActivity().startActivity(i)

        }

        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())

        getArticles()


    }


    fun getArticles() {
        val call = KickAshApi.retrofitService.getArticles()
        call.enqueue(object : Callback<List<ArticleModel>> {
            override fun onResponse(call: Call<List<ArticleModel>>, response: Response<List<ArticleModel>>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {

                    } ?: run {
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<List<ArticleModel>>, t: Throwable) {
                Log.e("LoginActivity", "Network error: ${t.message}")
            }
        })
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}