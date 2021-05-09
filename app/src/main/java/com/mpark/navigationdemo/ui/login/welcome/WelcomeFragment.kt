package com.mpark.navigationdemo.ui.login.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mpark.navigationdemo.R
import com.mpark.navigationdemo.databinding.FragmentWelcomeBinding
import com.mpark.navigationdemo.ui.common.base.BaseFragment
import com.mpark.navigationdemo.ui.common.viewmodel.ViewModelFactory

class WelcomeFragment : BaseFragment() {

    private var mutableBinding: FragmentWelcomeBinding? = null
    private val binding get() = requireNotNull(mutableBinding)

    private val welcomeViewModel by viewModels<WelcomeViewModel> { ViewModelFactory(appModule) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mutableBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.signInBtn.setOnClickListener {
            screensNavigator.goToLogin()
        }
    }

    private fun observeViewModel() {
        welcomeViewModel.getAppUser().observe(viewLifecycleOwner) { loggedInUserView ->
            binding.welcomeTxt.text = getString(R.string.welcome_back, loggedInUserView.displayName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

}