package com.example.minerush.User

import android.content.Context
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

    private lateinit var binding:ActivityNewPdfViewrBinding
    lateinit var context:Context

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
        val pdfUrl = intent.getStringExtra("pdfUrl") ?: ""
        downloadPdf(pdfUrl)
    }

    fun downloadPdf(fileUrl: String?) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        //        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://f08c-14-139-187-225.ngrok-free.app/")
        val retrofit = Retrofit.Builder().baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<ResponseBody?> = service.downloadPdf("static/Articles/rules%20and%20acts/theminesact1952.pdf")
        call.enqueue(object : Callback<ResponseBody?> {
           override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful()) {
                    val written = writeResponseBodyToDisk(response.body()!!)
                    if (written) {
                        // Load the PDF
                        try {
                            val pdfFile: File =
                                File("${getExternalFilesDir(null)}/MyApp/downloaded_pdf.pdf")
                            if (pdfFile.exists()) {
                                val pdfView: PDFView = binding.pdfView
                                pdfView.fromFile(pdfFile).load()
                            } else {
                                Toast.makeText(context, "File Not Found", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(context, "Failed Load PDF", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Failed Load PDF", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Server returned error: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

           override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "Download failed: " + t.message, Toast.LENGTH_SHORT).show()
                Log.e("DownloadPdf", "Error: ", t)
            }
        })
    }

    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        try {
            val pdfDir: File = File(getExternalFilesDir(null), "MyApp")
            if (!pdfDir.exists()) {
                pdfDir.mkdirs()
            }

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
                    if (read == -1) {
                        break
                    }

                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()

                    Log.d("DownloadPdf", "Downloaded $fileSizeDownloaded of $fileSize")
                }

                outputStream.flush()
                return true
            } catch (e: IOException) {
                Log.e("DownloadPdf", "Error writing PDF to disk", e)
                return false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            Log.e("DownloadPdf", "Error writing PDF to disk", e)
            return false
        }
    }


}