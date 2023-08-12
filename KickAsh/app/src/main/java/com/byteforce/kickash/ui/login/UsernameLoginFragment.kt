package com.byteforce.kickash.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.data.api.KickAshApi
import com.byteforce.kickash.data.api.LoginData
import com.byteforce.kickash.ui.questionair.Questionair1Activity
import com.facebook.login.Login
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsernameLoginFragment : Fragment(R.layout.fragment_username_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<MaterialButton>(R.id.btnDone)
        val username = view.findViewById<TextInputEditText>(R.id.tietUserName)
        val password = view.findViewById<TextInputEditText>(R.id.tietPassword)

        view.findViewById<MaterialButton>(R.id.btnBack).setOnClickListener {
            requireActivity().onBackPressed()
        }

        view.findViewById<MaterialButton>(R.id.btnRegister).setOnClickListener {
            (requireActivity() as LoginActivity).goToRegister()
        }

        button.setOnClickListener {

            if(username.text.toString() == "test" && password.text.toString() == "test") {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("You want to start questionair ?")
                    .setPositiveButton("Yes") {
                        _,_ ->
                        val i = Intent(requireActivity(), Questionair1Activity::class.java)
                        requireContext().startActivity(i)

                    }.setNegativeButton("No, To Home") { _,_ ->
                        val i = Intent(requireActivity(), MainActivity::class.java)
                        requireContext().startActivity(i)
                    }.show()


            }else {
                Toast.makeText(requireActivity(),"Invalid username password",Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun loginUser(username: String, password: String) {
        val loginData = LoginData(username, password)
        val call = KickAshApi.retrofitService.login(loginData)
        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {
                        val token = it["token"]
                        if (token != null) {
                            // Save the token for future use
                        }
                      //  showChoice()
                    } ?: run {
                        Log.e("LoginActivity", "Invalid response format.")
                    }
                } else {
                    Log.e("LoginActivity", "Login failed.")
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                Log.e("LoginActivity", "Network error: ${t.message}")
            }
        })
    }

    fun showChoice(){

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("You want to start questionair ?")
            .setPositiveButton("Yes") {
                    _,_ ->
                val i = Intent(requireActivity(), Questionair1Activity::class.java)
                requireContext().startActivity(i)

            }.setNegativeButton("No, To Home") { _,_ ->
                val i = Intent(requireActivity(), MainActivity::class.java)
                requireContext().startActivity(i)
            }.show()
    }


}
