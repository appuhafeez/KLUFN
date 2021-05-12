package duobroscreations.klufn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import duobroscreations.klufn.R;
public class Branch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
    }
    public void cse(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV") ) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://nvrr.96.lt/klufn/2nd%20year.html");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://nveerraghavareddy.wixsite.com/klu14");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.klu13.in/p/cse-7th-sem.html");
            startActivity(i);
        }
    }public void ece(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/p/3rd-year-subjects.html#");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/p/3rd-year-subjects.html#");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/p/3rd-year-subjects.html#");
            startActivity(i);
        }
    }public void ecm(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/p/3rd-year-subjects.html#");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/p/3rd-year-subjects.html#");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/p/3rd-year-subjects.html#");
            startActivity(i);
        }
    }public void eee(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.indianshout.com/electrical-engineering-eee-full-study-material-by-iit-professors/2180");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.indianshout.com/electrical-engineering-eee-full-study-material-by-iit-professors/2180");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.indianshout.com/electrical-engineering-eee-full-study-material-by-iit-professors/2180");
            startActivity(i);
        }
    }public void mech(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://bookboon.com/en/mechanics-ebooks");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://bookboon.com/en/mechanics-ebooks");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://bookboon.com/en/mechanics-ebooks");
            startActivity(i);
        }
    }public void civil(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/search/label/CIVIL");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/search/label/CIVIL");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "https://www.klu13.in/search/label/CIVIL");
            startActivity(i);
        }
    }public void bt(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.ncbi.nlm.nih.gov/");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.ncbi.nlm.nih.gov/");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.ncbi.nlm.nih.gov/");
            startActivity(i);
        }
    }public void pe(View v){
        String year=getIntent().getExtras().getString("year");
        if (year.equals("I/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://www.kluniversity15.in/test1.html");
            startActivity(i);
        } else if (year.equals("II/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://bookboon.com/en/petroleum-engineering-ebooks");
            startActivity(i);
        } else if (year.equals("III/IV")) {
            Intent i = new Intent(Branch.this, Browser.class);
            i.putExtra("website", "http://bookboon.com/en/petroleum-engineering-ebooks");
            startActivity(i);
        } else if (year.equals("IV/IV")) {
            Toast.makeText(getApplicationContext(), "Your year doesn't has this branch", Toast.LENGTH_SHORT).show();
        }
    }

}
