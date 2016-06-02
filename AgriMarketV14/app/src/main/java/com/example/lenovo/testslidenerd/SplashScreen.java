package com.example.lenovo.testslidenerd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import login_process.LogIn;
import luncher.landing_page.MainActivity;

public class SplashScreen extends Activity {
    public static final String fileName="SharedFile";
    Intent intent;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);

            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        //Connect To Check user Web Service-----------------------------------------------------------------------------





                        //-----------------------------------------------------------------------------

                        int userId=-1;
                        if(userId==-1){
                            intent = new Intent(SplashScreen.this, LogIn.class);
                        }else {
                            intent = new Intent(SplashScreen.this, MainActivity.class);
                        }
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }

        @Override
        protected void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            finish();
        }
}