package devsbox.crazy2048;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView myWebView = (WebView) findViewById(R.id.GameWebView);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            //

            myWebView.loadUrl("http://quizee.club/2048/");

            myWebView.setWebViewClient(new MyWebViewClient());

            WebSettings webSettings = myWebView.getSettings();

            webSettings.setJavaScriptEnabled(true);
        }else {
            //
            pop();
            mDialog.show();
        }

    }



    // Use When the user clicks a link from a web page in your WebView
    private class MyWebViewClient extends WebViewClient {

        @Override

        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (Uri.parse(url).getHost().equals("http://quizee.club/2048/")) {

                return false;

            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            startActivity(intent);

            return true;
        }
    }

    ////
    public void pop() {

        mDialog = new Dialog(MainActivity.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.pop_up_window);

        /*firstcall = (ImageView) mDialog.findViewById(R.id.firstcallimageView);
        secondcall = (ImageView) mDialog.findViewById(R.id.secondcallimageView);
        thirdcall = (ImageView) mDialog.findViewById(R.id.thirdcallimageView);*/


    }
}