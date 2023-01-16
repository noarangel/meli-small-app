package com.smallmeliapp.product.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.smallmeliapp.R
import com.smallmeliapp.adapter.HomeListAdapter
import com.smallmeliapp.adapter.ProductDetailCarouselAdapter
import com.smallmeliapp.core.extension.MoneyExtension.geSimpleFormat
import com.smallmeliapp.home.viewmodel.HomeViewModel
import com.smallmeliapp.model.ProductPictureModel
import com.smallmeliapp.model.SiteModel
import com.smallmeliapp.product.viewmodel.ProductViewModel
import com.smallmeliapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbarBackButton = view.findViewById<ImageView>(R.id.productDetailBackButton)
        val productDetailCarousel = view.findViewById<ViewPager2>(R.id.productDetailCarousel)
        val productListItemTitleTextview =
            view.findViewById<TextView>(R.id.productListItemTitleTextview)
        val productListItemAmountTextView =
            view.findViewById<TextView>(R.id.productListItemAmountTextView)
        val productDetailDescription =
            view.findViewById<TextView>(R.id.productDetailDescription)
        val productDetailCarouselCount =
            view.findViewById<TextView>(R.id.productDetailCarouselCount)

        productDetailCarousel.apply {
            clipChildren = true
            clipToPadding =
                false
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
        }

        productViewModel.productDetailModelUI.observe(viewLifecycleOwner) {
            productDetailCarousel.adapter = ProductDetailCarouselAdapter(
                productViewModel.productDetailModelUI.value?.productItemDetailModel?.pictures
                    ?: listOf<ProductPictureModel>()
            )
            productListItemTitleTextview.text = it.productItemDetailModel?.title ?: ""
            productListItemAmountTextView.text =
                it.productItemDetailModel?.price?.geSimpleFormat(
                    it.productItemDetailModel?.siteId ?: Constants.ID_SITE_COLOMBIA
                )
            productDetailDescription.text = it.descriptionModel?.plainText ?: ""
        }

        productDetailCarousel.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                productDetailCarouselCount.text = resources.getString(
                    R.string.product_detail_carousel_count_text,
                    "${position + 1}",
                    "${productDetailCarousel.adapter?.itemCount}"
                )
                super.onPageSelected(position)
            }
        })

        toolbarBackButton.setOnClickListener { findNavController().popBackStack() }
        super.onViewCreated(view, savedInstanceState)
    }
}