package solo.robotium.com.qianjiaochen.weather.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.model.CityInfo;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;

import static solo.robotium.com.qianjiaochen.weather.R.color.lightblue;

/**
 * Created by qianjiaochen on 16/6/24.
 */
public class SelectCityAdapter extends BaseAdapter {
    public Context mContext;
    public ArrayList<CityInfo> mHotCitys;
    public ViewHold mViewHold;

    public SelectCityAdapter(Context c, ArrayList<CityInfo> infos){
        mContext=c;
        mHotCitys=infos;
    }

    @Override
    public int getCount() {
        return mHotCitys!=null?mHotCitys.size():0;
    }

    @Override
    public CityInfo getItem(int position) {
        return mHotCitys!=null?mHotCitys.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            mViewHold=new ViewHold();
            convertView= LinearLayout.inflate(mContext, R.layout.select_city_name,null);
            mViewHold.mCityName=(TextView)convertView.findViewById(R.id.select_city_name);
            convertView.setTag(mViewHold);
        }else{
            mViewHold=(ViewHold)convertView.getTag();
        }
        //if(position==0){
            //mViewHold.mCityName.setText("自动定位");
        //}else{
            if(WeatherInfoDB.getInstance().mSelectOneWeatherInfos(mHotCitys.get(position).getCityID())==1){
                mViewHold.mCityName.setTextColor(mContext.getResources().getColor(R.color.lightblue));
                mViewHold.mCityName.setText(mHotCitys.get(position).getCityNmame());
            }else{
                mViewHold.mCityName.setText(mHotCitys.get(position).getCityNmame());
            }
        //}

        return convertView;
    }


    static class ViewHold{
        TextView mCityName;
    }
}

