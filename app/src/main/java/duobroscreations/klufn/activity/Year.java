package duobroscreations.klufn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import duobroscreations.klufn.R;
public class Year extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
    }
    public  void first(View v)
    {
        Intent i =new Intent(Year.this,Branch.class);
        i.putExtra("year","I/IV");
        startActivity(i);
    }
    public  void third(View v)
    {
        Intent i =new Intent(Year.this,Branch.class);
        i.putExtra("year","III/IV");
        startActivity(i);
    }
    public  void second(View v)
    {
        Intent i =new Intent(Year.this,Branch.class);
        i.putExtra("year","II/IV");
        startActivity(i);
    }
    public void finall(View v)
    {
        Intent i =new Intent(Year.this,Branch.class);
        i.putExtra("year","IV/IV");
        startActivity(i);
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
