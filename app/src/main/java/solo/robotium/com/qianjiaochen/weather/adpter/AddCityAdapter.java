package solo.robotium.com.qianjiaochen.weather.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.activity.SelectActivity;
import solo.robotium.com.qianjiaochen.weather.model.CityInfo;
import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;
import solo.robotium.com.qianjiaochen.weather.view.AddCityView;

/**
 * Created by qianjiaochen on 16/6/26.
 */
public class AddCityAdapter extends BaseAdapter{
    public static Context mContext;
    public static ArrayList<WeatherInfo> mWeatherInfos;
    public AddCityView mAddCityView;
    public int mEdit=View.INVISIBLE;

    public static AddCityAdapter mAddCityAdapter;

    public static AddCityAdapter getInstance(Context context,ArrayList<WeatherInfo> array){
        mContext=context;
        mWeatherInfos=array;
        if(mAddCityAdapter==null){
            mAddCityAdapter=new AddCityAdapter();
        }
        return mAddCityAdapter;
    }

    private AddCityAdapter(){}

    @Override
    public int getCount() {
        if(mEdit==View.INVISIBLE){
            return mWeatherInfos!=null?mWeatherInfos.size()+1:0;
        }else {
            if(mWeatherInfos!=null&&mWeatherInfos.size()!=0){
                return mWeatherInfos.size();
            }else {
                mEdit=View.INVISIBLE;
                notifyDataSetChanged();
                return 0;
            }
        }
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
        mAddCityView=new AddCityView(mContext,mAddCityAdapter);

        if(mWeatherInfos.size()==position){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.add,null);
            convertView.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent=new Intent(mContext, SelectActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
            return convertView;
        }else{
            mAddCityView.mCityName.setText(mWeatherInfos.get(position).getCity());
            mAddCityView.setmOne_city_delete(mWeatherInfos.get(position).getCityId(), mEdit);
        }
        return mAddCityView;
    }

}
