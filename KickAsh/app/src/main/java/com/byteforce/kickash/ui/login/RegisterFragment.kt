package com.byteforce.kickash.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.ui.questionair.Questionair1Activity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<MaterialButton>(R.id.btnDone)
        val username = view.findViewById<TextInputEditText>(R.id.tietUserName)
        val password = view.findViewById<TextInputEditText>(R.id.tietPassword)


        view.findViewById<MaterialButton>(R.id.btnBack).setOnClickListener {
            requireActivity().onBackPressed()
        }

        button.setOnClickListener {

            if(username.text.toString() == "test" && password.text.toString() == "test") {

                Toast.makeText(activity,"Register successful",Toast.LENGTH_SHORT).show()

                requireActivity().onBackPressed()


            }else {
                Toast.makeText(requireActivity(),"Invalid username password",Toast.LENGTH_SHORT).show()
            }

        }

    }

}
