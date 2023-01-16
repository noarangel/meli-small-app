package com.smallmeliapp.home.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.smallmeliapp.R
import com.smallmeliapp.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeSearchFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val homeSearchView: SearchView = view.findViewById(R.id.homeSearchView)
        val toolbarBackButton: ImageView = view.findViewById(R.id.searchHomeBackButton)

        val editText =
            homeSearchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editText.setTextColor(resources.getColor(R.color.dark_gray, null))
            editText.setHintTextColor(resources.getColor(R.color.light_gray, null));
            editText.hint = resources.getString(R.string.home_search_hint)
        };

        homeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                homeViewModel.getSearchResults(query ?: "")
                findNavController().navigate(R.id.action_homeSearchFragment_to_productSearchResultFragment)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        toolbarBackButton.setOnClickListener { findNavController().popBackStack() }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}