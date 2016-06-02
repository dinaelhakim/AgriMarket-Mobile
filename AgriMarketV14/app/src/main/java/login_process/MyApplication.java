package login_process;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Enas on 07/05/2016.
 */


public class MyApplication extends Application {
    private static MyApplication MyApplication;

    public MyApplication getInstance(){
        return MyApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication = this;
    }
}

