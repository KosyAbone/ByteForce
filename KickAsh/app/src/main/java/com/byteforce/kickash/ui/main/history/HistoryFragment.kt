package com.byteforce.kickash.ui.main.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.DatePickerDialog
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentHistoryBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ValueEventListener
import java.io.Console

class HistoryFragment : Fragment(R.layout.fragment_history) {


    private lateinit var frombutton: Button
    private lateinit var tobutton: Button
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    // constructor()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frombutton = binding.FromDateTimePicker;
        tobutton = binding.ToDateTimePicker;
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("historyData")
        val myCalendar = Calendar.getInstance();
        val fromDate = ""
        val toDate = ""

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
            val fromDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA).format(myCalendar.time)
        }
        val datePicker1 = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable1(myCalendar)
            val toDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA).format(myCalendar.time)
        }

        frombutton.setOnClickListener {
            context?.let { it1 -> DatePickerDialog(it1,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show() }
        }

        tobutton.setOnClickListener {
            context?.let { it1 -> DatePickerDialog(it1,datePicker1,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show() }
        }
        val recyclerView = binding.HistoryRV;

        recyclerView.layoutManager = LinearLayoutManager(context);

        Log.d("fromDate","Value:$fromDate")
        Log.d("toDate","Value:$toDate")
        fetchDataFromFirebase(fromDate,toDate)
//        val historyData = ArrayList<HistoryData>()
//
//        historyData.add(HistoryData("13/10/1997",5))
//        historyData.add(HistoryData("12/10/1997",6))
//        historyData.add(HistoryData("11/10/1997",2))
//        historyData.add(HistoryData("10/10/1997",4))
//        historyData.add(HistoryData("9/10/1997",4))
//        historyData.add(HistoryData("8/10/1997",7))
//        historyData.add(HistoryData("7/10/1997",8))
//        historyData.add(HistoryData("6/10/1997",2))
//        historyData.add(HistoryData("7/10/1997",4))
//        historyData.add(HistoryData("4/10/1997",5))
//        historyData.add(HistoryData("3/10/1997",3))
//        historyData.add(HistoryData("2/10/1997",10))
//        historyData.add(HistoryData("1/10/1997",12))
//        historyData.add(HistoryData("30/9/1997",4))
//        historyData.add(HistoryData("29/9/1997",10))
//
//        val adapter = HistoryAdapter(historyData)
//        recyclerView.adapter = adapter

    }

    private fun fetchDataFromFirebase(fromDate: String, toDate: String) {
        val userId = auth.currentUser?.uid
        val historyDataList = ArrayList<HistoryData>()

        // Read data from Firebase
        database.child(userId!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val date = snapshot.child("date").value.toString()
                    val value = snapshot.child("value").getValue(Integer::class.java)

                    // Check if the date is within the specified range
                    if (isDateInRange(date, fromDate, toDate)) {
                        historyDataList.add(HistoryData(date, value))
                    }
                }

                // Update RecyclerView with the fetched data
                updateRecyclerView(historyDataList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
            }
        })
    }

    private fun isDateInRange(date: String, fromDate: String, toDate: String): Boolean {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)
        val currentDate = sdf.parse(date)
        val fromDateObj = sdf.parse(fromDate)
        val toDateObj = sdf.parse(toDate)

        return currentDate in fromDateObj..toDateObj
    }

    private fun updateRecyclerView(historyDataList: ArrayList<HistoryData>) {
        val recyclerView = binding.HistoryRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = HistoryAdapter(historyDataList)
        recyclerView.adapter = adapter
    }
    private fun updateLable1(myCalendar: Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.CANADA)
        tobutton.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.CANADA)
        frombutton.setText(sdf.format(myCalendar.time))
    }


}
