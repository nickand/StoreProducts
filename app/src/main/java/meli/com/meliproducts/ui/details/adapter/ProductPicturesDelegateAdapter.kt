package meli.com.meliproducts.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import meli.com.meliproducts.databinding.ItemViewProductDetailPicturesBinding
import meli.com.meliproducts.ui.details.adapter.model.PictureItemView
import meli.com.meliproducts.ui.details.adapter.model.ViewType
import meli.com.meliproducts.ui.details.adapter.model.ViewTypeDelegateAdapter

/**
 * Delegate adapter to display a Product's pictures.
 */
class ProductPicturesDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PicturesViewHolder(
            ItemViewProductDetailPicturesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PicturesViewHolder
        holder.bind(item as PictureItemView)
    }

    inner class PicturesViewHolder(view: ItemViewProductDetailPicturesBinding) :
        RecyclerView.ViewHolder(view.root) {

        private val binding = ItemViewProductDetailPicturesBinding.bind(view.root)
        private val picturesList = binding.picturesList
        private val pictureAdapter = PicturesAdapter()

        init {
            picturesList.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            picturesList.adapter = pictureAdapter
            LinearSnapHelper().attachToRecyclerView(picturesList)
        }

        fun bind(item: PictureItemView) {
            pictureAdapter.urls = item.urls
        }
    }

}