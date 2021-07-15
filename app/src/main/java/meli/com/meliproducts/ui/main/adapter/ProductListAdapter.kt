package meli.com.meliproducts.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meli.com.meliproducts.R
import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.databinding.ItemViewProductBinding
import meli.com.meliproducts.extensions.setRoundCorners
import meli.com.meliproducts.ui.util.ImageUtils
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Adapter to display search results.
 */
class ProductListAdapter(
    private val onItemClicked: (Product) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ResultViewHolder>() {

    var productList: List<Product?>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ResultViewHolder(
        view: ItemViewProductBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view.root) {

        private val binding = ItemViewProductBinding.bind(view.root)
        val name = binding.productNameLabel
        val price = binding.productPriceLabel
        val image = binding.productImage

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ItemViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) { productList?.get(it)?.let(onItemClicked) }
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.name.text = productList!![position]?.title
        val decimalFormat = DecimalFormat("#,###,###")
        decimalFormat.roundingMode = RoundingMode.DOWN
        holder.price.text = "$ ".plus(decimalFormat.format(productList!![position]?.price))
        ImageUtils.load(
            imageView = holder.image,
            placeholder = R.drawable.ic_image,
            url = productList!![position]?.thumbnail!!,
        )
        holder.image.setRoundCorners(R.dimen.radius_size_s)
    }
}