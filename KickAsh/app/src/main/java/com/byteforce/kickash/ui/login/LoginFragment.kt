package com.byteforce.kickash.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentLoginBinding
import com.byteforce.kickash.ui.main.SlideShowActivity

class LoginFragment : Fragment(R.layout.fragment_login) {

    // googly thingy

    private lateinit var binding: FragmentLoginBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        initPrivacyPolicy()


        binding.btnFacebookLogin.setOnClickListener {
            openSlideShow()
        }

        binding.btnGoogleLogin.setOnClickListener {
            openSlideShow()
        }

        binding.btnUserName.setOnClickListener {
            openSlideShow()
        }

    }
    fun openSlideShow() {

        val i = Intent(requireContext(),SlideShowActivity::class.java)
        requireActivity().startActivity(i)

    }



    private fun initPrivacyPolicy() {


        binding.tvPrivacyPolicy.makeLinks(
            Pair("Terms of Service", View.OnClickListener {
                requireContext().startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://byteforce/terms")
                    )
                )

            }),
            Pair("Privacy Policy", View.OnClickListener {
                requireContext().startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://byte-forece-terms html")
                    )
                )
            })
        )

    }


    companion object {

        private val RC_GOOGLE_SIGN_IN = 9001

    }


    fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        var startIndexOfLink = -1
        for (link in links) {
            val clickableSpan = object : ClickableSpan() {
                override fun updateDrawState(textPaint: TextPaint) {
                    // use this to change the link color
                    textPaint.color = textPaint.linkColor
                    // toggle below value to enable/disable
                    // the underline shown below the clickable text
                    textPaint.isUnderlineText = true
                }

                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable, 0)
                    view.invalidate()
                    link.second.onClick(view)
                }
            }
            startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
//      if(startIndexOfLink == -1) continue // todo if you want to verify your texts contains links text
            spannableString.setSpan(
                clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        this.movementMethod =
            LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
    }
}