package com.mpark.navigationdemo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mpark.navigationdemo.databinding.FragmentDashboardBinding
import com.mpark.navigationdemo.ui.common.base.BaseFragment

class DashboardFragment : BaseFragment() {

    private var mutableBinding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = requireNotNull(mutableBinding)

    private val dashboardViewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mutableBinding = FragmentDashboardBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }
}