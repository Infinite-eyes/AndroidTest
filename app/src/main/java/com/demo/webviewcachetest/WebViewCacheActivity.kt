package com.demo.webviewcachetest

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient

class WebViewCacheActivity : AppCompatActivity() {


    protected var mAgentWeb: AgentWeb? = null
    private var mLinearLayout: LinearLayout? = null
    private var mAlertDialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_cache_activity)


        WebView.setWebContentsDebuggingEnabled(true);

        mLinearLayout = findViewById(R.id.container) as LinearLayout
//
        val p = System.currentTimeMillis()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mLinearLayout!!, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK) //打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() //拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready()
            .go(getUrl())

//        mAgentWeb?.getUrlLoader()?.loadUrl(getUrl());
        val n = System.currentTimeMillis()
        Log.i("Info", "init used time:" + (n - p))
    }

    private val mWebViewClient: WebViewClient =
        object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
//                Log.i("Info", "BaseWebActivity onPageStarted")
            }

            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)
            }


            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
            }


        }

    private val mWebChromeClient: WebChromeClient =
        object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
            }

        }

    fun getUrl(): String? {
//        mAgentWeb?.agentWebSettings?.webSettings?.cacheMode = WebSettings.LOAD_NO_CACHE
        mAgentWeb?.agentWebSettings?.webSettings?.cacheMode = WebSettings.LOAD_CACHE_ONLY
//        return "https://m.jd.com/"
        return "https://mdetail.tmall.com/templates/pages/desc?id=534123490427"
    }


    private fun showDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = AlertDialog.Builder(this)
                .setMessage("您确定要关闭该页面吗?")
                .setNegativeButton("再逛逛",
                    DialogInterface.OnClickListener { dialog, which ->
                        if (mAlertDialog != null) {
                            mAlertDialog?.dismiss()
                        }
                    }) //
                .setPositiveButton("确定",
                    DialogInterface.OnClickListener { dialog, which ->
                        if (mAlertDialog != null) {
                            mAlertDialog?.dismiss()
                        }
                        this@WebViewCacheActivity.finish()
                    }).create()
        }
        mAlertDialog!!.show()
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        return if (mAgentWeb!!.handleKeyEvent(keyCode, event)) {
//            true
//        } else super.onKeyDown(keyCode, event)
//    }

    override fun onPause() {
        super.onPause()
        mAgentWeb?.webLifeCycle?.onPause()
    }

    override fun onResume() {
        super.onResume()
        mAgentWeb?.webLifeCycle?.onResume()

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        Log.i("Info", "onResult:$requestCode onResult:$resultCode")
        super.onActivityResult(requestCode, resultCode, data)
    }

    //
//
    override fun onDestroy() {
        super.onDestroy()
        mAgentWeb?.webLifeCycle?.onDestroy()
    }


}
