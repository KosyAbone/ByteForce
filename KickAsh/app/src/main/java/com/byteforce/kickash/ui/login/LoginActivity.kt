package com.byteforce.kickash.ui.login

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.byteforce.kickash.databinding.ActivityAuthBinding
import com.byteforce.kickash.ui.base.BaseActivity
import com.byteforce.kickash.ui.custom.CustomFragmentAdapter

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CustomFragmentAdapter(supportFragmentManager)
        adapter.addFragment(SplashFragment(), "")
        adapter.addFragment(LoginFragment(), "")
        adapter.addFragment(UsernameLoginFragment(), "")
        adapter.addFragment(RegisterFragment(), "")


        binding.vpAuth.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    binding.vpAuth.enableScrolling(false)
                    //setWhiteWindowBar()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {


            }

        })

        binding.vpAuth.adapter = adapter
    }

    fun goToUsernameLogin() {
        binding.vpAuth.setCurrentItem(2, true)
    }

    fun goToRegister() {
        binding.vpAuth.setCurrentItem(3, true)
    }

    override fun onBackPressed() {
        when (binding.vpAuth.currentItem) {
            3 -> {
                binding.vpAuth.setCurrentItem(2, true)
            }
            2 -> {
                binding.vpAuth.currentItem = 1
            }
            1 -> {
                binding.vpAuth.currentItem = 0
            }
            else -> {
                super.onBackPressed()
            }
        }

    }
}