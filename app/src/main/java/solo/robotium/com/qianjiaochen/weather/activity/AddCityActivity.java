package solo.robotium.com.qianjiaochen.weather.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.adpter.AddCityAdapter;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;

/**
 * Created by qianjiaochen on 16/6/20.
 */
public class AddCityActivity extends Activity{

    public Context mContext;
    public GridView mGridView;
    public AddCityAdapter mAddCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.add_city);
        initView();
    }

    public void initView(){
        mGridView=(GridView)findViewById(R.id.mGridView);
        mAddCityAdapter=AddCityAdapter.getInstance(mContext,WeatherInfoDB.getInstance().mSelectAllWeatherinfos());
        mGridView.setAdapter(mAddCityAdapter);

        mGridView.setOnItemClickListener(mOnItemClickListener);
        mGridView.setOnItemLongClickListener(mOnItemLongClickListener);
    }

    public AdapterView.OnItemClickListener mOnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(mAddCityAdapter.mEdit==View.VISIBLE){
                mAddCityAdapter.mEdit=View.INVISIBLE;
                mAddCityAdapter.notifyDataSetChanged();
            }else{
                Intent myIntent=new Intent("SELECT_POSITION");
                myIntent.putExtra("position", position);
                mContext.sendBroadcast(myIntent);
                finish();
            }
        }
    };

    public AdapterView.OnItemLongClickListener mOnItemLongClickListener=new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if(mAddCityAdapter.mEdit==View.INVISIBLE){
                mAddCityAdapter.mEdit=View.VISIBLE;
                mAddCityAdapter.notifyDataSetChanged();
            }
            return true;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
