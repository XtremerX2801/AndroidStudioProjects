package com.example.stringapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.stringapp.R
import com.example.stringapp.Activity.RegisterLoginActivity
import kotlinx.android.synthetic.main.intro_view_pager.*

class ViewPagerManager: AppCompatActivity(), View.OnClickListener {
    private val myAdapter = MyViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_view_pager)

        myAdapter.addFragment(IntroFragment1())
        myAdapter.addFragment(IntroFragment2())
        myAdapter.addFragment(IntroFragment3())
        intro_viewPager.adapter = myAdapter

        dotsIndicator.setViewPager(intro_viewPager)
        intro_viewPager.adapter?.registerDataSetObserver(dotsIndicator.dataSetObserver)

        intro_next_button.setOnClickListener(this)
        intro_skip.setOnClickListener(this)
    }

    class MyViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList: MutableList<Fragment> = arrayListOf()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

    }

    override fun onClick(view: View?) {
        if (view != null) {
            if (view.id == R.id.intro_next_button) {
                when (intro_viewPager.currentItem) {
                    0 -> intro_viewPager.setCurrentItem(1, true)
                    1 -> intro_viewPager.setCurrentItem(2, true)
                    else -> goToRegisterActivity()
                }
            }

            if (view.id == R.id.intro_skip) {
                goToRegisterActivity()
            }
        }
    }

    private fun goToRegisterActivity(){
        val intent = Intent(this@ViewPagerManager, RegisterLoginActivity::class.java)
        startActivity(intent)
    }
}