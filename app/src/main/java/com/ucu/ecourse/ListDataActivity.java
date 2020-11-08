package com.ucu.ecourse;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    private String selectedNumber;
    private int selectedID;
    DatabaseHelper mDatabaseHelper;
    private ArrayAdapter<String> adapter;
    private ListView mListView;
    ArrayList<String> arrayList;
    private ArrayList<UserNumbers> user;
    final ArrayList<String> listData = new ArrayList<>();
    private int itemID = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView =  findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);


        populateListView();

    }






    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();

        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter

        mListView.setAdapter(adapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String number = parent.getItemAtPosition(position).toString();
                final Cursor data = mDatabaseHelper.getItemID(number);


                new AlertDialog.Builder(ListDataActivity.this)
                        .setTitle("Delete Data")
                        .setMessage("Do you want to delete this contact?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                while(data.moveToNext()){
                                    itemID = data.getInt(0);
                                }
                                if(itemID>-1){
                                    mDatabaseHelper.deleteName(itemID,number);
                                    adapter.notifyDataSetChanged();
                                    listData.remove(position);
                                    adapter.notifyDataSetInvalidated();
                                    toastMessage("Data deleted successfully");
                                }


                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                return false;
            }
        });
        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                final String number = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + number);

                final Cursor data = mDatabaseHelper.getItemID(number); //get the id associated with that name


                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    final Dialog dialog = new Dialog(ListDataActivity.this);
                    dialog.setTitle("Input Box");
                    dialog.setContentView(R.layout.input_box);
                    final EditText editText = dialog.findViewById(R.id.txtinput);
                    editText.setText(number);
                    Button bt = dialog.findViewById(R.id.btdone);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String item = editText.getText().toString();
                            if(item.length() == 11){

                                mDatabaseHelper.updateName(item,itemID,number);


                                    //mDatabaseHelper.getData();
                                    //adapter.notifyDataSetChanged();
                                    //adapter.notifyDataSetInvalidated();

                                ArrayList<String> newItems = mDatabaseHelper.getAllTable();
                                adapter.clear();
                                adapter.addAll(newItems);
                                adapter.notifyDataSetChanged();
                                mDatabaseHelper.close();
                                toastMessage("Number updated succesfully");




                            }else{
                                toastMessage("You must enter a valid number");
                            }

                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    /*Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("number",number);
                    startActivity(editScreenIntent); */
                }



                else{
                    toastMessage("No ID associated with that name");
                }


            }
        });
    }



    public void showInputBox(String oldItem, final int index){
        final Dialog dialog = new Dialog(ListDataActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);
        TextView textMessage = findViewById(R.id.txtmessage);
        textMessage.setText("Update item");
        textMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText = dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);
        Button bt = dialog.findViewById(R.id.btdone);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedNumber);
                }else{
                    toastMessage("You must enter a name");
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });


    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }



    
}
