package com.example.omoiyaemma.coinscashfor8ballpool;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Main2Activity extends AppCompatActivity {

    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";


    int status = 0 ;
    private  ProgressBar mProgress;
    private TextView textViewPercent;
    private TextView textViewCoins;
    private TextView textViewrate;
    private Handler handler = new Handler();
    private Button button;
    private TextView changeText;
    private static final int START_LEVEL = 1;
    private int mLevel;
    //private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle(getString(R.string.title));
        status = 0;


        textViewPercent = (TextView) findViewById(R.id.tv);
        mAdView = (AdView) findViewById(R.id.adView);
        mProgress = (ProgressBar) findViewById(R.id.marker_progress);
        button = (Button) findViewById(R.id.button2);
        changeText = (TextView) findViewById(R.id.changeText);
        textViewrate = (TextView) findViewById(R.id.changeText4);
        textViewCoins = (TextView) findViewById(R.id.changeText3);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                status = 0;
            }
        });


        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();


        if (status == 0) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (status < 100) {
                        status += 1;


                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                mProgress.setProgress(status);
                                textViewPercent.setText(status + "%");


                                if (status == 100) {
                                    changeText.setVisibility(View.INVISIBLE);
                                    textViewCoins.setVisibility(View.VISIBLE);
                                    textViewrate.setVisibility(View.VISIBLE);
                                    button.setVisibility(View.VISIBLE);
                                    //  button.setEnabled(false);
                                }

                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            // Just to display the progress slowly
                            Thread.sleep(300); //thread will take approx 3 seconds to finish
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }).start();


        }
    }



    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                button.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                button.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
                status = 0;
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
      //  button.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        // mLevelTextView.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    @Override
    public void onPause(){
        if(mAdView != null){
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume(){
        if(mAdView != null){
            mAdView.resume();
        }
       // status = 0;
        super.onResume();
    }

    @Override
    public void onDestroy(){
        if(mAdView != null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
}


