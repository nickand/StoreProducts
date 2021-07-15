package meli.com.meliproducts.ui.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.size.Scale
import com.facebook.shimmer.ShimmerFrameLayout
import meli.com.meliproducts.R

class ImageUtils {
    companion object {
        fun load(imageView: ImageView, url:String,
                 @DrawableRes placeholder: Int,
                 shimmerLayout: ShimmerFrameLayout? = null) {
            shimmerLayout?.showShimmer(true)
            imageView.load(url) {
                crossfade(true)
                placeholder(placeholder)
                allowHardware(true)
                scale(Scale.FIT)
                listener(object : ImageRequest.Listener {
                    override fun onSuccess(
                        request: ImageRequest,
                        metadata: ImageResult.Metadata
                    ) {
                        super.onSuccess(request, metadata)
                        shimmerLayout?.hideShimmer()
                    }

                    override fun onError(
                        request: ImageRequest,
                        throwable: Throwable
                    ) {
                        super.onError(request, throwable)
                        imageView.setImageResource(R.drawable.ic_broken_document)
                        shimmerLayout?.hideShimmer()
                    }
                })
            }
        }
    }
}