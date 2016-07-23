package solo.robotium.com.qianjiaochen.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import solo.robotium.com.qianjiaochen.weather.service.WeatherService;

/**
 * Created by qianjiaochen on 16/6/14.
 */
public class AutoRecevier extends BroadcastReceiver{
    public String TAG="AutoRecevier";
    public Intent mIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"onReceive");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mIntent=new Intent(context, WeatherService.class);
        context.startService(mIntent);
    }
}
