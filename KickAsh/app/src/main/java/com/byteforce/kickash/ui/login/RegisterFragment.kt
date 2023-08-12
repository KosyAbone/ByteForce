package com.byteforce.kickash.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.data.api.KickAshApi
import com.byteforce.kickash.data.api.RegistrationData
import com.byteforce.kickash.data.api.RegistrationResponse
import com.byteforce.kickash.ui.questionair.Questionair1Activity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<MaterialButton>(R.id.btnDone)
        val username = view.findViewById<TextInputEditText>(R.id.tietUserName)
        val password = view.findViewById<TextInputEditText>(R.id.tietPassword)
        val email = view.findViewById<TextInputEditText>(R.id.tietEmail)


        view.findViewById<MaterialButton>(R.id.btnBack).setOnClickListener {
            requireActivity().onBackPressed()
        }

        button.setOnClickListener {


            if (username.text.toString() == "test" && password.text.toString() == "test") {

                Toast.makeText(activity, "Register successful", Toast.LENGTH_SHORT).show()

                requireActivity().onBackPressed()


            } else {


                registerUser(
                    RegistrationData(
                        firstName = "",
                        lastName = "",
                        password = password.text.toString(),
                        username = username.text.toString(),
                        email = email.text.toString()
                    )
                )


            }

        }

    }


    private fun registerUser(registrationData: RegistrationData) {
        val call = KickAshApi.retrofitService.register(registrationData)
        call.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {
                if (response.isSuccessful) {
                    val registrationResponse = response.body()
                    registrationResponse?.let {
                        Log.d("RegisterActivity", "Success: ${it.message}")
                        Toast.makeText(activity, "Register successful", Toast.LENGTH_SHORT).show()

                        requireActivity().onBackPressed()

                    } ?: run {
                        Log.e("RegisterActivity", "Invalid response format.")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(
                        requireActivity(),
                        "Invalid username password",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("RegisterActivity", "Error: $errorBody")
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Log.e("RegisterActivity", "Network error: ${t.message}")
            }
        })
    }

}
