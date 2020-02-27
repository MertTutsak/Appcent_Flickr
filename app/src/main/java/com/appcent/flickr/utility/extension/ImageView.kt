package com.appcent.flickr.utility.extension

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.appcent.flickr.utility.provider.PicassoProvider
import com.appcent.flickr.utility.transform.CircleTransform
import com.squareup.picasso.Callback

/**Examples**/
/**
 * imgView.loadImage("...url...", R.drawable.placeholder, listener)
 * imgView.loadImage("...url...", null, null)
 *
 * parameters -->
 * url -> should be url
 * placeholder -> can be null
 * listener -> can be null
 */
fun ImageView.loadImage(
    url: String?,
    placeholder: Drawable?,
    listener: ImageLoadListener?
) {
    try {
        if (url.isNullOrEmpty()) {
            listener?.onError(NullPointerException())
            return
        }

        if (placeholder != null) {
            PicassoProvider.with(this.context)
                .load(url)
                .placeholder(placeholder)
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        } else {
            PicassoProvider.with(this.context)
                .load(url)
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        }
    } catch (e: java.lang.Exception) {
        listener?.onError(e)
    }
}

/**Examples**/
/**
 * imgView.loadImage(uri, R.drawable.placeholder, listener)
 * imgView.loadImage(uri, null, null)
 *
 * parameters -->
 * image -> should be Uri
 * placeholder -> can be null
 * listener -> can be null
 */

fun ImageView.loadImage(
    image: Uri?,
    placeholder: Drawable?,
    listener: ImageLoadListener?
) {
    try {
        if (image == null) {
            listener?.onError(NullPointerException())
            return
        }

        if (placeholder != null) {
            PicassoProvider.with(this.context)
                .load(image)
                .placeholder(placeholder)
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        } else {
            PicassoProvider.with(this.context)
                .load(image)
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        }
    } catch (e: java.lang.Exception) {
        listener?.onError(e)
    }
}

/**Examples**/
/**
 * imgView.loadCircleImage("...url...", R.drawable.placeholder, listener)
 * imgView.loadCircleImage("...url...", null, null)
 *
 * parameters -->
 * url -> should be url
 * placeholder -> can be null
 * listener -> can be null
 */
fun ImageView.loadCircleImage(
    url: String?,
    placeholder: Drawable?,
    listener: ImageLoadListener?
) {
    try {
        if (url.isNullOrEmpty()) {
            listener?.onError(NullPointerException())
            return
        }

        if (placeholder != null) {
            PicassoProvider.with(this.context)
                .load(url)
                .transform(CircleTransform())
                .placeholder(placeholder)
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        } else {
            PicassoProvider.with(this.context)
                .load(url)
                .transform(CircleTransform())
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        }
    } catch (e: java.lang.Exception) {
        listener?.onError(e)
    }
}

/**Examples**/
/**
 * imgView.loadCircleImage(uri, R.drawable.placeholder, listener)
 * imgView.loadCircleImage(uri, null, null)
 *
 * parameters -->
 * image -> should be Uri
 * placeholder -> can be null
 * listener -> can be null
 */
fun ImageView.loadCircleImage(
    image: Uri?,
    placeholder: Drawable?,
    listener: ImageLoadListener?
) {
    try {
        if (image == null) {
            listener?.onError(NullPointerException())
            return
        }

        if (placeholder != null) {
            PicassoProvider.with(this.context)!!
                .load(image)
                .transform(CircleTransform())
                .placeholder(placeholder)
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        } else {
            PicassoProvider.with(this.context)
                .load(image)
                .transform(CircleTransform())
                .fit().centerCrop().into(this, object : Callback {
                    override fun onSuccess() {
                        listener?.onSuccess()
                    }

                    override fun onError(e: Exception) {
                        listener?.onError(e)
                    }
                })
        }
    } catch (e: java.lang.Exception) {
        listener?.onError(e)
    }
}


/**Examples**/
/**
 * imgView.loadUrl(uri)
 *
 * parameters -->
 * image -> should be Url
 */
fun ImageView.loadUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        PicassoProvider.with(context).load(url).into(this)
    }
}

interface ImageLoadListener {
    fun onSuccess()
    fun onError(e: Exception)
}