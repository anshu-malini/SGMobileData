package com.am.sgmobiledata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.data.repository.Repository
import com.am.sgmobiledata.databinding.DetailsFragmentScreenBinding
import com.am.sgmobiledata.databinding.DetailsQuaterListItemBinding
import com.am.sgmobiledata.utils.INTENT_YEARS_ID
import com.am.sgmobiledata.viewmodel.DetailsViewModel
import com.am.sgmobiledata.viewmodel.DetailsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeValue()
        arguments?.getInt(INTENT_YEARS_ID)?.let { viewModel.start(it) }
        setObserver()
    }

    private fun initializeValue() {
        val vmFactory = DetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, vmFactory).get(DetailsViewModel::class.java)
    }

    private fun setObserver() {
        lifecycleScope.launch(Dispatchers.Main) {
            updateUI(viewModel.getYearData())
        }
    }

    private fun updateUI(year: EntityYear) {
        binding.itemText.text = "${year.yearName}"

        val includedView = binding.linLayout

        val quarterList = year.quarter
        if (!quarterList.isNullOrEmpty()) {
            for (quarter in quarterList) {
                if (quarter != null && (quarter.quarterName?.trim()?.length != 0) && (quarter.volumePerQuarter != 0.0)) {
                    val itemBinding: DetailsQuaterListItemBinding =
                        DetailsQuaterListItemBinding.inflate(layoutInflater)

                    itemBinding.q1Name.text = "Quarter: ${quarter.quarterName}"
                    itemBinding.q1Volume.text =
                        "Data: ${String.format("%.4f", quarter.volumePerQuarter)}"

                    includedView.addView(itemBinding.root)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}