package com.byteforce.kickash.ui.main.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentHistoryBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.HistoryRV;

        recyclerView.layoutManager = LinearLayoutManager(context);

        val historyData = ArrayList<HistoryData>()

        historyData.add(HistoryData("13/10/1997",5))
        historyData.add(HistoryData("12/10/1997",6))
        historyData.add(HistoryData("11/10/1997",2))
        historyData.add(HistoryData("10/10/1997",4))
        historyData.add(HistoryData("9/10/1997",4))
        historyData.add(HistoryData("8/10/1997",7))
        historyData.add(HistoryData("7/10/1997",8))
        historyData.add(HistoryData("6/10/1997",2))
        historyData.add(HistoryData("7/10/1997",4))
        historyData.add(HistoryData("4/10/1997",5))
        historyData.add(HistoryData("3/10/1997",3))
        historyData.add(HistoryData("2/10/1997",10))
        historyData.add(HistoryData("1/10/1997",12))
        historyData.add(HistoryData("30/9/1997",4))
        historyData.add(HistoryData("29/9/1997",10))

        val adapter = HistoryAdapter(historyData)
        recyclerView.adapter = adapter

    }

}
