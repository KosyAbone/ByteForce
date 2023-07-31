package com.byteforce.kickash.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.ui.questionair.Questionair1Activity
import com.facebook.login.Login
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

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

}
