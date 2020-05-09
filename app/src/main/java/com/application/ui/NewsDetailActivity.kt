package com.application.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.application.covid_19.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val image=intent.getStringExtra("image")
        val date=intent.getStringExtra("date")
        val source=intent.getStringExtra("source")
        val description=intent.getStringExtra("description")


        Picasso.get().load(image).into(iv_news_details)
        date_news_details.text=date
        tv_source_news_details.text=source
        tv_description_news_details.text=description

//
//        var url=intent.getStringExtra("url")
//        webView = findViewById(R.id.newsDetailWebView)
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                view?.loadUrl(url)
//                return true
//            }
//        }
//        webView.loadUrl(url)
    }
}