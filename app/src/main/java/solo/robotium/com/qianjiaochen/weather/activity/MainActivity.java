/**
 * Created by qianjiaochen on 16/6/20.
 Activity的生命周期:
 1.启动Activity：系统会先调用onCreate方法，然后调用onStart方法，最后调用onResume，Activity进入运行状态。
 2.当前Activity被其他Activity覆盖其上或被锁屏：系统会调用onPause方法，暂停当前Activity的执行。
 3.当前Activity由被覆盖状态回到前台或解锁屏：系统会调用onResume方法，再次进入运行状态。
 4.当前Activity转到新的Activity界面或按Home键回到主屏，自身退居后台：系统会先调用onPause方法，然后调用onStop方法，进入停滞状态。
 5.用户后退回到此Activity：系统会先调用onRestart方法，然后调用onStart方法，最后调用onResume方法，再次进入运行状态。
 6.当前Activity处于被覆盖状态或者后台不可见状态，即第2步和第4步，系统内存不足，杀死当前Activity，而后用户退回当前Activity：再次调用onCreate方法、onStart方法、onResume方法，进入运行状态。
 7.用户退出当前Activity：系统先调用onPause方法，然后调用onStop方法，最后调用onDestory方法，结束当前Activity。

 onCreate()中进行程序的初始化编程
 onResume():
 onDestory()中进行程序的资源释放性销毁性编程

 控件的监听文件

 */

package solo.robotium.com.qianjiaochen.weather.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.adpter.WeatherAdapter;
import solo.robotium.com.qianjiaochen.weather.model.WeatherInfo;
import solo.robotium.com.qianjiaochen.weather.service.WeatherService;
import solo.robotium.com.qianjiaochen.weather.sqlit.WeatherInfoDB;
import solo.robotium.com.qianjiaochen.weather.util.CityUtil;
import solo.robotium.com.qianjiaochen.weather.util.NetworkUtil;
import solo.robotium.com.qianjiaochen.weather.util.PictureUtil;
import solo.robotium.com.qianjiaochen.weather.util.WeatherUtil;
import solo.robotium.com.qianjiaochen.weather.view.GalleryFlow;

public class MainActivity extends Activity implements View.OnClickListener{
    public String TAG="MainActivity";
    public Context mContext;
    public ImageView main_bg_before;
    public GalleryFlow main_gallery;
    public ImageButton cityweather_refresh,cityweather_add;
    public Button main_add_firstcity;
    public WeatherAdapter mWeatherAdapter;
    public RelativeLayout main_first_RelativeLayout;
    public LinearLayout CWV_LinearLayout_top;
    public int currenPosition=0;
    public ArrayList<WeatherInfo> mWeatherInfos;

    //广播接收器
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            Log.e(TAG,"onReceive...");
            String action = intent.getAction();
            if("UPDATE_ALL_WEATHERINFO".equals(action)){
                //接到更新通知,更新数据库
                mWeatherInfos=WeatherInfoDB.getInstance().mSelectAllWeatherinfos();
                main_gallery.setSelection(currenPosition);
                mWeatherAdapter.notifyDataSetChanged();
            }else if("UPDATE_ONE_WEATHERINFO".equals(action)){
                //接到更新通知,更新数据库
                mWeatherInfos=WeatherInfoDB.getInstance().mSelectAllWeatherinfos();
                main_gallery.setSelection(currenPosition);
                mWeatherAdapter.notifyDataSetChanged();
                Toast.makeText(mContext,R.string.refreshed,Toast.LENGTH_SHORT).show();
            }else if("SELECT_POSITION".equals(action)){
                currenPosition=intent.getIntExtra("position",0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate...");
        super.onCreate(savedInstanceState);
        //设置视图
        setContentView(R.layout.main);
        //初始化程序的上下文
        mContext=this;

        //导入数据库到终端程序包当中
        CityUtil.getInstance(mContext).importDataBase();
        //初始化网络
        NetworkUtil.getInstance(mContext).getNetWorkState();

        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("UPDATE_ALL_WEATHERINFO");
        intentFilter.addAction("UPDATE_ONE_WEATHERINFO");
        intentFilter.addAction("SELECT_POSITION");
        registerReceiver(mBroadcastReceiver, intentFilter);

        mWeatherInfos=WeatherInfoDB.getInstance().mSelectAllWeatherinfos();
        //初始化视图文件
        initView();
    }

    /**
     * 初始化视图资源
     */
    public void initView()  {
        Log.e(TAG, "initView...");
        main_gallery=(GalleryFlow)findViewById(R.id.main_gallery);
        cityweather_refresh=(ImageButton)findViewById(R.id.cityweather_refresh);
        cityweather_add=(ImageButton)findViewById(R.id.cityweather_add);
        main_add_firstcity=(Button)findViewById(R.id.main_add_firstcity);
        main_first_RelativeLayout =(RelativeLayout)findViewById(R.id.main_first_RelativeLayout);
        main_bg_before=(ImageView)findViewById(R.id.main_bg_before);
        CWV_LinearLayout_top=(LinearLayout)findViewById(R.id.CWV_LinearLayout_top);

        //给控件设置监听事件(点击控,触发事件)
        main_add_firstcity.setOnClickListener(this);
        cityweather_add.setOnClickListener(this);
        cityweather_refresh.setOnClickListener(this);

        //给自定义的GalleryFlow视图绑定adapter(adapter是适配器的意思,是数据库和视图控件之间的数据桥梁)
        mWeatherAdapter=new WeatherAdapter(mContext,mWeatherInfos);
        main_gallery.setAdapter(mWeatherAdapter);
        //Item选中触发事件
        main_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currenPosition=position;
                main_bg_before.setBackgroundResource(mWeatherInfos.size() != 0 ? PictureUtil.getImageId(mWeatherInfos.get(position).getImg1(), 4) : PictureUtil.getImageId(null, 4));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume...");
        if(mWeatherInfos.size()!=0){
            mWeatherInfos=WeatherInfoDB.getInstance().mSelectAllWeatherinfos();
            CWV_LinearLayout_top.setVisibility(View.VISIBLE);
            main_first_RelativeLayout.setVisibility(View.INVISIBLE);
            main_gallery.setSelection(currenPosition);
            mWeatherAdapter.notifyDataSetChanged();
        }else{
            CWV_LinearLayout_top.setVisibility(View.INVISIBLE);
            main_first_RelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        Log.e(TAG,"onDestroy...");
    }

    /**
     * 当前视图的事件监听器
     * @param v 当前Activity的视图
     */
    @Override
    public void onClick(View v) {
        Log.e(TAG,"onClick...");
        switch (v.getId()){
            case R.id.cityweather_add:
                Intent mIntent2=new Intent(mContext,AddCityActivity.class);
                startActivity(mIntent2);
                break;
            case R.id.cityweather_refresh:
                if(NetworkUtil.getInstance(mContext).netSate== NetworkInfo.State.CONNECTED){
                    Toast.makeText(mContext, R.string.refreshing, Toast.LENGTH_SHORT).show();
                    WeatherUtil.getInstance(mContext).getWeatherInfo(mWeatherInfos.get(currenPosition).getCityId());
                }
                break;
            case R.id.main_add_firstcity:
                Intent mIntent1=new Intent(mContext,SelectActivity.class);
                startActivity(mIntent1);
                break;
            default:
                break;
        }
    }

}
