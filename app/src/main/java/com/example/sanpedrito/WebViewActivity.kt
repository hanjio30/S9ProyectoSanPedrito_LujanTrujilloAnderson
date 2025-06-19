package com.example.sanpedrito

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.view.View
import android.webkit.WebChromeClient

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        // Configurar ActionBar
        supportActionBar?.title = "Página Web UNS"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Configurar colores
        window.statusBarColor = ContextCompat.getColor(this, R.color.uns_red)

        initViews()
        setupWebView()
        loadWebPage()
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupWebView() {
        // Configurar WebView
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = true
            displayZoomControls = false
        }

        // WebViewClient para manejar la navegación
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                // Manejar URLs especiales de Facebook y otras apps
                return if (url != null && (url.startsWith("intent://") ||
                            url.startsWith("fb://") ||
                            url.startsWith("market://") ||
                            url.startsWith("mailto:"))) {
                    // Para URLs de apps nativas, cargar la versión web
                    if (url.contains("facebook.com") || url.startsWith("fb://")) {
                        view?.loadUrl("https://www.facebook.com/sanpedritochimbote/?locale=es_LA")
                    }
                    true
                } else {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    false
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }

        // WebChromeClient para manejar la barra de progreso
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                } else {
                    progressBar.visibility = View.GONE
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                supportActionBar?.title = title ?: "Página Web UNS"
            }
        }
    }

    private fun loadWebPage() {
        val url = "https://www.facebook.com/sanpedritochimbote/?locale=es_LA"

        // Configurar User-Agent para evitar redirecciones a la app
        webView.settings.userAgentString = "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36"

        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}