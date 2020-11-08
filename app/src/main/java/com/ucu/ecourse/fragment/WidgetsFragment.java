package com.ucu.ecourse.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ucu.ecourse.ABCommunication;
import com.ucu.ecourse.DatabaseHelper;
import com.ucu.ecourse.Dialog;
import com.ucu.ecourse.ListDataActivity;
import com.ucu.ecourse.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class WidgetsFragment extends Fragment implements DialogInterface.OnClickListener {

    private EditText et_main_3;
    private TextView tv_ad;
    private Button gotoUcu;
    private Button viewContacts;
    private Button addContacts;
    private FusedLocationProviderClient client;
    public String smsReciever;
    ProgressDialog dialog;


    String myLat, myLng;
    DatabaseHelper mDatabaseHelper;
    private String userInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.map, container, false);
        // et_main_3 = nestedScrollView.findViewById(R.id.et_main_3);


        client = LocationServices.getFusedLocationProviderClient(getActivity());
        getLocation();

        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, 1);
        nestedScrollView.findViewById(R.id.goto_ucu).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sendSMStoAll();



            }
        });







     /*   ad_view_widget = nestedScrollView.findViewById(R.id.ad_view_widget);
        card_ad_widget = nestedScrollView.findViewById(R.id.card_ad_widget);*/
        // tv_ad = nestedScrollView.findViewById(R.id.tv_ad);
        gotoUcu = nestedScrollView.findViewById(R.id.goto_ucu);
        viewContacts = nestedScrollView.findViewById(R.id.view_contacts);
        addContacts = nestedScrollView.findViewById(R.id.add_contacts);

        viewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getContext(), ABCommunication.class);
                startActivity(intent);*/

            }
        });

        addContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openDialog();
            }
        });

        mDatabaseHelper = new DatabaseHelper(getContext());

        return nestedScrollView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    public void openDialog() {

        DialogFragment dialog = new DialogFragment();
        //dialog.show(getSupportFragmentManager(),"dialog");
        dialog.show(getFragmentManager(), "dialog");
    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }

    /*public void showAd() {
        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
            if (!sharedPreferences.getBoolean("isDonated", false)) {
                AdRequest adRequest = new AdRequest.Builder().build();
                ad_view_widget.loadAd(adRequest);

                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                tv_ad.setVisibility(View.VISIBLE);
                tv_ad.startAnimation(animation);
                card_ad_widget.setVisibility(View.VISIBLE);
                card_ad_widget.startAnimation(animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    public void getLocation(){

        if (ActivityCompat.checkSelfPermission(getActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            return;
        }


        client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){

                    myLat = String.valueOf(location.getLatitude());
                    myLng = String.valueOf(location.getLongitude());

                    //  myLat.equals(String.valueOf(location.getLatitude()));
                    // myLng.equals(String.valueOf(location.getLongitude()));



                    Toast.makeText(getActivity(), myLat+" "+myLng, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void sendSMStoAll(){


        Cursor cursor = mDatabaseHelper.getData();
        if (cursor.moveToFirst())
        {
            do
            {

                String numbers = cursor.getString(1);//Column Number should be on 1st Position
                smsReciever = numbers;
                sendSmsEmergency(smsReciever);
            }while(cursor.moveToNext());
        }

        cursor.close();


    }


    private void sendSmsEmergency(String smsReciever) {


        dialog.setMessage("Sending Message. Please wait.... ");
        String message = " Help me! I'm in Danger! Im at http://maps.google.com/maps?q=loc:"+String.valueOf(myLat)+","+String.valueOf(myLng);
        dialog.show();
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        // String message = "I need help! Im at "+String.valueOf(myLat)+","+String.valueOf(myLat);

        PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(), 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(), 0,
                new Intent(DELIVERED), 0);


        //---when the SMS has been sent---

        getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();

                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Please check your load balance.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        dialog.dismiss();
                        Toast.makeText(getContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(smsReciever, null, message, sentPI, deliveredPI);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
