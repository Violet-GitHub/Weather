/**
 * Created by qianjiaochen on 16/6/12.
 * view的适配器的使用
 * extends BaseAdapter ,override 它的函数
 *
 */
package solo.robotium.com.qianjiaochen.weather.adpter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;
import solo.robotium.com.qianjiaochen.weather.util.PictureUtil;
import solo.robotium.com.qianjiaochen.weather.view.CityWeatherView;


public class WeatherAdapter extends BaseAdapter {
    public String TAG="WeatherAdapter";
    public Context mContext;
    public ArrayList<WeatherInfo> mWeatherInfos;//数据源
    public  CityWeatherView mCityWeatherView;
    public MainAdapterHolder mMainAdapterHolder;


    public WeatherAdapter(Context c,ArrayList<WeatherInfo> infos){
        mContext=c;
        mWeatherInfos=infos;
    }

    @Override
    public int getCount() {
        return mWeatherInfos!=null&mWeatherInfos.size()!=0 ? mWeatherInfos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView");
        if(convertView==null){
            mCityWeatherView=new CityWeatherView(mContext);
            mMainAdapterHolder=new MainAdapterHolder();
            mMainAdapterHolder.cityName=mCityWeatherView.cityName;
            mCityWeatherView.setTag(mMainAdapterHolder);
        }else{
            mMainAdapterHolder=(MainAdapterHolder)convertView.getTag();
        }

        mCityWeatherView.cityName.setText(mWeatherInfos!=null&mWeatherInfos.size()!=0 ?mWeatherInfos.get(position).getCity():"无网");
        mCityWeatherView.weather.setText(mWeatherInfos!=null&mWeatherInfos.size()!=0 ?mWeatherInfos.get(position).getWeather():"晴");
        mCityWeatherView.weather_temp.setText(mWeatherInfos!=null&mWeatherInfos.size()!=0 ?mWeatherInfos.get(position).getTemp1():"34");
        mCityWeatherView.weather_WD.setText("风向:"+(mWeatherInfos!=null&mWeatherInfos.size()!=0 ?mWeatherInfos.get(position).getWD():"东南风"));
        mCityWeatherView.weather_SD.setText("湿度:"+(mWeatherInfos!=null&mWeatherInfos.size()!=0 ?mWeatherInfos.get(position).getSD():"30%"));
        mCityWeatherView.weather_time.setText("更新时间:" + (mWeatherInfos != null & mWeatherInfos.size() != 0 ? mWeatherInfos.get(position).getPtime() : "5:00"));
        mCityWeatherView.weather_img.setBackgroundResource(mWeatherInfos != null & mWeatherInfos.size() != 0 ? PictureUtil.getImageId(mWeatherInfos.get(position).getImg1(), 1) : PictureUtil.getImageId(null, 1));

        mMainAdapterHolder.cityName.setText(mCityWeatherView.cityName.getText().toString());

        return mCityWeatherView;
    }

    //getView中视图的Tag
    class MainAdapterHolder{
        TextView cityName;
    }

}
