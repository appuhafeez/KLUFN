package duobroscreations.klufn.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import duobroscreations.klufn.R;

public class Emailcheck extends AppCompatActivity implements View.OnClickListener{
SQLiteDatabase db;
    private Button buttonLogin;
    private static final String LOGIN_URL = "http://klufn.96.lt/gcm/login.php";
    //private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_emailcheck);
            buttonLogin = (Button) findViewById(R.id.verify);

            buttonLogin.setOnClickListener(this);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void login()
    {
        try{
        String id=getIntent().getExtras().getString("id");
        //long id1 = Integer.parseInt(id);
        String email=getIntent().getExtras().getString("email");
        String num=getIntent().getExtras().getString("num");
        String year=getIntent().getExtras().getString("year");
        String branch=getIntent().getExtras().getString("branch");
       // String section=getIntent().getExtras().getString("section");
        //String who=getIntent().getExtras().getString("who");
        SQLiteDatabase db;
        db = openOrCreateDatabase("KLUFN", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(id varchar,email varchar,mobile varchar,year varchar,branch varchar)");
        String query="INSERT into student VALUES('"+id+"','"+email+"','"+num+"','"+year+"','"+branch+"')";
        db.execSQL(query);
        String status="true";
        userLogin(email,status);
    }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void userLogin(final String username, final String password) {
        try {
            class UserLoginClass extends AsyncTask<String, Void, String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(Emailcheck.this, "Please Wait", null, true, true);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equalsIgnoreCase("success")) {
                        Intent intent = new Intent(Emailcheck.this, MainActivity123.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Emailcheck.this, s, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                protected String doInBackground(String... params) {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("username", params[0]);
                    data.put("password", params[1]);

                    RegisterUserClass ruc = new RegisterUserClass();

                    String result = ruc.sendPostRequest(LOGIN_URL, data);

                    return result;
                }
            }
            UserLoginClass ulc = new UserLoginClass();
            ulc.execute(username, password);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            ;
            if (v == buttonLogin) {
                login();
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
