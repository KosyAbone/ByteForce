package com.byteforce.kickash.ui.main.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.byteforce.kickash.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

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

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initUI()

        return root
    }




    fun initUI() {




//        binding.rvArticles.adapter = ArticleAdapter(DataHub.articles) {
//
//        }
//
//        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())

      //  binding.btn

        binding.editButton.setOnClickListener {
            isInEditMode = !isInEditMode
            setEditMode(isInEditMode)
        }

        setMockData()

    }

    fun setMockData() {
        binding.etDob.setText("2000-02-03")
        binding.emailEditText.setText("test@test.com")
        binding.etGender.setText("Male")
        binding.etSmokingAge.setText("8")

        binding.etEstimatedCost.setText("18")
        binding.etDisplayName.setText("Test")
        binding.etNoOfCigarette.setText("10")

    }

    private var isInEditMode = false

    fun setEditMode(editEnabled:Boolean) {

        binding.etDob.isEnabled = editEnabled
        binding.etDisplayName.isEnabled = editEnabled
        binding.etGender.isEnabled = editEnabled
        binding.etEstimatedCost.isEnabled = editEnabled
        binding.etNoOfCigarette.isEnabled = editEnabled
        binding.etSmokingAge.isEnabled = editEnabled
        binding.emailEditText.isEnabled = editEnabled

        binding.llConfirmPassword.isVisible = editEnabled

        binding.btnUpdate.isVisible = editEnabled
        binding.editButton.text = if(editEnabled) "View" else "Edit"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}