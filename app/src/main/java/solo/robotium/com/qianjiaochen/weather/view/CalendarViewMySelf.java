package solo.robotium.com.qianjiaochen.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.model.CalendarInfo;
import solo.robotium.com.qianjiaochen.weather.util.CalendarUtil;

/**
 * Created by qianjiaochen on 16/7/11.
 */
public class CalendarViewMySelf extends View{
    public int widthSize;
    public int heightSize;
    public int calendarItemWidth;
    public int calendarItemHeight;
    public int offSetW;
    public int offSetH;
    public int weekItemHeight;
    public int weekItemWidth;
    public String[] weeks;//数组{日,一,二,三,四,五,六}
    public Paint mPaint;
    public Context mContext;
    public CalendarUtil mCalendarUtil;
    public int todayYear;
    public int todayMonth;
    public int curYear;
    public int curMonth;
    public Calendar mCalendar;
    public CalendarInfo[][] mCalendarInfos=null;



    public CalendarViewMySelf(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public CalendarViewMySelf(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public CalendarViewMySelf(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    public void init(){
        mPaint=new Paint();
        //获取当前月份的日历二维数组
        mCalendarUtil=new CalendarUtil();
        mCalendar= Calendar.getInstance();
        todayYear=mCalendar.get(Calendar.YEAR);
        todayMonth=mCalendar.get(Calendar.MONTH);
        curYear=todayYear;//初始化当前年份,在切换当前日历视图的时候,可以再次基础上进行修改
        curMonth=todayMonth;//初始化当前月份,在切换当前日历视图的时候,可以再次基础上进行修改
        mCalendarInfos=mCalendarUtil.updateCalendar(todayYear,todayMonth);
        invalidate();
    }

    /**
     * 更新view
     * @param year
     * @param month
     */
    public void updateCalendar(int year ,int month){
        mCalendarInfos=mCalendarUtil.updateCalendar(year,month);
        curYear=year;
        curMonth=month;
        //调用父类方法,绘制视图;
        this.invalidate();
    }

    /**
     * 真正地开始对视图进行绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWeek(canvas);
        drawCalendar(canvas);
    }

    public void drawWeek(Canvas canvas){
        weeks=getResources().getStringArray(R.array.head_week);
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setTextSize(40);
        for(int i=0;i<7;i++){
            int iPosX=i*weekItemWidth;
            int iposY=weekItemHeight;
            canvas.drawText(weeks[i],iPosX,iposY,mPaint);
        }
    }

    public void drawCalendar(Canvas canvas){
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                CalendarInfo myCalendarInfo=mCalendarInfos[i][j];
                mPaint.setTextSize(40);
                int iPosX=j*calendarItemWidth;
                int iPosY=(i+1)*calendarItemHeight+weekItemHeight;
                if(myCalendarInfo.currentMonth){
                    if (j == 0 || j == 6) {
                        mPaint.setColor(Color.parseColor("#fd3434"));
                    } else {
                        mPaint.setColor(Color.parseColor("#000000"));
                    }
                }else{
                    mPaint.setColor(Color.parseColor("#a2a1a1"));
                }
                canvas.drawText(myCalendarInfo.day+"",iPosX,iPosY,mPaint);
            }
        }
    }

    /**
     * 用于测量视图的大小的
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize=MeasureSpec.getSize(widthMeasureSpec);
        heightSize=MeasureSpec.getSize(heightMeasureSpec);
        calendarItemWidth=weekItemWidth=widthSize/7;
        calendarItemHeight=weekItemHeight=heightSize/7;
        offSetH=widthSize%7;
        offSetW=heightSize%7;
    }


    /**
     * 用于给视图进行布局的，也就是确定视图的位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


}
