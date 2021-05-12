package duobroscreations.klufn.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import duobroscreations.klufn.R;

public class Feedback extends AppCompatActivity {
TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        t1=(TextView)findViewById(R.id.feedback);
    }
    public void feedback(View v)
    {
        SQLiteDatabase db;
        db = openOrCreateDatabase("KLUFN", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(id varchar,email varchar,mobile varchar,year varchar,branch varchar)");
        String query = "SELECT * from student";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String id=c.getString(0).toString();
        String email=c.getString(1);
        String aEmailList[] = { "duobroscreations@gmail.com" };
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, aEmailList);
        Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        Email.putExtra(Intent.EXTRA_TEXT, t1.getText());
        try {
        startActivity(Email);
    } catch (android.content.ActivityNotFoundException ex) {
        Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    }
        finish();

    }
}
