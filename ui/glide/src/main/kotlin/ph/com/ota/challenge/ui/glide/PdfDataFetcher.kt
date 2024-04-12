/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.data.HttpUrlFetcher
import com.bumptech.glide.load.model.GlideUrl
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class PdfDataFetcher constructor(
  private val context: Context,
  private val model: PdfUrl,
) : DataFetcher<Bitmap> {

  private lateinit var fetcher: HttpUrlFetcher

  override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in Bitmap>) {
    try {
      var url = model.url
      val pdf = Uri.parse(url).lastPathSegment
      val cacheDir = Glide.getPhotoCacheDir(context.applicationContext)
      val thumbnail = File(cacheDir, "$pdf.png")

      if (!thumbnail.exists()) {
        if (!url.startsWith("http:")) {
          // Force HTTPS
          url = "https:$url"
        }

        val callback: DataFetcher.DataCallback<in InputStream> = object :
          DataFetcher.DataCallback<InputStream> {
          override fun onDataReady(input: InputStream?) {
            val file = File(cacheDir, pdf)
            input.use {
              file.outputStream().use { output ->
                input?.copyTo(output)
              }
            }

            val parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(parcelFileDescriptor)
            if (pdfRenderer.getPageCount() != 0) {
              val page = pdfRenderer.openPage(0)
              val output = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
              page.render(output, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
              page.close()
              callback.onDataReady(output)
              // now saving thumbnail on local disc
              val fileOutputStream = FileOutputStream(thumbnail)
              output.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream)
              fileOutputStream.flush()
              fileOutputStream.close()
            }

            pdfRenderer.close()
            parcelFileDescriptor.close()

            callback.onDataReady(BitmapFactory.decodeFile(thumbnail.absolutePath))
          }

          override fun onLoadFailed(exception: java.lang.Exception) {
            callback.onLoadFailed(exception)
          }
        }

        fetcher = HttpUrlFetcher(GlideUrl(url), 30000)
        fetcher.loadData(priority, callback)
      } else {
        callback.onDataReady(BitmapFactory.decodeFile(thumbnail.absolutePath))
      }
    } catch (exception: Exception) {
      callback.onLoadFailed(exception)
    }
  }

  override fun cleanup() {
  }

  override fun cancel() {
  }

  override fun getDataClass(): Class<Bitmap> {
    return Bitmap::class.java
  }

  override fun getDataSource(): DataSource {
    return DataSource.LOCAL
  }
}
