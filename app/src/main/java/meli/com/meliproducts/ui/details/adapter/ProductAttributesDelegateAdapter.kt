package meli.com.meliproducts.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meli.com.meliproducts.databinding.ItemViewProductDetailAttributesBinding
import meli.com.meliproducts.ui.details.adapter.model.AttributesItemView
import meli.com.meliproducts.ui.details.adapter.model.ViewType
import meli.com.meliproducts.ui.details.adapter.model.ViewTypeDelegateAdapter

/**
 * Delegate adapter to display an attribute label and value
 */
class ProductAttributesDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AttributeViewHolder(
            ItemViewProductDetailAttributesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as AttributeViewHolder
        holder.bind(item as AttributesItemView)
    }

    inner class AttributeViewHolder(view: ItemViewProductDetailAttributesBinding) :
        RecyclerView.ViewHolder(view.root) {
        private val binding = ItemViewProductDetailAttributesBinding.bind(view.root)
        private val label = binding.textView
        private val value = binding.textView2

        fun bind(item: AttributesItemView) {
            label.text = "${item.name}:"
            value.text = item.value
        }
    }
}