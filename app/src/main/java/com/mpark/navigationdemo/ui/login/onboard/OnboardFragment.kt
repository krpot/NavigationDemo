package com.mpark.navigationdemo.ui.login.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mpark.navigationdemo.databinding.FragmentOnboardBinding
import com.mpark.navigationdemo.ui.common.base.BaseFragment

class OnboardFragment : BaseFragment() {

    private var mutableBinding: FragmentOnboardBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = requireNotNull(mutableBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mutableBinding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.doneBtn.setOnClickListener {
            appModule.sessionStore.onBoarded = true
            screensNavigator.onboardToHome()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

}