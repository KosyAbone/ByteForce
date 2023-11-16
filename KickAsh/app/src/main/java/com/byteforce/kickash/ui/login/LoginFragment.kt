package com.byteforce.kickash.ui.login

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
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.data.db.FbUserData
import com.byteforce.kickash.databinding.FragmentLoginBinding
import com.byteforce.kickash.ui.main.SlideShowActivity
import com.byteforce.kickash.ui.questionair.Questionnaire1Activity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {

    // googly thingy

    private lateinit var binding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        initPrivacyPolicy()

        initGoogleLogin()

//        binding.btnFacebookLogin.setOnClickListener {
//            openSlideShow()
//        }

        binding.btnGoogleLogin.setOnClickListener {
            signIn()
        }

        binding.btnUserName.setOnClickListener {
            (requireActivity() as LoginActivity).goToUsernameLogin()
        }

    }

    fun openSlideShow() {

        val i = Intent(requireContext(), SlideShowActivity::class.java)
        requireActivity().startActivity(i)

    }

    lateinit var signInRequest: BeginSignInRequest

    fun initGoogleLogin() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth


    }

    private val RC_SIGN_IN = 9001
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
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


    override fun onStart() {
        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user == null) return


        checkIfitisFirstTime(user)


    }


    fun checkIfitisFirstTime(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()
        val userDocumentRef = db.collection("users").document(user.uid)

        // Check if the user already has data
        userDocumentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val existingFirstName = documentSnapshot.getString("displayName")
                    if (existingFirstName.isNullOrBlank()) {

                        val userData = FbUserData(displayName = user.displayName ?: "Anon")
                        saveFirstTimeUser(user, userData)

                    } else {


                        getUserDataFromFirestore(user.uid) {
                            KickAshApp.globalUserData = it!!

                            Toast.makeText(requireContext(), "Welcome ${user!!.displayName}", Toast.LENGTH_SHORT).show()

                            val i = Intent(requireContext(), MainActivity::class.java)
                            requireActivity().startActivity(i)

                        }



                        // Existing data has a firstName, handle accordingly
                    }
                } else {
                    // No existing data, save new data
                    val userData = FbUserData(displayName = user.displayName ?: "Anon")

                    saveFirstTimeUser(user, userData)
                }
            }
            .addOnFailureListener { e ->
                // Handle the failure
                e.printStackTrace()
                Toast.makeText(requireContext(),"Cannot register. Try again later..",Toast.LENGTH_SHORT).show()

            }
    }

    fun getUserDataFromFirestore(userId: String, onComplete: (FbUserData?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val userDocumentRef = db.collection("users").document(userId)

        userDocumentRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val userData = document.toObject(FbUserData::class.java)
                    onComplete(userData)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener { e ->
                // Handle the failure
                onComplete(null)
            }
    }

    private fun saveFirstTimeUser(user: FirebaseUser, userData: FbUserData) {
        val db = FirebaseFirestore.getInstance()
        val userDocumentRef = db.collection("users").document(user.uid)

        // Create a map to represent the user data
        val userMap = hashMapOf(
            "displayName" to userData.displayName,
            "email" to userData.email,
            "username" to userData.username,
            "gender" to userData.gender,
            "questionnaire" to hashMapOf(
                "start_smoking_date" to userData.questionnaire.startSmokingDate,
                "no_of_cigarette_per_day" to userData.questionnaire.noOfCigarettePerDay,
                "feel_smoking" to userData.questionnaire.feelSmoking,
                "trigger_smoking" to userData.questionnaire.triggerSmoking,
                "stressful_meter" to userData.questionnaire.stressfulMeter,
                "prompt_decision" to userData.questionnaire.promptDecision,
                "hobbies" to userData.questionnaire.hobbies
            ),
            "smokingHistory" to userData.smokingHistory.map {
                hashMapOf(
                    "id" to it.id,
                    "date_time" to it.dateTime,
                    "is_relapsed" to it.isRelapsed
                )
            }
        )

        KickAshApp.globalUserData = userData

        // Save the user data to Firestore
        userDocumentRef.set(userMap)
            .addOnSuccessListener {
                // Data saved successfully

                val i = Intent(requireContext(), Questionnaire1Activity::class.java)
                requireActivity().startActivity(i)

            }
            .addOnFailureListener { e ->

                e.printStackTrace()
                Toast.makeText(requireContext(),"Cannot register. Try again later..",Toast.LENGTH_SHORT).show()
                // Handle the failure
            }
    }


    companion object {

        private val RC_GOOGLE_SIGN_IN = 9001
        private val TAG = "LoginFragment"

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