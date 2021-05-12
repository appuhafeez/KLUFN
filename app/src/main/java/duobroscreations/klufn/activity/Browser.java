package duobroscreations.klufn.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import duobroscreations.klufn.R;

public class Browser extends Activity {
    WebView wv;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browser);
            wv = (WebView) findViewById(R.id.webView);
            spinner = (ProgressBar)findViewById(R.id.progressBar);
            String url = getIntent().getExtras().getString("website");
            wv.loadUrl(url);
            wv.setWebViewClient(new WebViewClient());
            wv.getSettings().setBuiltInZoomControls(true);
            wv.getSettings().setSupportZoom(true);
            wv.getSettings().setAllowContentAccess(true);
            wv.getSettings().setAllowFileAccessFromFileURLs(true);
            wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
            wv.getSettings().setAppCacheEnabled(true);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.setVerticalScrollBarEnabled(false);
            wv.setHorizontalScrollBarEnabled(false);
            wv.getSettings().setLoadsImagesAutomatically(true);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });

            if(url.equals("http://49.156.157.75:8084/klu_new/"))
            {
                SQLiteDatabase db;
                db = openOrCreateDatabase("ERP", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS login(id varchar,pass varchar)");
                String q="SELECT * FROM login";
                Cursor c=db.rawQuery(q,null);
                if(c.moveToFirst()==true){
                   final String id=c.getString(0);
                    final String pass=c.getString(1);
                    final Long id1=Long.parseLong(id);
                    wv.setWebViewClient(new WebViewClient());
                    wv.getSettings().setBuiltInZoomControls(true);
                    wv.getSettings().setSupportZoom(true);
                    wv.getSettings().setAllowContentAccess(true);
                    wv.getSettings().setAllowFileAccessFromFileURLs(true);
                    wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
                    wv.getSettings().setAppCacheEnabled(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setVerticalScrollBarEnabled(false);
                    wv.setHorizontalScrollBarEnabled(false);
                    wv.getSettings().setLoadsImagesAutomatically(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.loadUrl(url);
                    wv.setDownloadListener(new DownloadListener() {
                        @Override
                        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
                           /* wv.loadUrl("javascript: {" +
                                    "document.getElementById('userId').value = '"+id1 +"';" +
                                    "document.getElementById('password1').value = '"+pass+"';" +
                                    "var frms = document.getElementsByName('Login');" +
                                    "frms[0].checkLogin(); };");*/
                            wv.loadUrl("javascript:document.getElementById('userId').value = '" + id1 + "';javascript:document.getElementById('password1').value = '" + pass + "';javascript:document.getElementById('Login').click();");
                            Toast.makeText(getApplicationContext(),"Please wait......",Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else{
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView v, String url) {
                            wv.loadUrl(url);
                            return true;
                        }
                    });
                }
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
        class WebViewClient extends android.webkit.WebViewClient {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                try {
                    // TODO Auto-generated method stub
                    super.onPageStarted(view, url, favicon);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                spinner.setVisibility(View.GONE);
            }
        }
    }
    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            try {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectivityManager cm=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        boolean isConnected=activeNetwork!=null && activeNetwork.isConnectedOrConnecting();
        if(isConnected!=true){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Please check your internet Connection");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                }
            });
            builder.setNegativeButton("go back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog b=builder.create();
            b.show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
