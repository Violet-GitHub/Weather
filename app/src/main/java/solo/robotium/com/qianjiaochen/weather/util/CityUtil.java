package solo.robotium.com.qianjiaochen.weather.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;

/**
 * Created by qianjiaochen on 16/6/24.
 */
public class CityUtil {
    public String TAG="CityUtil";
    private static CityUtil mCityUtil;
    public static Context mContext;
    public static CityUtil getInstance(Context con){
        mContext=con;
        if(mCityUtil==null){
            mCityUtil=new CityUtil();
        }
        return mCityUtil;
    }

    /**
     * 从res/raw文件夹下导入数据库到apk文件中;
     */
    public void importDataBase(){
        if(!(new File(WeatherInfoDB.getInstance().DB_PATH)).exists()){
            InputStream is= mContext.getResources().openRawResource(R.raw.weather_city);
            try {
                FileOutputStream fos=new FileOutputStream(WeatherInfoDB.getInstance().DB_PATH);
                byte[] mbyte=new byte[1024];
                int count=0;
                while((count = is.read(mbyte))>0){
                    fos.write(mbyte,0,count);
                }
                is.close();
                fos.close();
                Log.e(TAG,"数据库已导入");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        WeatherInfoDB.getInstance().mCreateWeatherInfos();
    }

}
