package duobroscreations.klufn.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import duobroscreations.klufn.R;

public class Mainmessage extends AppCompatActivity {
    TextView t1,t2;
    String msg1;
    Button i;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mainmessage);
            t1 = (TextView)findViewById(R.id.mainmsg);
            i=(Button) findViewById(R.id.link);
            db = openOrCreateDatabase("items", MODE_PRIVATE, null);
            db.execSQL("create table if not exists item7(id integer primary key autoincrement,note text[100],mainnote text[1000],link text[200]);");
            msg1 = getIntent().getExtras().getString("msg");
            String query = "SELECT * from item7 where note='" + msg1 + "'";
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst() == true) {
                //Toast.makeText(getApplicationContext(),c.getString(2),Toast.LENGTH_SHORT).show();
                t1.setText(c.getString(2));
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void link(View v) {
        try {
            String msg2 = getIntent().getExtras().getString("msg");
            String query = "SELECT * from item7 where note='" + msg2 + "'";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            Intent i = new Intent(Mainmessage.this, Browser.class);
            String linkl=c.getString(3);
            //Toast.makeText(getApplicationContext(),c.getString(3),Toast.LENGTH_SHORT).show();
            i.putExtra("website", linkl);
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
