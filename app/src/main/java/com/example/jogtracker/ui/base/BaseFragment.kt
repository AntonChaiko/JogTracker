package com.example.jogtracker.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.jogtracker.util.view.hideKeyboard
import com.example.jogtracker.util.view.toast

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getFragmentView(), container, false)
        return binding.root
    }

    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun toast(message: String) {
        view?.let { activity?.toast(message) }
    }

    abstract fun getFragmentView(): Int

}