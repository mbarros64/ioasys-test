package com.matheus.testioasys.ui.enterprisedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import coil.load
import com.matheus.testioasys.R
import com.matheus.testioasys.data.model.Enterprise
import com.matheus.testioasys.databinding.FragmentEnterpriseDetailsBinding
import com.matheus.testioasys.extensions.doOnApplyWindowInsets
import com.matheus.testioasys.extensions.onClickListener
import com.matheus.testioasys.extensions.setTranslucentWindowControls
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.view_enterprise_search_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterpriseDetailsFragment : Fragment() {

    private lateinit var viewBinding: FragmentEnterpriseDetailsBinding

    private val viewModel: EnterpriseDetailsViewModel by viewModel()

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentEnterpriseDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(backPressedCallback)

        readArgs()
        setupTranslucentWindowControls()
        setupContentWindowInsets()
        setupEnterpriseData()
        setupBackButton()
    }

    override fun onDestroy() {
        backPressedCallback.remove()
        super.onDestroy()
    }

    // region Setup

    private fun readArgs() {
        viewModel.enterprise = arguments?.getParcelable(ARG_ENTERPRISE)
    }

    private fun setupTranslucentWindowControls() {
        context?.let {
            activity?.setTranslucentWindowControls(
                    navigationBarColor = ContextCompat.getColor(
                            it,
                            R.color.colorWhiteNavigationBar
                    ), withLightStatusBar = false, withLightNavigationBar = true
            )
        }
    }

    private fun setupContentWindowInsets() {
        viewBinding.contentContainer.doOnApplyWindowInsets { view, windowInsets, initialPadding ->
            view.updatePadding(bottom = initialPadding.bottom + windowInsets.systemWindowInsetBottom)
        }
    }

    private fun setupEnterpriseData() {
        viewModel.enterprise?.let { enterprise ->
            viewBinding.apply {
                name.text = enterprise.enterpriseName
                photo.load(enterprise.photo) {
                    crossfade(true)
                    placeholder(
                            ContextCompat.getColor(context ?: return, R.color.colorPrimary).toDrawable()
                    )
                }
                description.text = enterprise.description
            }
        }
    }

    private fun setupBackButton() {
        viewBinding.backButton.onClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        const val TAG = "EnterpriseDetailsFragment"
        private const val ARG_ENTERPRISE = "arg_enterprise"

        fun newInstance(enterPrise: Enterprise? = null) = EnterpriseDetailsFragment().apply {
            arguments = bundleOf(ARG_ENTERPRISE to enterPrise)
        }
    }
}