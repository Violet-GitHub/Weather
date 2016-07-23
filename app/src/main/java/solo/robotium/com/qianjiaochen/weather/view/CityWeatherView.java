package solo.robotium.com.qianjiaochen.weather.view;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.adpter.CalendarAdapter;

/**
 * Created by qianjiaochen on 16/6/12.
 */
public class CityWeatherView extends RelativeLayout {
    public Context mContext;
    public View mView;

    public TextView cityName,weather_temp,weather,weather_WD,weather_SD,weather_time;
    public ImageView weather_img;

    public TextView currentYM;//当前年月
    public TextView pre,next;//
    public CalendarGallery calendarView;

    public CalendarViewMySelf[] mCalendarViewMySelfs=new CalendarViewMySelf[3];//日历视图集合
    public int view_num=3;//日历视图集合size
    public int preMonth;
    public int preYear;
    public int nextMonth;
    public int nextYear;
    public int curMonth;
    public int curYear;

    public CityWeatherView(Context context) {
        super(context);
        mContext=context;
        initView();
    }

    private void initView(){

        mView= LayoutInflater.from(mContext).inflate(R.layout.city_weather,this,true);

        //初始化天气布局控件
        cityName=(TextView)findViewById(R.id.cityName);
        weather_temp=(TextView)findViewById(R.id.weather_temp);
        weather=(TextView)findViewById(R.id.weather);
        weather_WD=(TextView)findViewById(R.id.weather_WD);
        weather_SD=(TextView)findViewById(R.id.weather_SD);
        weather_time=(TextView)findViewById(R.id.weather_time);
        weather_img=(ImageView)findViewById(R.id.weather_img);

        //初始化日历布局控件
        pre=(TextView)findViewById(R.id.pre);
        next=(TextView)findViewById(R.id.next);
        currentYM=(TextView)findViewById(R.id.currentYM);
        calendarView=(CalendarGallery)findViewById(R.id.calendarView);

        pre.setOnClickListener(mOnClickListener);
        next.setOnClickListener(mOnClickListener);
        currentYM.setOnClickListener(mOnClickListener);

        mCalendarViewMySelfs[0]=new CalendarViewMySelf(mContext);
        mCalendarViewMySelfs[1]=new CalendarViewMySelf(mContext);
        mCalendarViewMySelfs[2]=new CalendarViewMySelf(mContext);

        CalendarAdapter mCalendarAdapter=new CalendarAdapter(this);
        calendarView.setAdapter(mCalendarAdapter);
        calendarView.setSelection(1000);
        calendarView.setOnItemSelectedListener(mOnItemSelectedListener);

        currentYM.setText(getResources().getString(R.string.currentYM,mCalendarViewMySelfs[1].todayYear,mCalendarViewMySelfs[1].todayMonth+1));
    }

    public AdapterView.OnItemSelectedListener mOnItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            curMonth=mCalendarViewMySelfs[position%view_num].curMonth;
            curYear=mCalendarViewMySelfs[position%view_num].curYear;

            if(position%view_num==0){//让[0]中存放当前月份,[2]变成上个月,[1]变成下个月份
                changeCalendarViewMyself(mCalendarViewMySelfs[1],1);
                changeCalendarViewMyself(mCalendarViewMySelfs[2],-1);
            }else if(position%view_num==1){
                changeCalendarViewMyself(mCalendarViewMySelfs[0],-1);
                changeCalendarViewMyself(mCalendarViewMySelfs[2],1);
            }else if(position%view_num==2){//让[2]中变成当前月份;[1]变成上个月份,[0]变成下个月份
                changeCalendarViewMyself(mCalendarViewMySelfs[0],1);
                changeCalendarViewMyself(mCalendarViewMySelfs[1],-1);
            }

            currentYM.setText(getResources().getString(R.string.currentYM,curYear,curMonth+1));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void changeCalendarViewMyself(CalendarViewMySelf calendarViewMySelf,int flag){
        if(flag==-1){
            if(curMonth==0){
                preMonth=11;
                preYear=curYear-1;
            }else{
                preMonth=curMonth-1;
                preYear=curYear;
            }
            calendarViewMySelf.updateCalendar(preYear,preMonth);
        }
        if(flag==1){
            if(curMonth==11){
                nextMonth=0;
                nextYear=curYear+1;
            }else{
                nextMonth=curMonth+1;
                nextYear=curYear;
            }
            calendarViewMySelf.updateCalendar(nextYear,nextMonth);
        }
    }

    /**
     * 点击事件监听器
     */
    public OnClickListener mOnClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pre:
                    calendarView.onPreNextFling(-1);
                    Toast.makeText(mContext,"pre",Toast.LENGTH_SHORT).show();
                case R.id.next:
                    calendarView.onPreNextFling(1);
                    Toast.makeText(mContext,"next",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.currentYM:
                    Toast.makeText(mContext,"currentYM",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

}
