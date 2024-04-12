/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideModule : AppGlideModule() {

  override fun isManifestParsingEnabled(): Boolean {
    return false
  }

  override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
    registry.prepend(PdfUrl::class.java, Bitmap::class.java, PdfModelLoaderFactory(context))
  }
}
