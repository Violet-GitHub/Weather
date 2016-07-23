package solo.robotium.com.qianjiaochen.weather.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.adpter.AddCityAdapter;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;

/**
 * Created by qianjiaochen on 16/7/2.
 */
public class AddCityView extends RelativeLayout implements View.OnClickListener {

    public Context mContext;
    public View mView;
    public TextView mCityName, mWeather, mCurrentTemp, mSD, mWD;
    public ImageView mOne_city_delete;
    public String mCityID;
    public AddCityAdapter mAddCityAdapter;

    public AddCityView(Context context, AddCityAdapter addCityAdapter) {
        super(context);
        mContext = context;
        mAddCityAdapter = addCityAdapter;
        initView();
    }

    public void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.add_city_onecity, this);
        mCityName = (TextView) mView.findViewById(R.id.mCityName);
        mWeather = (TextView) mView.findViewById(R.id.mWeather);
        mCurrentTemp = (TextView) mView.findViewById(R.id.mCurrentTemp);
        mSD = (TextView) mView.findViewById(R.id.mSD);
        mWD = (TextView) mView.findViewById(R.id.mWD);
        mOne_city_delete = (ImageView) mView.findViewById(R.id.one_city_delete);

        mOne_city_delete.setOnClickListener(this);
    }

    public void setmOne_city_delete(String cityID,int edit) {
        this.mCityID=cityID;
        this.mOne_city_delete.setVisibility(edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_city_delete:
                WeatherInfoDB.getInstance().mDeleteOneweatherinfos(mCityID, mAddCityAdapter);
                break;
            default:
                break;
        }
    }
}
