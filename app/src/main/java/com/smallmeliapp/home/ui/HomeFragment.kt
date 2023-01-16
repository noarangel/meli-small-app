package com.smallmeliapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smallmeliapp.R
import com.smallmeliapp.adapter.HomeListAdapter
import com.smallmeliapp.home.viewmodel.HomeViewModel
import com.smallmeliapp.model.SiteModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel.init()
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sitesList = view.findViewById<RecyclerView>(R.id.homeSitesRecyclerView)

        homeViewModel.sites.observe(viewLifecycleOwner, Observer {
            sitesList.adapter = null
            val homeListAdapter =
                HomeListAdapter(homeViewModel.sites.value ?: listOf<SiteModel>()) { site ->
                    homeViewModel.onSiteListItemClick(
                        site
                    )
                    findNavController().navigate(R.id.action_homeFragment_to_homeSearchFragment)

                }
            sitesList?.layoutManager = LinearLayoutManager(activity)
            sitesList?.adapter = homeListAdapter
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        homeViewModel.sites.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}