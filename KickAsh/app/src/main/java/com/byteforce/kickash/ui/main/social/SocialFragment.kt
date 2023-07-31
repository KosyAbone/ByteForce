package com.byteforce.kickash.ui.main.social

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byteforce.kickash.databinding.FragmentSocialBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SocialFragment : Fragment() {

    private var isLoading = false // Flag to prevent multiple requests while waiting for a response

    private var _binding: FragmentSocialBinding? = null

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: SocialMessageAdapter;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var socialViewModel: SocialViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        socialViewModel = ViewModelProvider(this).get(SocialViewModel::class.java)

        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.socialMessageRecyclerView


        adapter = SocialMessageAdapter(emptyList<SocialMessage>().toMutableList())

        val sendMessageButton: ImageButton = binding.sendMessageButton
        val messageField: EditText = binding.socialMessageSendField

        sendMessageButton.isEnabled = false

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        (recyclerView.layoutManager as LinearLayoutManager).reverseLayout = true;

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !recyclerView.canScrollVertically(-1)) {
                    // User scrolled to the top
                    socialViewModel.oldMessagePoll(adapter.getCurrentPageNumber()+1)
                }
            }
        })
        messageField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used, but required to implement the interface
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Check if the text is empty and disable the button accordingly
                sendMessageButton.isEnabled = !s.isNullOrBlank()
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used, but required to implement the interface
            }
        })
        socialViewModel.socialMessageList.observe(viewLifecycleOwner) { socialMessages ->
            adapter.updateMessages(socialMessages)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                socialViewModel.newMessagePoll()
                delay(5000) // Wait for 5 seconds before the next call
            }
        }
        socialViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                showViewModelErrorMessage(it)
            }
        }

        sendMessageButton.setOnClickListener {
            sendMessage()
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    private fun showViewModelErrorMessage(errorMessage: String) {
        val rootView = requireView()

        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    private fun sendMessage() {
        binding.sendMessageButton.isEnabled = false
        val messageField = binding.socialMessageSendField
        val message = messageField.text.toString()
        val senderId = "tester"
        lifecycleScope.launch {
            val result: Boolean = socialViewModel.sendMessage(senderId, message)
            //Reset message field (and keep button blocked because empty field) if true
            //Else unblock (since unsuccessful)
            if (result) {
                messageField.setText("")
            }else {
                binding.sendMessageButton.isEnabled = true
            }
        }
    }

}