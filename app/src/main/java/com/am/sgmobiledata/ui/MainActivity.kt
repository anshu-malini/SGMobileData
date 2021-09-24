package com.am.sgmobiledata.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.am.sgmobiledata.adapter.HomeAdapter
import com.am.sgmobiledata.data.repository.Repository
import com.am.sgmobiledata.databinding.HomeActivityBinding
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


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
        initialize()
        setListener()
        setObserver()
    }

    private fun initialize() {
        val vmFactory = ViewModelFactory(repository)
        viewModel =
            ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

        binding.recyclerView.adapter = adapter
    }

    private fun setListener() {
        adapter.onItemClick =
            { recordsItem ->
                Toast.makeText(this, "Item clicked", LENGTH_SHORT).show()
                startActivity(Intent(this, DetailsActivity::class.java))
            }
    }

    private fun setObserver() {
        viewModel.response.observe(this, Observer {
            when (it.status) {
                NetworkResult.Status.SUCCESS -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    if (it.data != null) adapter.setItemList(it.data.records!!)
                }
                NetworkResult.Status.ERROR -> {
                    Toast.makeText(this, "Network.Status.ERROR", LENGTH_SHORT).show()
                    binding.swipeRefreshLayout.isRefreshing = false

                }
                NetworkResult.Status.LOADING -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                }
            }
        })
    }
}