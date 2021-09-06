package com.example.jogtracker.ui.screens.loginfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.jogtracker.R
import com.example.jogtracker.databinding.LoginFragmentBinding
import com.example.jogtracker.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            viewModel.makeCall(binding.uuidTextInput.text.toString())
            hideKeyboard()
        }

        viewModel.loginSuccessListener.observe(viewLifecycleOwner, {
            when (it) {
                null -> return@observe
                true -> findNavController().navigate(R.id.action_loginFragment_to_jogsListFragment)
                false -> {
                    toast("Incorrect UUID")
                    return@observe
                }
            }
        })

    }

    override fun getFragmentView(): Int = R.layout.login_fragment


}