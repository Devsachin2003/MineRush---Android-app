package com.example.minerush.User

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.api.ApiService
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityNewPdfViewrBinding
import com.joanzapata.pdfview.PDFView
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class NewPdfViewrActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPdfViewrBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewPdfViewrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fileName = intent.getStringExtra("pdfUrl") ?: ""
        val fullPath = "static/Articles/rules and acts/$fileName"
        val encodedPath = fileName.replace(" ", "%20")
        downloadPdf(encodedPath)

    }

    private fun downloadPdf(fileUrl: String) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<ResponseBody?> = service.downloadPdf(fileUrl)

        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful && response.body() != null) {
                    val written = writeResponseBodyToDisk(response.body()!!)
                    if (written) {
                        try {
                            val pdfFile = File("${getExternalFilesDir(null)}/MyApp/downloaded_pdf.pdf")
                            if (pdfFile.exists()) {
                                val pdfView: PDFView = binding.pdfView
                                pdfView.fromFile(pdfFile).load()
                            } else {
                                Toast.makeText(context, "File Not Found", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(context, "Failed to load PDF", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Failed to save PDF", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Server error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "Download failed: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("DownloadPdf", "Error: ", t)
            }
        })
    }

    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
            val pdfDir = File(getExternalFilesDir(null), "MyApp")
            if (!pdfDir.exists()) pdfDir.mkdirs()

            val pdfFile = File(pdfDir, "downloaded_pdf.pdf")

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(50096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(pdfFile)

                while (true) {
                    val read = inputStream.read(fileReader)
                    if (read == -1) break
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read
                    Log.d("DownloadPdf", "Downloaded $fileSizeDownloaded of $fileSize")
                }

                outputStream.flush()
                true
            } catch (e: IOException) {
                Log.e("DownloadPdf", "Error writing PDF to disk", e)
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            Log.e("DownloadPdf", "Error preparing file", e)
            false
        }
    }
}
