package com.byteforce.kickash.ui.main.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.FragmentHomeBinding
import com.byteforce.kickash.ui.questionair.Questionnaire1Activity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initUI()

        return root
    }
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.HistoryText.setOnClickListener {

            findNavController().navigate(R.id.navigation_history)
        }
    }*/


    fun initUI() {

        binding.mcvHistory.setOnClickListener {
            findNavController().navigate(R.id.navigation_history)
        }

        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.navigation_history)
        }

        binding.btnReadArticles.setOnClickListener {
            findNavController().navigate(R.id.articlesFragment)
        }

        binding.mcvArticles.setOnClickListener {
            findNavController().navigate(R.id.articlesFragment)
        }

        binding.smokeCounterButton.setOnClickListener {
            var cigaretteNumber = Integer.parseInt(binding.smokeCounterNumber.text.toString())
            cigaretteNumber += 1

            binding.smokeCounterNumber.text = cigaretteNumber.toString()

            var progressBarNumber = binding.progressBar.progress
            progressBarNumber += 20
            binding.progressBar.progress = progressBarNumber

            if (cigaretteNumber >= 5) {
                binding.goalText.text = "You have smoked more than 5 cigarettes!"
                binding.goalText.setTextColor(Color.RED)

                binding.progressBar.setIndicatorColor(Color.RED)
            }
        }

        try {
            generateHomeData()
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }

    fun generateHomeData() {

        val sharedPrefs = requireActivity().getSharedPreferences(
            "QuestionnaireData", Context.MODE_PRIVATE
        )
//        with(sharedPrefs.edit()){
//            putString(com.byteforce.kickash.ui.questionair.Questionnaire1Activity.QuestionnaireConstants.question1 + username, answer)
//            commit()
//        }

        val username = "test"
        val question1Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question1 + username,
            ""
        )
        val question2Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question2 + username,
            ""
        )
        val question3Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question3 + username,
            ""
        )
        val question4Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question4 + username,
            ""
        )
        val question5Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question5 + username,
            ""
        )
//        val question6Answer = sharedPrefs.getString(Questionnaire1Activity.QuestionnaireConstants.question6+username,"")
        val question7Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question7 + username,
            ""
        )
        val question8Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question8 + username,
            ""
        )
        val question9Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question9 + username,
            ""
        )
        val question10Answer = sharedPrefs.getString(
            Questionnaire1Activity.QuestionnaireConstants.question10 + username,
            ""
        )


//        val answers = mapOf(
//            "question1" to "It's been more than 5 years",
//            "question2" to "Less than 10",
//            "question3" to "Male",
//            "question4" to "Stress free",
//            "question5" to "Stress",
//            "question7" to "Health Concern",
//            "question8" to "Photography",
//            "question9" to "2023-12-31",
//            "question10" to 40.0
//        )

        val answers = mapOf(
            "question1" to question1Answer,
            "question2" to question2Answer,
            "question3" to question3Answer,
            "question4" to question4Answer,
            "question5" to question5Answer,
            "question7" to question7Answer,
            "question8" to question8Answer,
            "question9" to question9Answer,
            "question10" to Integer.parseInt(question10Answer)
        )

        val cigarettesPerDay = when (answers["question2"]) {
            "Less than 10" -> 5.0
            "Less than 20" -> 15.0
            else -> 20.0
        }

       // val daysSmokeFree = daysBetweenDates("2023-08-13", answers["question9"] as String)
        val daysSmokeFree = daysBetweenDates("2023-08-09", "2023-08-13")


        val moneySaved =
            cigarettesPerDay * daysSmokeFree * 7  // Assuming average cost per pack is $7

        val lifeGainedMinutes =
            daysSmokeFree * cigarettesPerDay * 11  // Average lifespan reduction per cigarette is 11 minutes

        val heartRateImprovement = when (answers["question5"]) {
            "Stress" -> "Moderate improvement"
            "Anxiety" -> "Significant improvement"
            else -> "No significant improvement"
        }

        val cigarettesNotSmoked = daysSmokeFree * cigarettesPerDay

        binding.tvMoneySaved.text = ""+lifeGainedMinutes+" minutes" // life take back
        binding.tvMoneySavedOrig.text = "$moneySaved"
        binding.tvMoneySaved2.text = heartRateImprovement
        binding.tvNoOfCigarette.text = "$cigarettesNotSmoked"

    }

    fun daysBetweenDates(startDate: String, endDate:String): Int {

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val start = sdf.parse(startDate)

        val selectedGoal = Calendar.getInstance()
       // val end = sdf.format(selectedGoal.getTime())

        val end = sdf.parse(endDate)
        val diff = end.time - start.time
        return (diff / (24 * 60 * 60 * 1000)).toInt()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}