package duobroscreations.klufn.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import duobroscreations.klufn.R;
public class Signup extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6;
    Spinner sp4, sp5, sp3;
    SQLiteDatabase db;
    Button b;
    private static final String REGISTER_URL = "http://klufn.96.lt/gcm/register1.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
            e1 = (EditText) findViewById(R.id.id2);
            e2 = (EditText) findViewById(R.id.email);
           // e3 = (EditText) findViewById(R.id.phone);
            sp4 = (Spinner) findViewById(R.id.year);
            sp5 = (Spinner) findViewById(R.id.branch);
           // sp3 = (Spinner) findViewById(R.id.section);
            b = (Button) findViewById(R.id.signup);
            final String branch1[] = {"CSE", "ECE", "EEE", "MECH", "ECM", "BIO-TECH", "CIVIL", "PE"};
            final String year1[] = {"I/IV", "II/IV", "III/IV", "IV/IV"};
           // final String section1[] = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13", "s14", "s15","s16","s17","s18","s19","s20"};
            ArrayAdapter adapter2 = new ArrayAdapter(Signup.this, android.R.layout.simple_spinner_item, year1);
            adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            sp4.setAdapter(adapter2);
            ArrayAdapter adapter3 = new ArrayAdapter(Signup.this, android.R.layout.simple_spinner_item, branch1);
            adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            sp5.setAdapter(adapter3);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void signup(View v) {
        try {
            boolean a = true;
            if (e1.getText().length() == 0) {
                a = false;
                e1.setError("Id is Required");
            }
            if (e1.getText().length() > 0) {
                long id = Integer.parseInt(e1.getText().toString());
                if ((id >= 160030001 && id <= 160031552) || (id >= 160020001 && id <= 160020122) || (id >= 160010001 && id <= 160010085) || (id >= 160040001) && (id <= 160040994) || (id >= 160050001 && id <= 160050235) || (id >= 160060001 && id <= 160060139) || (id >= 160070001 && id <= 160070391) || (id >= 160080001 && id <= 160080010) || (id >= 150010001 && id <= 150010144) || (id >= 150020001 && id <= 150020175) || (id >= 150030001 && id <= 150031136) || (id >= 150040001 && id <= 150041026) || (id >= 150050001 && id <= 150050204) || (id >= 150060001 && id <= 150060108) || (id >= 150070001 && id <= 150070450) || (id >= 150080001 && id <= 150080034) || (id >= 14001001 && id <= 14001087) || (id >= 14002001 && id <= 14002178) || (id >= 14003001 && id <= 14003784) || (id >= 14004001 && id <= 14004645) || (id >= 14005001 && id <= 14005176) || (id >= 14006001 && id <= 14006188) || (id >= 14007001 && id <= 14007612) || (id >= 14008001 && id <= 14008079) && (id >= 13001001 && id <= 13001084) || (id >= 13002001 && id <= 13002206) || (id >= 13003001 && id <= 13003581) || (id >= 13004001 && id <= 13004569) || (id >= 13005001 && id <= 13005087) || (id >= 13006001 && id <= 13006187) || (id >= 13007001 && id <= 13007584)) {
                    // a=true;
                } else {
                    a = false;
                    e1.setError("id number is not valid");
                }

            }

            if (e2.getText().length() == 0) {
                a = false;
                e2.setError("Email is required");
            } else {
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                String email = e2.getText().toString();
                if (email.matches(emailPattern)) {
                    //a=true;
                } else {
                    a = false;
                    e2.setError("Invalid email");
                }
            }
            if (a == true) {
                long id1 = Integer.parseInt(e1.getText().toString());
                String id = e1.getText().toString();
                String email = e2.getText().toString();
                String num = "nill";
                String year = sp4.getSelectedItem().toString();
                String branch = sp5.getSelectedItem().toString();
                //String section = sp3.getSelectedItem().toString();
                register(id,email);
                Intent intent = new Intent(Signup.this, Emailcheck.class);
                intent.putExtra("id",id);
                intent.putExtra("email",email);
                intent.putExtra("num",num);
                intent.putExtra("year",year);
                intent.putExtra("branch",branch);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void register(String num, String ieee) {
        String urlSuffix = "?id1="+num+"&email1="+ieee;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Signup.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                    //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


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
