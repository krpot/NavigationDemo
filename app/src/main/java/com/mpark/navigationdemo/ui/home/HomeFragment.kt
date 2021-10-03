package com.mpark.navigationdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mpark.navigationdemo.databinding.FragmentHomeBinding
import com.mpark.navigationdemo.ui.common.base.BaseFragment
import com.mpark.navigationdemo.ui.common.navigation.Destinations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var mutableBinding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(mutableBinding)

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mutableBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

    private fun setupViews() {
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        binding.logout.setOnClickListener {
            navManager.navigate(Destinations.logout)
        }
    }

}