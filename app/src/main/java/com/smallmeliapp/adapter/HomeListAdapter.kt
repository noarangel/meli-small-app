package com.smallmeliapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smallmeliapp.R
import com.smallmeliapp.model.SiteModel
import com.smallmeliapp.core.extension.StringExtension.getCountryIcon

class HomeListAdapter(
    private val siteList: List<SiteModel>, private val onSiteItemClicked: (SiteModel) -> Unit
) :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homeItemImage: ImageView
        val homeItemText: TextView

        init {
            homeItemImage = view.findViewById(R.id.homeItemImageView)
            homeItemText = view.findViewById(R.id.homeItemTextview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val homeItemCard =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_list_item_layout, parent, false)
        return ViewHolder(homeItemCard)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.homeItemText.text = siteList[position].name
        holder.homeItemImage.setImageResource(siteList[position].id.getCountryIcon())
        holder.itemView.setOnClickListener { onSiteItemClicked(siteList[position]) }
    }

    override fun getItemCount() = siteList.size
}
