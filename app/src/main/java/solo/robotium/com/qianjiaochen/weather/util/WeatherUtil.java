package solo.robotium.com.qianjiaochen.weather.util;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;

/**
 * Created by qianjiaochen on 16/6/12.
 */
public class WeatherUtil{

    public String TAG="WeatherUtil";
    private static WeatherUtil mWeatherUtil;
    public static Context mContext;
    public WeatherInfo myWeatherInfo;

    public static WeatherUtil getInstance(Context context){
        mContext=context;
        if (mWeatherUtil==null){
            mWeatherUtil=new WeatherUtil();
        }
        return mWeatherUtil;
    }

    /**
     * 根据用户设置的城市,获取城市的天气信息,并存放到数据库当中;
     */
    public void getWeatherInfos(){
        if(NetworkUtil.getInstance(mContext).netSate== NetworkInfo.State.CONNECTED){
            myWeatherInfo = new WeatherInfo();
            new  Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<WeatherInfo> mWeatherInfos=WeatherInfoDB.getInstance().mSelectAllWeatherinfos();
                    for (int i=0;i<mWeatherInfos.size();i++){
                        WeatherUtil.getInstance(mContext).getWeatherInfo(mWeatherInfos.get(i).getCityId());
                        Log.e(TAG, myWeatherInfo.getCityId() + "  " + myWeatherInfo.getCity());
                        //更新数据库
                        WeatherInfoDB.getInstance().mUpdateWeatherinfos(myWeatherInfo);
                    }
                    //更新
                    Intent myIntent = new Intent("UPDATE_ALL_WEATHERINFO");
                    mContext.sendBroadcast(myIntent);
                }
            }).start();
        }
    }


    /**
     * 根据用户设置的城市,获取城市的天气信息,并存放到数据库当中;
     */
    public void getWeatherInfo(final String cityID){
        if(NetworkUtil.getInstance(mContext).netSate== NetworkInfo.State.CONNECTED){
            myWeatherInfo = new WeatherInfo();
            new  Thread(new Runnable() {
                @Override
                public void run() {
                    myWeatherInfo =getWeatherinfo(cityID);
                    Log.e(TAG, myWeatherInfo.getCityId() + "  " + myWeatherInfo.getCity());
                    //更新数据库
                    WeatherInfoDB.getInstance().mUpdateWeatherinfos(myWeatherInfo);
                    //更新
                    Intent myIntent = new Intent("UPDATE_ONE_WEATHERINFO");
                    myIntent.putExtra("curCityId",myWeatherInfo.getCityId());
                    mContext.sendBroadcast(myIntent);
                }
            }).start();
        }else{
            Toast.makeText(mContext, R.string.netconnect,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取指定城市的天气信息;
     * @param cityID
     * @return
     */
    private WeatherInfo getWeatherinfo(String cityID) {
        WeatherInfo mWeatherInfo=new WeatherInfo();
        JSONObject mJSONObject;
        try {
            mJSONObject=new JSONObject(getCityInfo(cityID)).getJSONObject("weatherinfo");
            Log.e(TAG, mJSONObject.toString() + 1);
            mWeatherInfo.setCity(mJSONObject.getString("city"));
            mWeatherInfo.setCityId(mJSONObject.getString("cityid"));
            mWeatherInfo.setTemp1(mJSONObject.getString("temp1"));
            mWeatherInfo.setTemp2(mJSONObject.getString("temp2"));
            mWeatherInfo.setWeather(mJSONObject.getString("weather"));
            mWeatherInfo.setImg1(mJSONObject.getString("img1"));
            mWeatherInfo.setImg2(mJSONObject.getString("img2"));
            mWeatherInfo.setPtime(mJSONObject.getString("ptime"));

            mJSONObject=new JSONObject(getSK(cityID)).getJSONObject("weatherinfo");
            Log.e(TAG, mJSONObject.toString() + 2);
            mWeatherInfo.setTemp(mJSONObject.getString("temp"));
            mWeatherInfo.setWD(mJSONObject.getString("WD"));
            mWeatherInfo.setWS(mJSONObject.getString("WS"));
            mWeatherInfo.setSD(mJSONObject.getString("SD"));
            mWeatherInfo.setWSE(mJSONObject.getString("WSE"));
            mWeatherInfo.setTime(mJSONObject.getString("time"));
            mWeatherInfo.setIsRadar(mJSONObject.getString("isRadar"));
            mWeatherInfo.setRadar(mJSONObject.getString("Radar"));
            mWeatherInfo.setNjd(mJSONObject.getString("njd"));
            mWeatherInfo.setQy(mJSONObject.getString("qy"));
            mWeatherInfo.setRain(mJSONObject.getString("rain"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            Log.e(TAG,"mJSONObject is NullPointerException");
        }

        return mWeatherInfo;
    }

    /*
     *获取cityinfo信息
     *http://www.weather.com.cn/data/cityinfo/101010100.html
     * {"weatherinfo":{"city":"北京","cityid":"101010100","temp1":"-2 0c","temp2":"16 0c","weather":"晴","img1":"no.gif","img2":"do.gif","ptime":"18:00"}}
     */
    public String getCityInfo(String cityID)  {
        String str = null;
        try {
             str=getWeatherNW(new URL("http://www.weather.com.cn/data/cityinfo/"+cityID+".html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /*
     *获取sk信息
     * http://www.weather.com.cn/data/sk/101010100.html
     * {"weatherinfo":{"city":"北京","cityid":"101010100","temp":"18","WD":"东南风","WS":"1级","SD":"17%","WSE":"1",
     * "time":"17:05","isRadar":"1","Radar":"JC_RADAR_AZ9010_JB","njd":"暂无实况","qy":"1011","rain":"0"}}
     */
    public String getSK(String cityID){
        String str = null;
        try {
            str=getWeatherNW(new URL("http://www.weather.com.cn/data/sk/"+cityID+".html"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 根据给定url,获取天气信息
     * @param url
     * @return
     * @throws IOException
     */
    public String getWeatherNW(URL url) {
        String result = null;
        URLConnection mURLConnection= null;
        try {
            mURLConnection = url.openConnection();
            mURLConnection.setConnectTimeout(1000);
            InputStream is = mURLConnection.getInputStream();
            BufferedInputStream bfis=new BufferedInputStream(is);
            ByteArrayBuffer baf=new ByteArrayBuffer(100);
            int current = 0;
               try{
                //当BufferedInputStream数据读完后,返回-1,并跑出异常
                while((current = bfis.read())!=-1){
                    //把数据存入ByteArrayBuffer中
                    baf.append(current);
                }
               }catch (IOException e){
                   Log.e(TAG,"IOException");
               }
            result=new String(baf.toByteArray(),"UTF-8");
            if(is!=null){
                is.close();
            }
            if (bfis!=null){
                bfis.close();
            }
            if (baf!=null){
                baf.clear();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        //返回一个字符串
        return result;
    }


}
