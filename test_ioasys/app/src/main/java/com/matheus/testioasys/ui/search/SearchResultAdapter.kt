package com.matheus.testioasys.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.matheus.testioasys.R
import com.matheus.testioasys.data.model.Enterprise
import com.matheus.testioasys.databinding.ViewEnterpriseSearchResultBinding
import com.matheus.testioasys.extensions.globalSafeClickListener

class SearchResultAdapter(private val onItemSelected: (Enterprise) -> Unit = {}) :
        RecyclerView.Adapter<SearchResultAdapter.ItemViewHolder>() {

    private val items: ArrayList<Enterprise> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.view_enterprise_search_result, parent, false), onItemSelected
            )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Enterprise>) {
        val oldList = ArrayList(items)

        items.clear()
        items.addAll(newList)

        val diffResult = DiffUtil.calculateDiff(EnterpriseDiffCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }

    class ItemViewHolder(view: View, onItemSelected: (Enterprise) -> Unit) : RecyclerView.ViewHolder(view) {

        private val viewBinding = ViewEnterpriseSearchResultBinding.bind(view)

        private var currentEnterprise: Enterprise? = null

        init {
            itemView.globalSafeClickListener {
                currentEnterprise?.let(onItemSelected)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(enterprise: Enterprise) {
            currentEnterprise = enterprise
            viewBinding.apply {
                name.text = enterprise.enterpriseName
                type.text = enterprise.enterpriseType.enterpriseTypeName
                city.text = "${enterprise.city}, ${enterprise.country}"
                photo.load(enterprise.photo) {
                    crossfade(true)
                    placeholder(
                            ContextCompat.getColor(itemView.context, R.color.colorPrimary).toDrawable()
                    )
                }
            }
        }
    }

    class EnterpriseDiffCallback(
            private val oldList: List<Enterprise>,
            private val newList: List<Enterprise>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}