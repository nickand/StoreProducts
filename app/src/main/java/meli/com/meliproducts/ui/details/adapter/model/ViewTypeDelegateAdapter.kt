package meli.com.meliproducts.ui.details.adapter.model

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meli.com.meliproducts.ui.details.adapter.model.ViewType

/**
 * Generic adapter to hold a list of delegate adapters.
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}