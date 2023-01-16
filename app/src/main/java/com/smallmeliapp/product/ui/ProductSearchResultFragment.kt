package com.smallmeliapp.product.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.smallmeliapp.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smallmeliapp.adapter.ProductSearchResultListAdapter
import com.smallmeliapp.home.viewmodel.HomeViewModel
import com.smallmeliapp.model.SearchResultItemModel
import com.smallmeliapp.product.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchResultFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val productViewModel: ProductViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_search_result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val productsList = view.findViewById<RecyclerView>(R.id.productResultListRecyclerView)
        val productSearchBackButton: ImageView = view.findViewById(R.id.productSearchBackButton)
        val productSearchTitleTextView: TextView =
            view.findViewById(R.id.productSearchTitleTextView)


        homeViewModel.searchResult.observe(viewLifecycleOwner) {
            productsList.adapter = null
            productSearchTitleTextView.text = it?.query
            val productSearchResultListAdapter =
                ProductSearchResultListAdapter(
                    homeViewModel.searchResult.value?.results ?: listOf<SearchResultItemModel>()
                ) { productItem ->
                    productViewModel.getDetailProduct(productItem.id)
                    findNavController().navigate(R.id.action_productSearchResultFragment_to_productDetailFragment)
                }
            productsList?.layoutManager = LinearLayoutManager(activity)
            productsList?.adapter = productSearchResultListAdapter

            productSearchBackButton.setOnClickListener { findNavController().popBackStack() }
        }

        super.onViewCreated(view, savedInstanceState)
    }

}