package com.mpark.navigationdemo.ui.login.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mpark.navigationdemo.data.SessionStore
import com.mpark.navigationdemo.databinding.FragmentOnboardBinding
import com.mpark.navigationdemo.ui.common.base.BaseFragment
import com.mpark.navigationdemo.ui.common.navigation.Destinations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardFragment : BaseFragment() {

    @Inject
    lateinit var sessionStore: SessionStore

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
            sessionStore.onBoarded = true
            navManager.navigate(Destinations.home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

}