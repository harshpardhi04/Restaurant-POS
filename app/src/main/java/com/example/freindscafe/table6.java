package com.example.freindscafe;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.usb.UsbConnection;
import com.dantsu.escposprinter.exceptions.EscPosBarcodeException;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;
import com.dantsu.escposprinter.exceptions.EscPosEncodingException;
import com.dantsu.escposprinter.exceptions.EscPosParserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class table6 extends AppCompatActivity {


    KonfettiView konfettiView;
    Toolbar toolbar;
    TableLayout tableLayout;
    Button rmv, add;
    TextView money;
    TableRow tableRow34;
    AutoCompleteTextView autoCompleteTextView;
        @Override
    protected void onStart() {
        super.onStart();
            boolean hasRows = false;
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("my_db6", MODE_PRIVATE, null);
            String sql = "CREATE TABLE IF NOT EXISTS myfood6 ( id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, qty INTEGER, price REAL)";
            db.execSQL(sql);
        Cursor cursor=db.rawQuery("SELECT COUNT(*) FROM myfood6", null);
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    hasRows = true;
                    loaddata();
                }
            }else {
                Toast.makeText(this, "Welcome Yogesh", Toast.LENGTH_SHORT).show();
            }

    }

    private ArrayList<TableRow> loaddata() {
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("my_db6", MODE_PRIVATE, null);
        // Get the data
        Cursor cursor = db.rawQuery("SELECT * FROM myfood6", null);
        ArrayList<TableRow> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int qty = cursor.getInt(cursor.getColumnIndexOrThrow("qty"));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                double totalprice = price*qty;
                TableRow row = new TableRow(getApplicationContext());
                TextView nameView = new TextView(getApplicationContext());
                nameView.setText(name);
                nameView.setGravity(Gravity.START);
                nameView.setSingleLine(false);
                nameView.setMaxLines(1);
                nameView.setPadding(80, 0, 0, 0);

                TextView quantityView = new TextView(getApplicationContext());
                quantityView.setText(qty+ "");
                quantityView.setGravity(Gravity.CENTER);
                quantityView.setPadding(70, 0, 0, 0);
                quantityView.setMaxLines(1);

                TextView itemPriceView = new TextView(getApplicationContext());
                itemPriceView.setText(price + "");
                itemPriceView.setGravity(Gravity.CENTER);
                itemPriceView.setSingleLine(false);
                itemPriceView.setPadding(105, 0, 0, 0);
                itemPriceView.setMaxLines(1);
                row.addView(nameView);
                row.addView(quantityView);
                row.addView(itemPriceView);
                tableLayout.addView(row);
                double totalFoodPrice = 0;
                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                    TableRow rw = (TableRow) tableLayout.getChildAt(i);
                    TextView textView = (TextView) rw.getChildAt(2);
                    if (textView != null) {
                        String billprice = textView.getText().toString();
                        if (billprice.isEmpty() || billprice == null) {
                            continue;
                        }
                        totalFoodPrice += Double.parseDouble(billprice);
                        money.setText("Rs." + totalFoodPrice);

                    }
                }

        }
        db.close();

        // Return the data
        return rows;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        tableLayout = findViewById(R.id.tableLayout);
        toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("Table-6");
        toolbar.setTitleTextColor(Color.WHITE);
        money = findViewById(R.id.total);
        rmv = findViewById(R.id.remove);
        add = findViewById(R.id.addme);
        konfettiView=findViewById(R.id.konfettiView);

        ArrayList<String> arr=new ArrayList<>();
        arr.add("Red Souce Pasta");
        arr.add("White Souce Pasta");
        arr.add("Masala Maggi");
        arr.add("Veg Club Sandwich");
        arr.add("Veg Cheese Sandwich");
        arr.add("Creamy Frothy Coffee");
        arr.add("Mocha Frappuccino");
        arr.add("Ice Cream Cold Coffee");
        arr.add("Oreo Cold Coffee");
        arr.add("Chocolate Frappuccino");
        arr.add("Mango Milk Shake");
        arr.add("Oreo Milk Shake");
        arr.add("Apple Milk Shake");
        arr.add("Banana Milk Shake");
        arr.add("Chocolate Milk Shake");
        arr.add("Strawberry Milk Shake");
        arr.add("Kit Kat Milk Shake");
        arr.add("Adark Elaichi Chai");
        arr.add("Masala Chai");
        arr.add("Gud Chai");
        arr.add("Black Tea");
        arr.add("Chocolate Tea");
        arr.add("Lemon Tea");
        arr.add("Ross Lassi");
        arr.add("Kesar Pista Lassi");
        arr.add("Mango Lassi");
        arr.add("Plain Lassi");
        arr.add("Chocolate Lassi");
        arr.add("Banana Lassi");
        arr.add("Coldrink");
        arr.add("Black Coffee");
        arr.add("Classic Coffee");
        arr.add("Bournvita Milk");
        arr.add("Hot Chocolate Coffee");
        arr.add("Chilled Pani Bottle");
        arr.add("Ice Cream Roll");
        arr.add("Donuts");


                  //****table layout define
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

                private List<TableRow> savedRows;

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int position = item.getItemId();
                    if (position == R.id.print) {
                        UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
                        UsbDevice usbDevice = usbManager.getDeviceList().get(0);
                        UsbDeviceConnection usbDeviceConnection = usbManager.openDevice(usbDevice);
                        if(usbDeviceConnection==null)
                        {
                            Toast.makeText(table6.this, "Printer is Not Connected", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(table6.this, "Loading...", Toast.LENGTH_SHORT).show();

                            try {
                                EscPosPrinter printer = new EscPosPrinter(new UsbConnection(usbManager, usbDevice), 203, 48f, 32);
                                try {
                                    printer.printFormattedText("[C]Freinds Cafe[C]");
                                    printer.printFormattedText("Foodname, Food Quantity, and Price\n");
                                } catch (EscPosParserException e) {
                                    throw new RuntimeException(e);
                                } catch (EscPosEncodingException e) {
                                    throw new RuntimeException(e);
                                } catch (EscPosBarcodeException e) {
                                    throw new RuntimeException(e);
                                }
                                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                                    TableRow rw = (TableRow) tableLayout.getChildAt(i);
                                    TextView itemname = (TextView) rw.getChildAt(0);
                                    TextView itemqty = (TextView) rw.getChildAt(1);
                                    TextView itemprice = (TextView) rw.getChildAt(2);
                                    try {
                                        printer.printFormattedText(itemname + " " + itemqty + " " + itemprice);

                                    } catch (EscPosParserException e) {
                                        throw new RuntimeException(e);
                                    } catch (EscPosEncodingException e) {
                                        throw new RuntimeException(e);
                                    } catch (EscPosBarcodeException e) {
                                        throw new RuntimeException(e);
                                    }


                                }
                                double totalFoodPrice = 0;
                                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                                    TableRow rw = (TableRow) tableLayout.getChildAt(i);
                                    TextView textView = (TextView) rw.getChildAt(2);
                                    if (textView != null) {
                                        String billprice = textView.getText().toString();
                                        if (billprice.isEmpty() || billprice == null) {
                                            continue;
                                        }
                                        totalFoodPrice += Double.parseDouble(billprice);
                                        if (totalFoodPrice >= 500) {
                                            totalFoodPrice = totalFoodPrice - (totalFoodPrice * 0.05);
                                            try {
                                                printer.printFormattedText("Hurray You Got 5% discount");
                                                printer.printFormattedText("Total price:" + totalFoodPrice);
                                            } catch (EscPosParserException e) {
                                                throw new RuntimeException(e);
                                            } catch (EscPosEncodingException e) {
                                                throw new RuntimeException(e);
                                            } catch (EscPosBarcodeException e) {
                                                throw new RuntimeException(e);
                                            }

                                        } else if (totalFoodPrice >= 1000) {
                                            totalFoodPrice = totalFoodPrice - (totalFoodPrice * 0.01);
                                            try {
                                                printer.printFormattedText("Hurray You Got 10% discount");
                                                printer.printFormattedText("Total price:" + totalFoodPrice);
                                            } catch (EscPosParserException e) {
                                                throw new RuntimeException(e);
                                            } catch (EscPosEncodingException e) {
                                                throw new RuntimeException(e);
                                            } catch (EscPosBarcodeException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }

                                }
                                try {
                                    printer.printFormattedText("Thank You Visit Again");
                                    printer.printFormattedText("Software Powered by : <b>Harsh Pardhi</b>");
                                } catch (EscPosParserException e) {
                                    throw new RuntimeException(e);
                                } catch (EscPosEncodingException e) {
                                    throw new RuntimeException(e);
                                } catch (EscPosBarcodeException e) {
                                    throw new RuntimeException(e);
                                }

                            } catch (EscPosConnectionException e) {
                                Toast.makeText(table6.this, "Unable To Connect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else if (position == R.id.quit) {
                      int row=tableLayout.getChildCount();
                      tableLayout.removeViews(1,row-1);
                        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("my_db6", MODE_PRIVATE, null);
                        db.execSQL("DELETE FROM myfood6");
                        db.close();
                        money.setText("Rs. 0");


                            } else if (position==R.id.addcust) {
                        View mylayout2 = getLayoutInflater().inflate(R.layout.custadd, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(table6.this);
                        builder.setTitle("Add Customer Details");
                        builder.setCancelable(true);
                        builder.setView(mylayout2);
                        EditText name = mylayout2.findViewById(R.id.custname);
                        EditText mobile = mylayout2.findViewById(R.id.mobile);
                        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String getname = name.getText().toString();
                                String getmob = mobile.getText().toString();
                                double totalFoodPrice = 0;
                                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                                    TableRow rw = (TableRow) tableLayout.getChildAt(i);
                                    TextView textView = (TextView) rw.getChildAt(2);
                                    if (textView != null) {
                                        String billprice = textView.getText().toString();
                                        if (billprice.isEmpty() || billprice == null) {
                                            continue;
                                        }
                                        totalFoodPrice += Double.parseDouble(billprice);
                                    }
                                }
                                Date date = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                                String formattedDate = sdf.format(date);
                                String gettable="Table 6";
                                savedmydata(getname,getmob,totalFoodPrice,formattedDate,gettable);

                            }
                            private void savedmydata(String getname,String getmob,Double totalFoodPrice,String formattedDate,String gettable ) {
                                String url = "Your Web App URL Here ";
                                OkHttpClient okHttpClient=new OkHttpClient();

                                RequestBody requestBody = new MultipartBody.Builder()
                                        .setType(MultipartBody.FORM)
                                        .addFormDataPart("action", "create")
                                        .addFormDataPart("getname", getname)
                                        .addFormDataPart("getmob", getmob)
                                        .addFormDataPart("totalFoodPrice", String.valueOf(totalFoodPrice))
                                        .addFormDataPart("formattedDate", formattedDate)
                                        .addFormDataPart("gettable", gettable)
                                        .build();
                                Request request=new Request.Builder()
                                        .url(url)
                                        .method("POST",requestBody)
                                        .build();

                                Call call=okHttpClient.newCall(request);
                                call.enqueue(new Callback() {
                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(table6.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                        if (response.isSuccessful()) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(table6.this, "Data Sent Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(table6.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }

                                    }
                                });


                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        builder.show();



                    } else {
                                Toast.makeText(table6.this, "Sorry", Toast.LENGTH_SHORT).show();
                            }

                        return false;

                }




            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View mylayout = getLayoutInflater().inflate(R.layout.activity_test, null);
                    AutoCompleteTextView atx=(AutoCompleteTextView)mylayout.findViewById(R.id.myfoodname);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(table6.this, android.R.layout.simple_list_item_1, arr);
                    atx.setAdapter(adapter);


                    AlertDialog.Builder builder = new AlertDialog.Builder(table6.this);
                    builder.setTitle("Table-6 Add FoodItem");
                    builder.setCancelable(false);
                    builder.setView(mylayout);
                    EditText editText = mylayout.findViewById(R.id.myfoodname);
                    EditText foodqty = mylayout.findViewById(R.id.myqty);
                    EditText foodprice = mylayout.findViewById(R.id.myprice);
                    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String foodName = editText.getText().toString();
                            String foodQty = foodqty.getText().toString();
                            String foodPrice = foodprice.getText().toString();

                            if(foodName.isEmpty() && foodPrice.isEmpty() && foodQty.isEmpty())
                            {
                                foodName="";
                                foodPrice=" ";
                                foodQty= " ";
                                Toast.makeText(table6.this,"Please Enter Food Price and Food Quantity",Toast.LENGTH_SHORT).show();

                            }else {
                                try {
                                    double totalprice = Double.parseDouble(foodPrice) * Double.parseDouble(foodQty);

                                    TableRow row = new TableRow(getApplicationContext());
                                    TextView name = new TextView(getApplicationContext());
                                    name.setText(foodName);
                                    name.setGravity(Gravity.START);
                                    name.setSingleLine(false);
                                    name.setMaxLines(1);
                                    name.setPadding(80, 0, 0, 0);
                                    TextView quantity = new TextView(getApplicationContext());
                                    quantity.setText(foodQty);
                                    quantity.setGravity(Gravity.CENTER);
                                    quantity.setPadding(70, 0, 0, 0);
                                    quantity.setMaxLines(1);
                                    TextView itemprice = new TextView(getApplicationContext());
                                    itemprice.setText("" + totalprice);
                                    itemprice.setGravity(Gravity.CENTER);
                                    itemprice.setSingleLine(false);
                                    itemprice.setPadding(105, 0, 0, 0);
                                    itemprice.setMaxLines(1);
                                    row.addView(name);
                                    row.addView(quantity);
                                    row.addView(itemprice);
                                    tableLayout.addView(row);
                                    savedData(foodName, foodQty, foodPrice);
                                } catch (NumberFormatException e) {
                                    Toast.makeText(table6.this, "Please enter a valid food name", Toast.LENGTH_SHORT).show();
                                }


                                double totalFoodPrice = 0;
                                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                                    TableRow rw = (TableRow) tableLayout.getChildAt(i);
                                    TextView textView = (TextView) rw.getChildAt(2);
                                    if (textView != null) {
                                        String billprice = textView.getText().toString();
                                        if (billprice.isEmpty() || billprice == null) {
                                            continue;
                                        }
                                        totalFoodPrice += Double.parseDouble(billprice);
                                        money.setText("Rs." + totalFoodPrice);

                                    }
                                }
                                EmitterConfig emitter = new Emitter(300, TimeUnit.MILLISECONDS).max(300);
                                konfettiView.start(
                                        new PartyFactory(emitter)
                                                .shapes(Shape.Circle.INSTANCE, Shape.Square.INSTANCE)
                                                .spread(360)
                                                .position(0.5, 0.25, 1, 1)

                                                .sizes(new Size(8, 50, 10))
                                                .timeToLive(1000)
                                                .fadeOutEnabled(true)
                                                .build()
                                );



                                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                                    TableRow myrow = (TableRow) tableLayout.getChildAt(i);
                                    TextView name2 = (TextView) myrow.getChildAt(0);
                                    TextView qty2 = (TextView) myrow.getChildAt(1);
                                    TextView price2 = (TextView) myrow.getChildAt(2);
                                }
                            }
                                editText.setText("");
                                foodqty.setText("");
                                foodprice.setText("");


                        }
                        private void savedData(String foodName1, String foodQty2, String foodPrice2 ) {
                            SQLiteDatabase db=getApplicationContext().openOrCreateDatabase("my_db6",MODE_PRIVATE,null);
                            ContentValues values=new ContentValues();
                            values.put("name", foodName1);
                            values.put("qty", foodQty2);
                            values.put("price", foodPrice2);
                            db.insert("myfood6", null, values);
                            db.close();

                        }


                    });
                    builder.setNegativeButton("Cancel", null);
                    builder.show();


                }



            });


               rmv.setOnClickListener(new View.OnClickListener() {
                   @Override
                public void onClick(View v) {
                int row=tableLayout.getChildCount();
                 if(row>1) {
                     tableLayout.removeViewAt(row - 1);
                 }

                    double totalFoodPrice = 0;
                    for (int i = 1; i < tableLayout.getChildCount(); i++) {
                        TableRow rw = (TableRow) tableLayout.getChildAt(i);
                        TextView textView = (TextView) rw.getChildAt(2);
                        if (textView != null) {
                            String billprice = textView.getText().toString();
                            if (billprice.isEmpty() || billprice == null) {
                                continue;
                            }
                            totalFoodPrice += Double.parseDouble(billprice);
                            money.setText("Rs." + totalFoodPrice);
                        }

                    }
                    if (tableLayout.getChildCount() == 0) {
                        money.setText(0);
                }


               }
    });



    }
}




