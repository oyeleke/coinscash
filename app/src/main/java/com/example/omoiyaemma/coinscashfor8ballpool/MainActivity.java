package com.example.omoiyaemma.coinscashfor8ballpool;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        button = (Button)findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Button_clicked","yeah");

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.main_dialog);
                dialog.setCancelable(true);

                //set up buttons

                TextView textViewCancel = (TextView)dialog.findViewById(R.id.cancel);
                TextView textViewNext = (TextView)dialog.findViewById(R.id.next);

                //set up text views to be clicked

                textViewCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,getString(R.string.cncelled),Toast.LENGTH_LONG).show();
                    }
                });

                textViewNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);

                    }
                });

                dialog.show();



            }
        });


    }
}
