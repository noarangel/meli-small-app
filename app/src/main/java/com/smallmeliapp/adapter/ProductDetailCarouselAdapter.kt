package com.smallmeliapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smallmeliapp.R
import com.smallmeliapp.model.ProductPictureModel
import com.squareup.picasso.Picasso

class ProductDetailCarouselAdapter(
    private val picturesList: List<ProductPictureModel>,
) :
    RecyclerView.Adapter<ProductDetailCarouselAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productPictureImageView: ImageView

        init {
            productPictureImageView = view.findViewById(R.id.productDetailPictureImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val productPicture = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_detail_picture_layout, parent, false)
        return ProductDetailCarouselAdapter.ViewHolder(productPicture)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(picturesList[position].secureUrl).into(holder.productPictureImageView)
    }

    override fun getItemCount(): Int = picturesList.size

}