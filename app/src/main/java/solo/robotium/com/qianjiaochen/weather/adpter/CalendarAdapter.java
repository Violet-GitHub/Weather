package solo.robotium.com.qianjiaochen.weather.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import solo.robotium.com.qianjiaochen.weather.view.CityWeatherView;

/**
 * Created by qianjiaochen on 16/7/10.
 */
public class CalendarAdapter extends BaseAdapter {
    public CityWeatherView mContext;

    public CalendarAdapter(CityWeatherView context){
        mContext=context;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
        return mContext.mCalendarViewMySelfs[position%mContext.view_num];
    }
}
