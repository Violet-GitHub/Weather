package solo.robotium.com.qianjiaochen.weather.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;
import solo.robotium.com.qianjiaochen.weather.util.CityUtil;
import solo.robotium.com.qianjiaochen.weather.util.NetworkUtil;
import solo.robotium.com.qianjiaochen.weather.util.WeatherUtil;

/**
 * Created by qianjiaochen on 16/6/14.
 */
public class WeatherService extends Service {
    public String TAG="WeatherService";
    public Context mContext;
    public Timer mTimer=new Timer();
    public TimerTask mTimerTask=new TimerTask() {
        @Override
        public void run() {
            WeatherUtil.getInstance(mContext).getWeatherInfos();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate...");
        mContext=this;

        //导入数据库到终端程序包当中
        CityUtil.getInstance(mContext).importDataBase();
        //初始化网络
        NetworkUtil.getInstance(mContext).getNetWorkState();

        mTimer.schedule(mTimerTask, 1000, 1000*60*60*2);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
