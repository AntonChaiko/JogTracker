package com.example.jogtracker.ui.screens.createjogfragment

import android.os.Bundle
import android.view.View
import com.example.domain.models.JogEntity
import com.example.jogtracker.R
import com.example.jogtracker.databinding.CreateJogFragmentBinding
import com.example.jogtracker.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CreateJogFragment : BaseFragment<CreateJogFragmentBinding>() {

    private val viewModel: CreateJogViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val date = binding.dateTextInput.text.toString()
            val distance = binding.distanceTextInput.text.toString()
            val time = binding.timeTextInput.text.toString()

            if (date.isNotEmpty() && distance.isNotEmpty() && time.isNotEmpty()) {
                viewModel.addNewJog(
                    viewModel.createJogEntity(
                        id = 0,
                        userId = "",
                        distance = distance.toDouble(),
                        time = time.toInt(),
                        date = date
                    )
                )
            } else {
                toast("please fill in all fields")
                return@setOnClickListener
            }

        }

        viewModel.isCreateJogSuccess.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    hideKeyboard()
                    toast("jog created!")
                }
                false -> toast("something goes wrong")
            }
        })
    }

    override fun getFragmentView(): Int = R.layout.create_jog_fragment

}