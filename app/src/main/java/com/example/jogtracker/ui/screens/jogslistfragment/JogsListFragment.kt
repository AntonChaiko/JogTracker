package com.example.jogtracker.ui.screens.jogslistfragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.jogtracker.R
import com.example.jogtracker.databinding.JogsListFragmentBinding
import com.example.jogtracker.ui.base.BaseFragment
import com.example.jogtracker.ui.screens.jogslistfragment.adapter.JogListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class JogsListFragment : BaseFragment<JogsListFragmentBinding>(),
    DatePickerDialog.OnDateSetListener {

    private val viewModel: JogsListViewModel by viewModel()
    private val adapter by lazy {
        JogListAdapter(
            updateJog = { jogEntity -> viewModel.updateCurrentJog(jogEntity) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getJogsList()

        viewModel.jogsList.observe(viewLifecycleOwner, { jogs ->

            viewModel.fromDate.observe(viewLifecycleOwner, { fromDate ->
                viewModel.toDate.observe(viewLifecycleOwner, { toDate ->
                    jogs.filter {
                        viewModel.dateFormatter(it.date) in fromDate..toDate
                    }.also { adapter.submitList(it) }
                })
                jogs.filter {
                    viewModel.dateFormatter(it.date) >= fromDate
                }.also { adapter.submitList(it) }
            })

            viewModel.toDate.observe(viewLifecycleOwner, { toDate ->
                viewModel.fromDate.observe(viewLifecycleOwner, { fromDate ->
                    jogs.filter {
                        viewModel.dateFormatter(it.date) in fromDate..toDate
                    }.also { adapter.submitList(it) }
                })
                jogs.filter {
                    viewModel.dateFormatter(it.date) <= toDate
                }.also { adapter.submitList(it) }
            })

            adapter.submitList(jogs)
        })

        binding.jogsRecyclerView.adapter = adapter
        binding.jogsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), 1))

        viewModel.jogUpdateSuccessListener.observe(viewLifecycleOwner, {
            when (it) {
                true -> toast("jog updated")
                false -> toast("something goes wrong")
            }
        })
        binding.fromButton.setOnClickListener {
            showDatePickerDialog()
            viewModel.setFromListener()
        }
        binding.toButton.setOnClickListener {
            showDatePickerDialog()
            viewModel.setToListener()
        }
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            this,
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    override fun getFragmentView(): Int = R.layout.jogs_list_fragment

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val date = "$dayOfMonth.${month + 1}.$year"

        viewModel.fromDateToDateListener.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.fromDateTextView.text = date
                    viewModel.setFromDate(viewModel.dateFormatter(date))
                }
                false -> {
                    binding.toDateTextView.text = date
                    viewModel.setToDate(viewModel.dateFormatter(date))
                }
            }
        })
    }
}