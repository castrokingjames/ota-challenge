/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory

class PdfModelLoaderFactory constructor(
  private val context: Context,
) : ModelLoaderFactory<PdfUrl, Bitmap> {

  override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<PdfUrl, Bitmap> {
    return PdfModelLoader(context)
  }

  override fun teardown() {
  }
}
