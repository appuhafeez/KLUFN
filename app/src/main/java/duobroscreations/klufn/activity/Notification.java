package duobroscreations.klufn.activity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Notification extends ListActivity {
    String[] item_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_notification);
            SQLiteDatabase db = openOrCreateDatabase("items", MODE_PRIVATE, null);
            db.execSQL("create table if not exists item7(id integer primary key autoincrement,note text[100],mainnote text[1000],link text[200]);");
            Cursor c = db.rawQuery("select * from item7;", null);
            int count = c.getCount();
            int i;
            String[] item_menu = new String[count];
            if(count==0)
            {
                Toast.makeText(getApplicationContext(), "NO Notifications Available Right Now", Toast.LENGTH_SHORT).show();
            }
            for (i = 0; i < count; i++) {
                item_menu[i] = "empty";
            }
            for (i = 0; c.moveToNext(); i++) {
                item_menu[i] = c.getString(c.getColumnIndex("note"));
            }
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, item_menu));
            db.close();

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onListItemClick(final ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);
        //String mystr=item_menu[position];
        try{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("What do you want to do");
            builder.setPositiveButton("Know more", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        String jitem = l.getItemAtPosition(position).toString();
                        Intent i = new Intent(Notification.this, Mainmessage.class);
                        i.putExtra("msg", jitem);
                        startActivity(i);
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String jitem= l.getItemAtPosition(position).toString();
                    SQLiteDatabase db=openOrCreateDatabase("items",MODE_PRIVATE,null);
                    db.execSQL("create table if not exists item7(id integer primary key autoincrement,note text[100],mainnote text[1000],link text[200]);");
                    db.execSQL("delete from item7 where note=\""+jitem+"\";");
                    db.close();
                    finish();
                    startActivity(getIntent());
                }
            });
            AlertDialog b=builder.create();
            b.show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
