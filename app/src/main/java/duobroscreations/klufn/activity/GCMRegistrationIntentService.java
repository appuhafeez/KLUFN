package duobroscreations.klufn.activity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import duobroscreations.klufn.R;

/**
 * Created by whoami on 7/23/2016.
 */
public class GCMRegistrationIntentService extends IntentService {
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";
    public static final String TAG = "GCMTOKEN";
    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            registerGCM();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void registerGCM() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("GCM", Context.MODE_PRIVATE);//Define shared reference file name
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //Registration complete intent initially null
            Intent registrationComplete = null;

            //Register token is also null
            //we will get the token on successfull registration
            String token = null;
            try {
                //Creating an instanceid
                InstanceID instanceID = InstanceID.getInstance(getApplicationContext());

                //Getting the token from the instance id
                token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                //Displaying the token in the log so that we can copy it to send push notification
                //You can also extend the app by storing the token in to your server
                Log.w("GCMRegIntentService", "token:" + token);

                //on registration complete creating intent with success
                registrationComplete = new Intent(REGISTRATION_SUCCESS);

                //Putting the token to the intent
                registrationComplete.putExtra("token", token);

                String oldToken = sharedPreferences.getString(TAG, "");//Return "" when error or key not exists
                //Only request to save token when token is new
                //if(!"".equals(token) && !oldToken.equals(token)) {
                saveTokenToServer(token);
                //Save new token to shared reference
                editor.putString(TAG, token);
                editor.commit();
            } catch (Exception e) {
                //If any error occurred
                Log.w("GCMRegIntentService", "Registration error");
                registrationComplete = new Intent(REGISTRATION_ERROR);
            }

            //Sending the broadcast that registration is completed
            LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void saveTokenToServer(String token) {
        try {
            //String id1="null";
            //String password="null";
            SQLiteDatabase db;
            db = openOrCreateDatabase("KLUFN", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS student(id varchar,email varchar,mobile varchar,year varchar,branch varchar)");
            String query = "SELECT * from student";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            String id = c.getString(0).toString();
            String email = c.getString(1);
            String mobile = c.getString(2);
            String year = c.getString(3);
            String branch = c.getString(4);
            Map paramPost = new HashMap();
            paramPost.put("action", "add");
            paramPost.put("tokenid", token);
            paramPost.put("id1", id);
            paramPost.put("email1", email);
            paramPost.put("mobile1", mobile);
            paramPost.put("year1", year);
            paramPost.put("branch1", branch);
           // paramPost.put("loginpass",password);
            try {
                String msgResult = getStringResultFromService_POST("http://klufn.96.lt/gcm/gcm1.php", paramPost);//our ip
                Log.w("ServiceResponseMsg", msgResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public String getStringResultFromService_POST(String serviceURL, Map<String, String> params) {
        HttpURLConnection cnn = null;
        String line = null;
        URL url;
        try {
            url = new URL(serviceURL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL invalid:" + serviceURL);
        }
        StringBuilder bodyBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        //Construct the post body using the parameter
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            bodyBuilder.append(param.getKey()).append('=').append(param.getValue());
            if (iterator.hasNext()) {
                bodyBuilder.append('&');
            }
        }
        String body = bodyBuilder.toString(); //format same to arg1=val1&arg2=val2
        Log.w("AccessService", "param:" + body);
        byte[] bytes = body.getBytes();
        try {
            cnn = (HttpURLConnection) url.openConnection();
            cnn.setDoOutput(true);
            cnn.setUseCaches(false);
            cnn.setFixedLengthStreamingMode(bytes.length);
            cnn.setRequestMethod("POST");
            cnn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //Post the request
            OutputStream outputStream = cnn.getOutputStream();
            outputStream.write(bytes);
            outputStream.close();

            //Handle the response
            int status = cnn.getResponseCode();
            if (status != 200) {
                throw new IOException("Post fail with error code:" + status);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cnn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
