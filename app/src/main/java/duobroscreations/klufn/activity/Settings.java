package duobroscreations.klufn.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import duobroscreations.klufn.R;

public class Settings extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    Spinner sp1,sp2;
    SQLiteDatabase db,fb;
    Button b;
    private static final String REGISTER_URL = "http://klufn.96.lt/gcm/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            c1 = (CheckBox) findViewById(R.id.ieee);
            c2 = (CheckBox) findViewById(R.id.sports);
            c3 = (CheckBox) findViewById(R.id.kluso);
            c4 = (CheckBox) findViewById(R.id.sea);
            c5 = (CheckBox) findViewById(R.id.klug);
            c6 = (CheckBox) findViewById(R.id.focus);
            c7 = (CheckBox) findViewById(R.id.pulse);
            c8 = (CheckBox) findViewById(R.id.trusang);
            c9 = (CheckBox) findViewById(R.id.yantrik);
            //c10=(CheckBox)findViewById(R.id.beta);
            //sp1 = (Spinner) findViewById(R.id.reabranch);
            //sp2 = (Spinner) findViewById(R.id.research);
            b=(Button)findViewById(R.id.signup);
            db = openOrCreateDatabase("OTHERNOTIFICATION", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS other(ieee varchar,sports varchar,kluso varchar,sea varchar,klug varchar,focus varchar,pulse varchar,trusang varchar,yantrik varchar,name varchar)");
            String q="SELECT * FROM other";
            Cursor a= db.rawQuery(q,null);
            if(a.moveToFirst()==true){
                if(a.getString(0).equals("yes")){
                    c1.setChecked(true);
                }else {
                    c1.setChecked(false);
                }
                if(a.getString(1).equals("yes")){
                    c2.setChecked(true);
                }else {
                    c2.setChecked(false);
                }
                if(a.getString(2).equals("yes")){
                    c3.setChecked(true);
                }else {
                    c3.setChecked(false);
                }
                if(a.getString(3).equals("yes")){
                    c4.setChecked(true);
                }else {
                    c4.setChecked(false);
                }
                if(a.getString(4).equals("yes")){
                    c5.setChecked(true);
                }else {
                    c5.setChecked(false);
                }
                if(a.getString(5).equals("yes")){
                    c6.setChecked(true);
                }else {
                    c6.setChecked(false);
                }
                if(a.getString(6).equals("yes")){
                    c7.setChecked(true);
                }else {
                    c7.setChecked(false);
                }
                if(a.getString(7).equals("yes")){
                    c8.setChecked(true);
                }else {
                    c8.setChecked(false);
                }
                if(a.getString(8).equals("yes")){
                    c9.setChecked(true);
                }else {
                    c9.setChecked(false);
                }
            }
            /*
            final String cse[] = {"Software Engineering", "Networks Security and Forensics", "Knowledge Engineering", "Cloud Computing", "Internet of Things"};
            final String ece[] = {"Communication Systems", "Image, Speech and Signal Processing", "Micro Electronics"};
            final String ecm[] = {"Embedded Systems and Sensor Networks"};
            final String eee[] = {"Power Electronics", "Power Systems"};
            final String mech[] = {"Design and Manufacturing Engineering", "Thermal Engineering", "Robotics and Mechatronics"};
            final String bt[] = {"Genomics and Proteomics", "Bioprocess Technology"};
            final String ce[] = {"Structural Engineering", "Geotechnical and Transportation Engineering", "Environment, Water resources and Geo Space Technology"};
            final String branch1[] = {"CSE", "ECE", "EEE", "MECH", "ECM", "BIO-TECH", "CIVIL", "PE"};
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, branch1);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(arrayAdapter);
            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, cse);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }
                    if (position == 1) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, ece);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }
                    if (position == 2) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, ecm);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }
                    if (position == 3) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, eee);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }
                    if (position == 4) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, ce);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }
                    if (position == 5) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, bt);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }
                    if (position == 6) {
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Settings.this, android.R.layout.simple_spinner_item, mech);
                        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp2.setAdapter(arrayAdapter1);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void signup(View v) {
        try {
            String ieee = "no", pass="no", sports = "no", kluso = "no", sea = "no", klug = "no", focus = "no", pulse = "no", trusang = "no", yantrik = "no";

            if (c1.isChecked()) {
                ieee = "yes";
            }
            if (c2.isChecked()) {
                sports = "yes";
            }
            if (c3.isChecked()) {
                kluso = "yes";
            }
            if (c4.isChecked()) {
                sea = "yes";
            }
            if (c5.isChecked()) {
                klug = "yes";
            }
            if (c6.isChecked()) {
                focus = "yes";
            }
            if (c7.isChecked()) {
                pulse = "yes";
            }
            if (c8.isChecked()) {
                trusang = "yes";
            }
            if (c9.isChecked()) {
                yantrik = "yes";
            }

            String name = "nill";
            db = openOrCreateDatabase("OTHERNOTIFICATION", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS other(ieee varchar,sports varchar,kluso varchar,sea varchar,klug varchar,focus varchar,pulse varchar,trusang varchar,yantrik varchar,name varchar)");
            String query = "SELECT * from other";
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst() == true) {
                String some = c.getString(0);
                String many = c.getString(1);
                db.delete("other", "ieee=? and sports=?", new String[]{some, many});
            } else {
                db = openOrCreateDatabase("OTHERNOTIFICATION", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS other(ieee varchar,sports varchar,kluso varchar,sea varchar,klug varchar,focus varchar,pulse varchar,trusang varchar,yantrik varchar,name varchar)");
                String query1 = "INSERT into other values('" + ieee + "','" + sports + "','" + kluso + "','" + sea + "','" + klug + "','" + focus + "','" + pulse + "','" + trusang + "','" + yantrik + "','" + name + "')";
                db.execSQL(query1);
                db = openOrCreateDatabase("KLUFN", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS student(id varchar,email varchar,mobile varchar,year varchar,branch varchar,who varchar)");
                String query2 = "SELECT * from student";
                Cursor q = db.rawQuery(query2, null);
                q.moveToFirst();
                String id = q.getString(0).toString();

                register(id,ieee,sports,kluso,sea,klug,focus,pulse,trusang,yantrik,name);
            }

        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void register(String id, String ieee, String sports, String kluso, String sea, String klug, String focus, String pulse, String trusang, String yantrik, String name) {
        String urlSuffix = "?id1="+id+"&ieee1="+ieee+"&sports1="+sports+"&kluso1="+kluso+"&sea1="+sea+"&klug1="+klug+"&focus1="+focus+"&pulse1="+pulse+"&trusang1="+trusang+"&yantrik1="+yantrik+"&name1="+name;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Settings.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("successfully registered"))
                {
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                    Intent i =new Intent(Settings.this,MainActivity123.class);
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
