package duobroscreations.klufn.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import duobroscreations.klufn.R;

public class Regrequest extends AppCompatActivity {
    String url= "http://klufn.96.lt/gcm/update_info.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_regrequest);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
 public void regis(View v) {
     try {
         ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
         if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
             Intent i = new Intent(Regrequest.this, Signup.class);
             startActivity(i);
         } else {
             Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
             finish();
             startActivity(getIntent());
         }
     }catch (Exception e)
     {
         Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
     }
 }
}
