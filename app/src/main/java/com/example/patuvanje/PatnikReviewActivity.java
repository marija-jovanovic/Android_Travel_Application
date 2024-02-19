package com.example.patuvanje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PatnikReviewActivity extends AppCompatActivity {

    public String korisnik;
    private String ajdi;

    private String komentar;

    private String vremek;

    private String datum;

    public static int zname=0;

    private SQLiteDatabase db;
    public static final int ID_NOTIFICATION_DL_COMPLETE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patnik_review);

        Intent intent = getIntent();
        korisnik = intent.getExtras().getString("korisnik");
        ajdi = intent.getExtras().getString("id");

        Toast.makeText(this, korisnik, Toast.LENGTH_SHORT).show();

        db = openOrCreateDatabase("mojproekt",MODE_PRIVATE,null);
        String CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS review" + " ("
                +"ID INTEGER PRIMARY KEY," + "VOZENJA_ID VARCHAR," + "KORISNIK VARCHAR," + "KOMENTAR VARCHAR," + "OCENA VARCHAR" + ")";
        db.execSQL(CREATE_TABLE_REVIEW);


    }

    public void naPotvrda(View view) {

        String date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        Calendar now = Calendar.getInstance();

        // Get the current hour and minute as parameters
        String cas = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);

        Cursor cursor = db.rawQuery("SELECT * FROM vozenja WHERE ID = '" + Integer.parseInt(ajdi) + "'", null);
        cursor.moveToFirst();
        do {
            datum = cursor.getString(2);
            vremek = cursor.getString(4);

            String[] arrOfStr1 =  date.split("/");
            String[] arrOfStr2 = datum.split("/");
            String[] cas1 = cas.split(":");
            String[] cas2 = vremek.split(":");

            if((Integer.parseInt(arrOfStr1[0]) >= Integer.parseInt(arrOfStr2[0])) &&
                    (Integer.parseInt(arrOfStr1[1]) >= Integer.parseInt(arrOfStr2[1])) &&
                    (Integer.parseInt(arrOfStr1[2]) >= Integer.parseInt(arrOfStr2[2]))){
                //ako sega sme ist ili pogolem mesec od patuvanjeto
                if((Integer.parseInt(arrOfStr1[0]) == Integer.parseInt(arrOfStr2[0])) &&
                        (Integer.parseInt(arrOfStr1[1]) == Integer.parseInt(arrOfStr2[1])) &&
                        (Integer.parseInt(arrOfStr1[2]) == Integer.parseInt(arrOfStr2[2])) &&
                        (Integer.parseInt(cas1[0]) <= (Integer.parseInt(cas2[0]))) &&
                        (Integer.parseInt(cas1[1]) < (Integer.parseInt(cas2[1])))){
                    //nisto ne treba da prikaze
                    Toast.makeText(this, "You can review the trip only when it has finished!", Toast.LENGTH_SHORT).show();
                }

                else if((Integer.parseInt(arrOfStr1[0]) == Integer.parseInt(arrOfStr2[0])) &&
                        (Integer.parseInt(arrOfStr1[1]) == Integer.parseInt(arrOfStr2[1])) &&
                        (Integer.parseInt(arrOfStr1[2]) == Integer.parseInt(arrOfStr2[2])) &&
                        (Integer.parseInt(cas1[0]) < (Integer.parseInt(cas2[0])))){
                    //nisto ne treba da prikaze
                    Toast.makeText(this, "You can review the trip only when it has finished!", Toast.LENGTH_SHORT).show();
                }


                else{
                    EditText et = (EditText) findViewById(R.id.komentar);
                    RatingBar rb = (RatingBar) findViewById(R.id.ocena);
                    String rating = String.valueOf(rb.getRating());
                    komentar = et.getText().toString();
                    if(!komentar.isEmpty() && !rating.isEmpty()) {
                        ContentValues insertValues = new ContentValues();
                        insertValues.put("VOZENJA_ID", ajdi);
                        insertValues.put("OCENA", rating);
                        insertValues.put("KOMENTAR", komentar);
                        insertValues.put("KORISNIK", korisnik);

                        Cursor kursor = db.rawQuery("SELECT * FROM " + "review" + " WHERE " + "VOZENJA_ID" + "='" + ajdi + "'"
                                + " AND " + "KORISNIK" + "='" + korisnik + "'", null);
                        if(kursor.moveToFirst()) {
                            do {
                                Toast.makeText(this, "You have already reviewed this trip!", Toast.LENGTH_SHORT).show();
                                finish();
                            } while (kursor.moveToNext());
                        }

                        else{
                            db.insert("review", null, insertValues);
                            Toast.makeText(this, "Successfully sent.", Toast.LENGTH_SHORT).show();

                            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            String CHANNEL_ID = "my_channel_01";
                            CharSequence name = "my_channel";
                            String Description = "This is my channel";

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                int importance = NotificationManager.IMPORTANCE_LOW;
                                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                                mChannel.setDescription(Description);
                                //mChannel.enableLights(true);
                                //mChannel.setLightColor(Color.RED);
                                //mChannel.enableVibration(true);
                                //mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                                //mChannel.setShowBadge(true);
                                manager.createNotificationChannel(mChannel);
                            }

                            // прикажување нотификација
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(PatnikReviewActivity.this, CHANNEL_ID)
                                    .setContentTitle("Review sent.")
                                    .setContentText("Thank you for your time, the review was successfully sent!")
                                    .setAutoCancel(true)
                                    .setSmallIcon(R.drawable.baseline_notifications_active_24);
                            Notification notification = builder.build();

                            manager.notify(ID_NOTIFICATION_DL_COMPLETE, notification);



                            zname = 1;
                        }
                        kursor.close();

                      //  db.insert("review", null, insertValues);
                        //Toast.makeText(this, "Successfully sent.", Toast.LENGTH_SHORT).show();
                        //zname = 1;


                        finish();
                    }
                    else{
                        Toast.makeText(this, "Rating and comment is mandatory!", Toast.LENGTH_SHORT).show();
                    }
                }



                     }
            else{
                Toast.makeText(this, "You can review the trip only when it has finished!", Toast.LENGTH_SHORT).show();
            }
        } while (cursor.moveToNext());
        cursor.close();


    }
}
