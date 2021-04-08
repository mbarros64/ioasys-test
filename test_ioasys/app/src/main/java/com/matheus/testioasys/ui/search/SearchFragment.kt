package com.matheus.testioasys.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.matheus.testioasys.R
import com.matheus.testioasys.data.auth.AuthException
import com.matheus.testioasys.databinding.FragmentSearchBinding
import com.matheus.testioasys.extensions.globalSafeClickListener
import com.matheus.testioasys.extensions.hideKeyboard
import com.matheus.testioasys.extensions.showKeyboard
import com.matheus.testioasys.ui.signin.SignInActivity
import com.matheus.testioasys.util.GlobalHelper
import com.matheus.testioasys.util.RequestState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var viewBinding: FragmentSearchBinding
    private lateinit var searchResultAdapter: SearchResultAdapter

    private var performSearchRunnable: Runnable? = null

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWindowInset()
        setupStartSearchButton()
        setupRequestStateUpdate()
        setupCancelSearchButton()
        setupSearchButton()
        setupSearchAction()
        setupTextChange()
        setupSearchResult()
        setupSearchResultList()
    }

    // region Setup

    private fun setupWindowInset() {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.toolbar) { v, insets ->
            v.updatePadding(top = v.paddingTop + insets.systemWindowInsetTop)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.searchResultList) { v, insets ->
            v.updatePadding(bottom = v.paddingBottom + insets.systemWindowInsetBottom)
            insets
        }
    }

    private fun setupStartSearchButton() {
        viewBinding.searchButton.globalSafeClickListener {
            startSearch()
            viewBinding.startSearchMessage.isVisible = false
        }
    }

    private fun setupCancelSearchButton() {
        viewBinding.searchTextInputLayout.apply {
            isEndIconVisible = true
            endIconDrawable =
                    ResourcesCompat.getDrawable(context.resources, R.drawable.ic_close, null)
            setEndIconOnClickListener {
                cancelSearch()
            }
        }
    }

    private fun setupSearchButton() {
        viewBinding.searchTextInputLayout.setStartIconOnClickListener {
            if (GlobalHelper.canClick()) {
                performSearch()
            }
        }
    }

    private fun setupSearchAction() {
        viewBinding.searchTextInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun setupTextChange() {
        viewBinding.searchTextInput.doOnTextChanged { text, _, _, _ ->
            text?.takeIf { it.isNotEmpty() }?.let { text ->
                performSearchDelayed()
            }
        }
    }

    private fun setupSearchResultList() {
        viewBinding.searchResultList.apply {
            adapter = SearchResultAdapter().also {
                searchResultAdapter = it
            }
            itemAnimator = null
        }
    }

    private fun setupSearchResult() {
        viewModel.searchResult.observe(viewLifecycleOwner, {
            if (it?.isNotEmpty() == true) {
                if (viewBinding.noResultMessage.isVisible) {
                    viewBinding.noResultMessage.isVisible = false
                }

                if (!viewBinding.searchResultList.isVisible) {
                    viewBinding.searchResultList.isVisible = true
                }

                searchResultAdapter.updateList(it)
            } else if (performSearchRunnable == null) {
                searchResultAdapter.updateList(listOf())
                viewBinding.apply {
                    noResultMessage.isVisible = true
                    searchResultList.isVisible = false
                }
            }
        })
    }

    private fun setupRequestStateUpdate() {
        viewModel.requestState.observe(viewLifecycleOwner, {
            if (it is RequestState.FAILED && it.exception is AuthException.UnauthorizedException) {
                onAuthError()
            }
        })
    }

    // endregion

    private fun search() {
        viewBinding.searchTextInput.text?.toString()?.let {
            viewModel.search(it)
        }
    }

    private fun performSearch() {
        search()
        cancelDelayedSearch()
        closeKeyboard()
    }

    private fun startSearch() {
        viewBinding.apply {
            logo.isVisible = false
            searchButton.isVisible = false
            searchTextInputLayout.isVisible = true
            searchTextInputLayout.requestFocus()
            activity?.showKeyboard()
        }
    }

    private fun cancelSearch() {
        closeKeyboard()
        viewBinding.apply {
            searchTextInputLayout.isVisible = false
            searchTextInput.text = null
            logo.isVisible = true
            searchButton.isVisible = true
        }
    }

    private fun closeKeyboard() {
        activity?.hideKeyboard()
        viewBinding.root.requestFocus()
    }

    private fun performSearchDelayed() {
        cancelDelayedSearch()
        performSearchRunnable = Runnable {
            search()
            performSearchRunnable = null
        }.also {
            view?.postDelayed(it, SEARCH_DELAY)
        }
    }

    private fun cancelDelayedSearch() {
        performSearchRunnable?.let {
            view?.removeCallbacks(it)
            performSearchRunnable = null
        }
    }

    private fun onAuthError() {
        activity?.apply {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    companion object {
        const val TAG = "com.matheus.testioasys.ui.search.SearchFragment"
        private const val SEARCH_DELAY = 300L

        fun newInstance() = SearchFragment()
    }
}