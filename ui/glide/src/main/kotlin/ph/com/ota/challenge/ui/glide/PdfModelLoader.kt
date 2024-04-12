/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey

class PdfModelLoader constructor(
  private val context: Context,
) : ModelLoader<PdfUrl, Bitmap> {

  override fun buildLoadData(
    model: PdfUrl,
    width: Int,
    height: Int,
    options: Options,
  ): ModelLoader.LoadData<Bitmap>? {
    return ModelLoader.LoadData(ObjectKey(model), PdfDataFetcher(context, model))
  }

  override fun handles(model: PdfUrl): Boolean {
    return true
  }
}
