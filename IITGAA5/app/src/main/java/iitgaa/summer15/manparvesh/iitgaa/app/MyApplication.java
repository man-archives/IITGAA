package iitgaa.summer15.manparvesh.iitgaa.app;

import android.app.Application;

import iitgaa.summer15.manparvesh.iitgaa.helper.ParseUtils;

/**
 * Created by Man Parvesh on 7/9/2015.
 */
public class MyApplication  extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // register with parse
        ParseUtils.registerParse(this);
    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
