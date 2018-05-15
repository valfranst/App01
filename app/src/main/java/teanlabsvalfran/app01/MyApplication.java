package teanlabsvalfran.app01;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Valfran on 29/01/2018.
 */

public class MyApplication extends Application {

    private static MyApplication singleton;
    private AppDatabase database = null;
    private static Context context;

    private MyApplication() {
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
        context = getApplicationContext();
    }

    public  static MyApplication getInstance(){
        if(singleton == null){
            singleton = new MyApplication();
        }
        return singleton;
    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public AppDatabase getDatabase() {
        return database;
    }

    public static Context getContext() {
        return context;
    }
}
