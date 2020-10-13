package com.awra.stud.testtaskapp.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.fragment.app.Fragment

class WebViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return WebView(requireContext()).apply {
            CookieManager.getInstance().setAcceptCookie(true)
            settings.javaScriptEnabled = true
            settings.setAppCacheEnabled(true)
            webViewClient = AppWebViewClient()
            loadUrl("https://html5test.com/")

        }
    }
}
