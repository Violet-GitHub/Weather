package solo.robotium.com.qianjiaochen.weather.sqlit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.model.CityInfo;
import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;

/**
 * Created by qianjiaochen on 16/6/13.
 */
public class WeatherInfoDB {
    public String TAG="WeatherInfoDB";
    public ArrayList<WeatherInfo> mWeatherInfos=new ArrayList<WeatherInfo>();
    public ArrayList<CityInfo> mHotCitys=new ArrayList<CityInfo>();
    private static WeatherInfoDB mWeatherInfoDB=null;
    public String DATABASE_NAME="weather_city.db";
    private SQLiteDatabase db;
    public String DB_PATH="/data/data/solo.robotium.com.qianjiaochen.weather/"+DATABASE_NAME;

    public static WeatherInfoDB getInstance(){
        if(mWeatherInfoDB==null){
            mWeatherInfoDB=new WeatherInfoDB();
        }
        return mWeatherInfoDB;
    }

    //创建设置city的表
    public void mCreateWeatherInfos(){
        String city,cityId,temp1,temp2,temp,weather,img1,img2,ptime,WD,WS,SD,WSE,time,isRadar,Radar,njd,qy,rain;
        if(!tabIsExist("weatherinfos")){
            String sql="create table weatherinfos (" +
                    "city text not null ," +
                    "cityId text not null," +
                    "weather text not null," +
                    "temp1 text not null," +
                    "img1 text not null," +
                    "WD text not null," +
                    "SD text not null," +
                    "ptime text not null);";
            db = SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
            db.execSQL(sql);
            Log.e(TAG, "weatherinfos表创建成功");
            if(db.isOpen()){
                db.close();
            }
        }
    }

    /**
     * 判断某张表是否存在
     * @param tabName 表名
     * @return
     */
    public boolean tabIsExist(String tabName){
        boolean result = false;
        if(tabName == null){
            return false;
        }
        String sql="select count(*) as c from sqlite_master where type ='table' and name ='"+tabName.trim()+"' ;";
        db=SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        Cursor mCursor = db.rawQuery(sql,null);
        if(mCursor.moveToNext()){
            int count = mCursor.getInt(0);
            if(count>0){
                result = true;
                Log.e(TAG,"cityset表已存在");
            }
        }

        if(db.isOpen()){
            db.close();
        }
        if(!mCursor.isClosed()){
            mCursor.close();
        }
        return result;
    }


    /**
     * 查询制定城市是否存在
     * @param cityID
     * @return
     */
    public int mSelectOneWeatherInfos(String cityID){
        String sql="select * from weatherinfos where cityID="+cityID+"";
        db=SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        Cursor mCursor=db.rawQuery(sql, null);
        if(mCursor.getCount()!=0){
            return 1;
        }
        return -1;
    }

    /**
     * 查询制定城市是否存在
     * @param cityName
     * @return
     */
    public ArrayList<CityInfo> mSelectOneCitys(String cityName){
        mHotCitys.clear();
        String sql="select * from citys where english='"+cityName+"' or name='"+cityName+"';";
        db=SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        Cursor mCursor=db.rawQuery(sql, null);
        while(mCursor.moveToNext()){
            CityInfo mCityInfo=new CityInfo();
            mCityInfo.setCityNmame(mCursor.getString(2));
            mCityInfo.setCityID(mCursor.getString(3));
            mHotCitys.add(mCityInfo);
        }

        if(db.isOpen()){
            db.close();
        }
        if(!mCursor.isClosed()){
            mCursor.close();
        }
        return mHotCitys;
    }

    /*
    * 删除一个已设置城市
    * @param cityID
    * @return
    */
    public void mDeleteOneweatherinfos(String cityID,BaseAdapter adapter){
        String sql="delete from weatherinfos where cityID="+cityID+"";
        db=SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        db.execSQL(sql);
        mSelectAllWeatherinfos();
        adapter.notifyDataSetChanged();
        if(db.isOpen()){
            db.close();
        }
    }

    //city设置表中插入一条数据
    public void mInsertWeatherinfos(WeatherInfo weatherInfo){
        db = SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        db.insert("weatherinfos", null, getContentValues(weatherInfo));
        if(db.isOpen()){
            db.close();
        }
    }

    public void mUpdateWeatherinfos(WeatherInfo weatherInfo){
        db = SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        db.update("weatherinfos", getContentValues(weatherInfo), "cityId = ?", new String[]{weatherInfo.getCityId()});
        if(db.isOpen()){
            db.close();
        }
    }

    //city设置表中插入的value值
    public ContentValues getContentValues(WeatherInfo weatherInfo){
        ContentValues mContentValues=new ContentValues();
        mContentValues.put("city",weatherInfo.getCity()!=null?weatherInfo.getCity():"无网");
        mContentValues.put("cityId",weatherInfo.getCityId()!=null?weatherInfo.getCityId():"000000000");
        mContentValues.put("weather",weatherInfo.getWeather()!=null?weatherInfo.getWeather():"晴");
        mContentValues.put("temp1",weatherInfo.getTemp1()!=null?weatherInfo.getTemp1():"28");
        mContentValues.put("img1",weatherInfo.getImg1()!=null?weatherInfo.getImg1():"n0.gif");
        mContentValues.put("WD",weatherInfo.getWD()!=null?weatherInfo.getWD():"东南风");
        mContentValues.put("SD",weatherInfo.getSD()!=null?weatherInfo.getSD():"30%");
        mContentValues.put("ptime",weatherInfo.getPtime()!=null?weatherInfo.getPtime():"17:00");
        return mContentValues;
    }

    //获取所有设置的city
    public ArrayList<WeatherInfo> mSelectAllWeatherinfos(){
        String sql="select * from weatherinfos;";
        db = SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        Cursor mCursor=db.rawQuery(sql, null);
        mWeatherInfos.clear();
        while(mCursor.moveToNext()){
            WeatherInfo mWeatherInfo=new WeatherInfo();
            mWeatherInfo.setCity(mCursor.getString(0));
            mWeatherInfo.setCityId(mCursor.getString(1));
            mWeatherInfo.setWeather(mCursor.getString(2));
            mWeatherInfo.setTemp1(mCursor.getString(3));
            mWeatherInfo.setImg1(mCursor.getString(4));
            mWeatherInfo.setWD(mCursor.getString(5));
            mWeatherInfo.setSD(mCursor.getString(6));
            mWeatherInfo.setPtime(mCursor.getString(7));
            mWeatherInfos.add(mWeatherInfo);
            Log.e(TAG,mWeatherInfo.getCityId()+"  "+mWeatherInfo.getCity());
        }

        if(db.isOpen()){
            db.close();
        }
        if(!mCursor.isClosed()){
           mCursor.close();
        }
        return mWeatherInfos;
    }

    //获取所有热门的city
    public ArrayList<CityInfo> mSelectHotCity(){
        mHotCitys.clear();
        String sql="select * from citys where hotcity = 1";
        db = SQLiteDatabase.openOrCreateDatabase(DB_PATH,null);
        Cursor mCursor=db.rawQuery(sql, null);
        while(mCursor.moveToNext()){
            CityInfo mCityInfo=new CityInfo();
            mCityInfo.setCityNmame(mCursor.getString(2));
            mCityInfo.setCityID(mCursor.getString(3));
            mHotCitys.add(mCityInfo);
//            Log.e(TAG,mCityInfo.getCityID()+"  "+mCityInfo.getCityNmame());
        }

        if(!mCursor.isClosed()){
            mCursor.close();
        }
        if(db.isOpen()){
            db.close();
        }
        return mHotCitys;
    }

}
