package com.am.sgmobiledata.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.databinding.DetailsActivityScreenBinding
import com.am.sgmobiledata.utils.INTENT_YEARS_BUNDLE
import com.am.sgmobiledata.utils.INTENT_YEAR_ID
import com.am.sgmobiledata.utils.INTENT_YEAR_NAME
import com.am.sgmobiledata.utils.INTENT_YEAR_POS
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityScreenBinding
    private var actionBar: ActionBar? = null
    private lateinit var yearList: MutableList<EntityYear>
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter
    private var isFirstTime = true
    private var yearPos: Int = 0
    private var numPages = 1
    private var lastPos: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        binding.pager.adapter = pagerAdapter
        binding.pager.setCurrentItem(yearPos)
        lastPos = yearPos
        binding.pager.offscreenPageLimit = 1
    }

    private fun initialize() {
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.extras != null) {
            if (intent.hasExtra(INTENT_YEARS_BUNDLE)) {
                yearList =
                    intent.getParcelableArrayListExtra<EntityYear>(INTENT_YEARS_BUNDLE) as ArrayList<EntityYear>
                if (!yearList.isNullOrEmpty()) {
                    numPages = yearList.size
                }
            }
            if (intent.hasExtra(INTENT_YEAR_POS))
                yearPos = intent.getIntExtra(INTENT_YEAR_POS, 0)

        }

        binding.pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                isFirstTime = false
            }

            override fun onPageSelected(position: Int) {
                if (!isFirstTime) {
                    when {
                        (yearPos < yearList.size - 1) && lastPos < position -> {
                            yearPos = yearPos.plus(1)
                        }
                        (yearPos > -1) && lastPos > position -> {
                            yearPos = yearPos.minus(1)
                        }
                    }
                    lastPos = position
                }
                pagerAdapter.notifyDataSetChanged()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    fun setTitle(title: String) {
        actionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = numPages

        override fun getItem(position: Int): Fragment {
            val bundle = Bundle().apply {
                putInt(INTENT_YEAR_ID, yearList[yearPos]._yearId)
                putString(INTENT_YEAR_NAME, yearList[yearPos].yearName)
            }
            return DetailsFragment().apply { arguments = bundle }
        }

        override fun getItemPosition(`object`: Any): Int {
            return POSITION_NONE
        }
    }
}