package solo.robotium.com.qianjiaochen.weather.elements;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;

import com.robotium.solo.Solo;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.activity.MainActivity;

/**
 * Created by qianjiaochen on 16/7/20.
 */
public class MainActivityElement {
    public Solo solo=null;
    public ImageButton cityweather_add,cityweather_refresh;
    public Button main_add_firstcity;
    private static MainActivityElement mainActivityElement;

    private MainActivityElement(Solo msolo){
        solo=msolo;
        init();
    }
    public static MainActivityElement getInstance(Solo msolo){
        if(mainActivityElement==null){
            mainActivityElement=new MainActivityElement(msolo);
        }
        return mainActivityElement;
    }

    public void init(){
        cityweather_add=(ImageButton)solo.getCurrentActivity().findViewById(R.id.cityweather_add);
        cityweather_refresh=(ImageButton)solo.getCurrentActivity().findViewById(R.id.cityweather_refresh);
        main_add_firstcity=(Button)solo.getCurrentActivity().findViewById(R.id.main_add_firstcity);
    }


    public void clickMain_add_firstcity(){
        solo.clickOnView(main_add_firstcity);
    }

    public void clickCityweather_add(){
        solo.clickOnView(cityweather_add);
    }

    public void clickCityweather_refresh(){
        solo.clickOnView(cityweather_refresh);
    }

}
