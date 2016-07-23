package solo.robotium.com.qianjiaochen.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import solo.robotium.com.qianjiaochen.weather.util.NetworkUtil;
import solo.robotium.com.qianjiaochen.weather.util.WeatherUtil;

/**
 * Created by qianjiaochen on 16/7/10.
 */
public class NetWorkRecevier extends BroadcastReceiver{

    public String TAG="NetWorkRecevier";
    public Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"onReceive");
        mContext=context;
        //网络变化了,更新网络参数
        NetworkUtil.getInstance(mContext).getNetWorkState();
    }
}
