package com.am.sgmobiledata.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.am.sgmobiledata.R
import com.am.sgmobiledata.adapter.HomeAdapter
import com.am.sgmobiledata.data.repository.Repository
import com.am.sgmobiledata.databinding.HomeActivityBinding
import com.am.sgmobiledata.utils.INTENT_YEARS_BUNDLE
import com.am.sgmobiledata.utils.INTENT_YEAR_POS
import com.am.sgmobiledata.utils.NetworkResult
import com.am.sgmobiledata.viewmodel.MainViewModel
import com.am.sgmobiledata.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var repository: Repository

    private val adapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        initialize()
        setListener()

    }

    private fun initialize() {
        val vmFactory = ViewModelFactory(repository)
        viewModel =
            ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

        setObserver()
    }

    private fun setListener() {
        adapter.onItemClick =
            { recordsItem, itemPos ->
                val bundle = Bundle().apply {
                    putInt(INTENT_YEAR_POS, itemPos)
                    putParcelableArrayList(
                        INTENT_YEARS_BUNDLE,
                        ArrayList<Parcelable>(viewModel.response.value?.data?.years!!)
                    )
                }
                startActivity(
                    Intent(this, DetailsActivity::class.java).putExtras(bundle)
                )
            }
    }

    private fun setObserver() {
        viewModel.response.observe(this, Observer {
            when (it.status) {
                NetworkResult.Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    if (it.data != null) adapter.setItemList(it.data.years!!)
                }
                NetworkResult.Status.ERROR -> {
                    Toast.makeText(this, getString(R.string.network_error), LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE

                }
                NetworkResult.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        })
    }
}