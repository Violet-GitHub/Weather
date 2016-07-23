package solo.robotium.com.qianjiaochen.weather.util;

import android.util.Log;

import java.util.Calendar;

import solo.robotium.com.qianjiaochen.weather.model.CalendarInfo;

/**
 * Created by qianjiaochen on 16/7/10.
 */
public class CalendarUtil {
    public String TAG="CalendarUtil";
    public CalendarInfo[][] calendarInfos=new CalendarInfo[6][7];
    public int[] LEAP_YEAR=new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
    public int[] COMM_YEAR=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

//    public static void main(String[] args){
//        CalendarUtil mCalendarUtil=new CalendarUtil();
//        mCalendarUtil.updateCalendar(2016,6);
//    }

    public CalendarInfo[][] updateCalendar(int year,int month){
        int currentMonthDays=0;//当前月
        int preYear=0;//上个月所在年份
        int preMonth=0;//上个月
        int preMonthDays=0;//上个月天数

        //calendar 按照0-11月份来算
        Calendar myCalendar=Calendar.getInstance();
        myCalendar.set(year,month,1);
        int week_num=(int)myCalendar.get(Calendar.DAY_OF_WEEK)-1;

        //上个月所在月份和年份
        if(month==0) {
            preMonth = 11;
            preYear = year - 1;
        }else{
            preMonth=month-1;
            preYear=year;
        }

        //上个月天数
        if((preYear%4==0&&preYear%100!=0)||preYear%400==0){
            preMonthDays=LEAP_YEAR[preMonth];
        }else{
            preMonthDays=COMM_YEAR[preMonth];
        }

        //这个月天数
        if((year%4==0&&year%100!=0)||preYear%400==0){
            currentMonthDays=LEAP_YEAR[month];
        }else{
            currentMonthDays=COMM_YEAR[month];
        }

        //二维数组的位置
        int currIndex=0;
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                CalendarInfo myCalendatInfo=new CalendarInfo();
                if(currIndex<week_num){
                    myCalendatInfo.day=preMonthDays-week_num+currIndex+1;
                    myCalendatInfo.currentMonth=false;
                }else if(currIndex>=week_num&&currIndex<(week_num+currentMonthDays)){
                    myCalendatInfo.day=currIndex-week_num+1;
                    myCalendatInfo.currentMonth=true;
                }else{
                    myCalendatInfo.day=currIndex-week_num-currentMonthDays+1;
                    myCalendatInfo.currentMonth=false;
                }
                calendarInfos[i][j]=myCalendatInfo;
                //System.out.print(calendarInfos[i][j].day+",");
                currIndex++;
            }
        }

        return calendarInfos;
    }

}
