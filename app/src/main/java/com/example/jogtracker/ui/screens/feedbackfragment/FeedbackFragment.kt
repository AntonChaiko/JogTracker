package com.example.jogtracker.ui.screens.feedbackfragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.jogtracker.R
import com.example.jogtracker.databinding.FeedbackFragmentBinding
import com.example.jogtracker.ui.base.BaseFragment
import com.example.jogtracker.ui.screens.createjogfragment.CreateJogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedbackFragment : BaseFragment<FeedbackFragmentBinding>() {

    private val viewModel: FeedbackViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        var topicId = 1

        binding.sendFeedbackButton.setOnClickListener {
            viewModel.sendFeedback(
                topicId,
                binding.feedbackTextInput.text.toString()
            )
        }
        viewModel.isFeedbackSend.observe(viewLifecycleOwner, {
            when(it) {
                true -> {
                    toast("review sent")
                    hideKeyboard()
                }
                false -> toast("something goes wrong")
            }
        })



        binding.topicIdSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    topicId = adapterView?.getItemAtPosition(position).toString().toInt()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit

            }
    }

    override fun getFragmentView(): Int = R.layout.feedback_fragment

}