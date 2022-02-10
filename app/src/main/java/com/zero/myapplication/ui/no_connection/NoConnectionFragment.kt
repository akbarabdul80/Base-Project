package com.zero.myapplication.ui.no_connection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.oratakashi.viewbinding.core.binding.bottomsheet.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.databinding.FragmentNoConnectionBinding

class NoConnectionFragment : SuperBottomSheetFragment() {

    private val binding: FragmentNoConnectionBinding by viewBinding()

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnSubmit.onClick {
                callback()
                dismiss()
            }
        }
    }

    companion object {
        private lateinit var callback: () -> Unit

        fun newInstance(tryAgainFunction: () -> Unit): NoConnectionFragment {
            val fragment = NoConnectionFragment()
            callback = tryAgainFunction
            return fragment
        }
    }

    override fun getExpandedHeight(): Int {
        return -2
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }


}