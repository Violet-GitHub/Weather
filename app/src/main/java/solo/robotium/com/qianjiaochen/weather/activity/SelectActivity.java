package solo.robotium.com.qianjiaochen.weather.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.adpter.SelectCityAdapter;
import solo.robotium.com.qianjiaochen.weather.model.CityInfo;
import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;
import solo.robotium.com.qianjiaochen.weather.util.WeatherUtil;

/**
 * Created by qianjiaochen on 16/6/22.
 */
public class SelectActivity extends Activity implements AdapterView.OnItemClickListener{
    public String TAG="SelectActivity";
    public TextView input_name;
    public Button search_city;
    public GridView citylist;
    public SelectCityAdapter mSelectCityAdapter;
    public Context mContext;

    public Cursor mCursor;
    public ArrayList<CityInfo> mHotCitys=new ArrayList<CityInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        mContext=this;
        mHotCitys= WeatherInfoDB.getInstance().mSelectHotCity();
        initView();

    }

    public void initView(){
        input_name=(TextView)findViewById(R.id.input_name);
        search_city=(Button)findViewById(R.id.search_city);
        citylist=(GridView)findViewById(R.id.citylist);

        search_city.setOnClickListener(mOnClickListener);

        citylist.setOnItemClickListener(this);
         mSelectCityAdapter=new SelectCityAdapter(this,mHotCitys);
        citylist.setAdapter(mSelectCityAdapter);
    }


    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search_city:
                    if(input_name.getText().toString().equals("")||input_name.getText().toString().equals(null)){
                        mHotCitys= WeatherInfoDB.getInstance().mSelectHotCity();
                    }else{
                        mHotCitys=WeatherInfoDB.getInstance().mSelectOneCitys(input_name.getText().toString());
                    }
                    mSelectCityAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //if(position==0){
            //Toast.makeText(this,"正在定位...",Toast.LENGTH_SHORT).show();

       // }else{
            if(WeatherInfoDB.getInstance().mSelectOneWeatherInfos(mHotCitys.get(position).getCityID())==1){
                Toast.makeText(this,"所选城已存在!",Toast.LENGTH_SHORT).show();
            }else{
                WeatherInfo myWeatherInfo=new WeatherInfo();
                myWeatherInfo.setCityId(mHotCitys.get(position).getCityID());
                myWeatherInfo.setCity(mHotCitys.get(position).getCityNmame());
                WeatherInfoDB.getInstance().mInsertWeatherinfos(myWeatherInfo);
                WeatherUtil.getInstance(mContext).getWeatherInfo(myWeatherInfo.getCityId());
                Intent myIntent= new Intent(this,AddCityActivity.class);
                startActivity(myIntent);
                finish();
            }
        //}

    }




}
