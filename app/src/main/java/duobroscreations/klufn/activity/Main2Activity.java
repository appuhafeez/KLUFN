package duobroscreations.klufn.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import duobroscreations.klufn.R;

public class Main2Activity extends AppCompatActivity {
EditText e1,e2;
    Button b;
    private static final String REGISTER_URL = "http://kondreddy.96.lt/gcm/register.php";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e1=(EditText)findViewById(R.id.loginid);
        e2=(EditText)findViewById(R.id.loginpass);
        b=(Button)findViewById(R.id.loginsubmit);
    }
    public void login(View v){
        boolean a=true;
        if(e1.getText().length()<=0){
            a=false;
            e1.setError("Please enter id number");
        }
        if(e2.getText().length()<=0){
            a=false;
            e2.setError("Please enter password");
        }
        if(a==true){
            long id1 = Integer.parseInt(e1.getText().toString());
            String id=e1.getText().toString();
            String pass=e2.getText().toString();
            db = openOrCreateDatabase("ERP", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS login(id varchar,pass varchar)");
            String query="INSERT into login VALUES('"+id+"','"+pass+"')";
            db.execSQL(query);
            register(id,pass);
        }
    }
    private void register(String id, String pass) {
        String urlSuffix = "?id1="+id+"&pass1="+pass;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Main2Activity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("successfully registered"))
                {
                    Toast.makeText(getApplicationContext(),"Done Open erp now",Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(Main2Activity.this,MainActivity123.class);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}
