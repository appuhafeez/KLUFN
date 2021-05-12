package duobroscreations.klufn.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.Random;

import duobroscreations.klufn.R;

/**
 * Created by whoami on 7/23/2016.
 */
public class GCMPushReceiverService extends GcmListenerService{
    //This method will be called on every new message received
    @Override
    public void onMessageReceived(String from, Bundle data) {
        try {
            //Getting the message from the bundle
            String message = data.getString("message");
            String desc = data.getString("desc");
            String link = data.getString("link");
            SQLiteDatabase db = openOrCreateDatabase("items", MODE_PRIVATE, null);
            db.execSQL("create table if not exists item7(id integer primary key autoincrement,note text[100],mainnote text[1000],link text[200]);");
            db.execSQL("insert into item7(note,mainnote,link) values('" + message + "','" + desc + "','"+link+"');");
            db.close();
            //Displaying a notiffication with the message
            sendNotification(message);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    //This method is generating a notification and displaying the notification
    private void sendNotification(String message) {
        try {
            Intent intent = new Intent(this, Notification.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int requestCode = 0;
            Random rand = new Random();
            int n = rand.nextInt(50) + 1;
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(bm)
                    .setContentText(message)
                    .setContentTitle("KLUfn")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
