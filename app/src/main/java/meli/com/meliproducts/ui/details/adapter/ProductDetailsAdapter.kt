package meli.com.meliproducts.ui.details.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import meli.com.meliproducts.ui.details.adapter.model.*

/**
 * Adapter to display all data regarding a Product
 */
class ProductDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var pictures: PictureItemView? = null
        set(value) {
            field = value
            value?.let { items.add(it) }
            notifyDataSetChanged()
        }
    var titleInfo: DetailsItemView? = null
        set(value) {
            field = value
            value?.let { items.add(it) }
            notifyDataSetChanged()
        }
    var attributes: List<AttributesItemView>? = null
        set(value) {
            field = value
            value?.let { items.addAll(it) }
            notifyDataSetChanged()
        }
    var items: ArrayList<ViewType> = ArrayList()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapters.put(AdapterConstants.PICTURES, ProductPicturesDelegateAdapter())
        delegateAdapters.put(AdapterConstants.DETAILS, ProductTitleDelegateAdapter())
        delegateAdapters.put(AdapterConstants.ATTRIBUTES, ProductAttributesDelegateAdapter())
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

}











