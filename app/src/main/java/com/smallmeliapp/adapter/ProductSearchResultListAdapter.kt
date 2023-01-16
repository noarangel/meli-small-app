package com.smallmeliapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.smallmeliapp.R
import com.smallmeliapp.core.extension.MoneyExtension.geSimpleFormat
import com.smallmeliapp.model.SearchResultItemModel
import com.smallmeliapp.utils.Constants
import com.squareup.picasso.Picasso

class ProductSearchResultListAdapter(
    private val searchResultsModel: List<SearchResultItemModel>,
    private val onProductItemClicked: (SearchResultItemModel) -> Unit
) : RecyclerView.Adapter<ProductSearchResultListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productSearchResultItemCardView: CardView
        val productItemThumbnailImageView: ImageView
        val productListItemTitleTextview: TextView
        val productListItemAmountTextView: TextView
        val freeShippingProductLabel: TextView

        init {
            productSearchResultItemCardView =
                view.findViewById(R.id.productSearchResultItemCardView)
            productItemThumbnailImageView = view.findViewById(R.id.productItemThumbnailImageView)
            productListItemTitleTextview = view.findViewById(R.id.productListItemTitleTextview)
            productListItemAmountTextView = view.findViewById(R.id.productListItemAmountTextView)
            freeShippingProductLabel = view.findViewById(R.id.freeShippingProductLabel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val productItemCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item_layout, parent, false)
        return ViewHolder(productItemCard)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(
            searchResultsModel[position].thumbnail.replace(
                Constants.HTTP_VALUE,
                Constants.HTTPS_VALUE
            )
        )
            .error(R.drawable.no_pictures)
            .into(holder.productItemThumbnailImageView)
        holder.productListItemTitleTextview.text = searchResultsModel[position].title
        holder.productListItemAmountTextView.text =
            searchResultsModel[position].price?.geSimpleFormat(searchResultsModel[position].siteId)
        holder.freeShippingProductLabel.isVisible =
            searchResultsModel[position].shippingInfoModel?.freeShipping ?: false
        holder.productSearchResultItemCardView.setOnClickListener {
            onProductItemClicked(
                searchResultsModel[position]
            )
        }
    }

    override fun getItemCount(): Int = searchResultsModel.size

}