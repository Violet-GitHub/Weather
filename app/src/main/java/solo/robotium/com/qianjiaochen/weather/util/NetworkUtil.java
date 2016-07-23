package solo.robotium.com.qianjiaochen.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import solo.robotium.com.qianjiaochen.weather.R;

/**
 * Created by qianjiaochen on 16/7/11.
 */
public class NetworkUtil {
    public String TAG="NetworkUtil";
    public ConnectivityManager mConnectivityManager;
    private static NetworkUtil mNetworkUtil=null;
    public static Context mContext;
    public NetworkInfo.State netSate=NetworkInfo.State.DISCONNECTED;

    public static NetworkUtil getInstance(Context context){
        mContext=context;
        if(mNetworkUtil==null){
            mNetworkUtil=new NetworkUtil();
        }
        return mNetworkUtil;
    }

    public NetworkUtil(){}

    public void getNetWorkState(){
        Log.e(TAG,"getNetWorkState");
        mConnectivityManager= (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifiState=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        NetworkInfo.State mobileState=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        if(wifiState== NetworkInfo.State.CONNECTED||mobileState== NetworkInfo.State.CONNECTED){
            netSate=NetworkInfo.State.CONNECTED;
        }else{
            Toast.makeText(mContext, R.string.netconnect, Toast.LENGTH_LONG).show();
        }
    }

}
