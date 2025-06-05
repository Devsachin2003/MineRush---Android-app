package com.example.minerush.User

import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityPdfViewerBinding
import java.io.File

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding
    private val fileName = "temp_downloaded_file.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pdfUrl = intent.getStringExtra("pdfUrl") ?: ""

        if (pdfUrl.isNotEmpty()) {
            downloadAndDisplayPDF(pdfUrl)
        } else {
            Toast.makeText(this, "Invalid PDF URL", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadAndDisplayPDF(url: String) {
        val file = File(getExternalFilesDir(null), fileName)

        // If file already exists, directly display
        if (file.exists()) {
            displayPDF(file)
        } else {
            val request = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle("Downloading PDF")
                setDescription("Please wait...")
                setDestinationUri(Uri.fromFile(file))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            }

            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = downloadManager.enqueue(request)

            // Monitor the download in a background thread
            Thread {
                var downloading = true
                while (downloading) {
                    val query = DownloadManager.Query().setFilterById(downloadId)
                    val cursor = downloadManager.query(query)
                    if (cursor != null && cursor.moveToFirst()) {
                        val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        val status = cursor.getInt(statusIndex)
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            downloading = false
                            runOnUiThread { displayPDF(file) }
                        } else if (status == DownloadManager.STATUS_FAILED) {
                            downloading = false
                            runOnUiThread {
                                Toast.makeText(this, "Download failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    cursor?.close()
                    Thread.sleep(500)
                }
            }.start()
        }
    }

    private fun displayPDF(file: File) {
        try {
            val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val renderer = PdfRenderer(fileDescriptor)
            val page = renderer.openPage(0)

            val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

            binding.pdfImageView.setImageBitmap(bitmap)

            page.close()
            renderer.close()
            fileDescriptor.close()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error displaying PDF", Toast.LENGTH_SHORT).show()
        }
    }
}
