package com.byteforce.kickash.ui.main.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byteforce.kickash.databinding.FragmentSocialBinding
import com.byteforce.kickash.ui.main.social.messageapi.MessageModel
import com.google.android.material.snackbar.Snackbar

class SocialFragment : Fragment(), SocialMessageAdapter.SocialMessageRecyclerAdapterListener {

    private var isLoading = false // Flag to prevent multiple requests while waiting for a response

    private var _binding: FragmentSocialBinding? = null

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: SocialMessageAdapter;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val socialViewModel = ViewModelProvider(this).get(SocialViewModel::class.java)

        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.socialMessageRecyclerView


        adapter = SocialMessageAdapter(emptyList<SocialMessage>().toMutableList(), this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        (recyclerView.layoutManager as LinearLayoutManager).reverseLayout = true;

        socialViewModel.socialMessageList.observe(viewLifecycleOwner) {
            adapter.updateMessages(it)
        }
        socialViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                showViewModelErrorMessage(it)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemUpdate() {
        adapter?.notifyDataSetChanged()
    }


    private fun showViewModelErrorMessage(errorMessage: String) {
        val rootView = requireView()

        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

}