package com.jangisaac.alwaystest

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getBitmap(
//            "https://academy.avast.com/hubfs/New_Avast_Academy/what_are_qr_codes_and_how_do_you_scan_them_academy/Academy-What-are-QR-Codes-and-how-do-you-scan-them-Thumb.jpg"
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTscfSvCW1VMM_PZHW_5noes801_GhgxVkKTQ&usqp=CAU"
        ) { resorce ->
            DLog.ee("RETURN:: " + (readQRImage(resorce) ?: "null"))
        }
    }

    fun readQRImage(bMap: Bitmap): String? {
        var contents: String? = null
        val intArray = IntArray(bMap.width * bMap.height)
        //copy pixel data from the Bitmap into the 'intArray' array
        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val reader: Reader = MultiFormatReader() // use this otherwise ChecksumException
        try {
            val result = reader.decode(bitmap)
            contents = result.text
            //byte[] rawBytes = result.getRawBytes();
            //BarcodeFormat format = result.getBarcodeFormat();
            //ResultPoint[] points = result.getResultPoints();
        } catch (e: NotFoundException) {
            e.printStackTrace()
        } catch (e: ChecksumException) {
            e.printStackTrace()
        } catch (e: FormatException) {
            e.printStackTrace()
        }
        return contents
    }

    fun getBitmap(url: String, completion: (bitmap: Bitmap)->Unit) {
        DLog.ee()
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    DLog.ee()
                    completion.invoke(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                    DLog.ee()
                }
            })
    }
    
//     @SuppressLint("SetJavaScriptEnabled")
//     private void initWebView() {
//         mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//         mWebView.getSettings().setAppCacheEnabled(true);
//         mWebView.getSettings().setAppCachePath(getCacheDir().getPath());
//         mWebView.getSettings().setJavaScriptEnabled(true);
//         mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//         mWebView.getSettings().setSupportMultipleWindows(true);
//         mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//         mWebView.getSettings().setBuiltInZoomControls(true);
//         mWebView.getSettings().setSupportZoom(true);
//         mWebView.getSettings().setDisplayZoomControls(false);
//         mWebView.getSettings().setDomStorageEnabled(true);

//         mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

//         mWebView.setHorizontalScrollBarEnabled(false);
//         mWebView.setVerticalScrollBarEnabled(true);
//         mWebView.addJavascriptInterface(mJavaScriptInterface, "custommeapp");
//         mWebView.addJavascriptInterface(mJavaScriptInterfaceForChat, "AppJSInterface");
//         io.imqa.mpm.network.webview.WebviewInterface imqaJavscript = new io.imqa.mpm.network.webview.WebviewInterface();
//         mWebView.addJavascriptInterface(imqaJavscript, "ImqaBridge");

//         mWebView.setWebViewClient(mWebViewClient);
//         mWebView.setWebChromeClient(mWebChromeClient);


//         String checkUrl = CmConstant.getServerUrl() + CmConstant.URL_MAIN_PAGE;
//         CmLog.w("CmConstant.ONELINK_TARGET_URL =" + CmConstant.ONELINK_TARGET_URL);
//         if(CmConstant.ONELINK_TARGET_URL != "") {
//             checkUrl = CmConstant.ONELINK_TARGET_URL;
//             CmConstant.ONELINK_TARGET_URL = "";
//         }

//         String loadMainUrl = checkUrl;

//         // 앱 실행시 자동로그인 체크
//         if (isFirst && !checkRememberMe(loadMainUrl) && CmConstant.IS_FRIST_MAIN) {
//             CookieManager.getInstance().removeSessionCookies(new ValueCallback<Boolean>() {
//                 @Override
//                 public void onReceiveValue(Boolean aBoolean) {
//                     loadUrl(loadMainUrl);
//                 }
//             });
//             isFirst = false;

//         } else {
//             loadUrl(loadMainUrl);
//         }

//         if(CmConstant.IS_FRIST_MAIN) {
//             CmConstant.IS_FRIST_MAIN = false;
//         }

//         String userAgentString = CmUtil.setUserAgent(mWebView.getSettings().getUserAgentString());
//         CmLog.d(TAG, "userAgentString [" + userAgentString + "]");
//         mWebView.getSettings().setUserAgentString(userAgentString);

//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//             mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//         }

//         // Leo Tagging
//         AmoreTracker.setupWebView(mWebView);

//         if (CmConstant.useDebugLog) {
//             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                 mWebView.setWebContentsDebuggingEnabled(true);
//             }
//         }

//         CookieManager cookieManager = CookieManager.getInstance();
//         cookieManager.setAcceptCookie(true);
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//             cookieManager.setAcceptThirdPartyCookies(mWebView, true);
//         }


//     }
}

// public class CmJavaScriptInterface {

//     private String TAG = CmJavaScriptInterface.class.getSimpleName();
//     private final Activity mActivity;

//     public CmJavaScriptInterface(Activity activity) {
//         mActivity = activity;
//     }

//     @JavascriptInterface
//     public void launchCamera() {
//         ((MainActivity)mActivity).launchCamera();
//     }
// }
