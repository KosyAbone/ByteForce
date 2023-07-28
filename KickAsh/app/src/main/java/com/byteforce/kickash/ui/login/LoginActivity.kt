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

    fun goToUsernameLogin(){
        binding.vpAuth.setCurrentItem(2,true)
    }
}