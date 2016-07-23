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
    public Solo mSolo=null;
    public ImageButton cityweather_add,cityweather_refresh;
    private static MainActivityElement mainActivityElement;

    private MainActivityElement(Solo solo){
        mSolo=solo;
        init();
    }
    public static MainActivityElement getInstance(Solo solo){
        if(mainActivityElement==null){
            mainActivityElement=new MainActivityElement(solo);
        }
        return mainActivityElement;
    }

    public void init(){
        cityweather_add=(ImageButton)mSolo.getCurrentActivity().findViewById(R.id.cityweather_add);
        cityweather_refresh=(ImageButton)mSolo.getCurrentActivity().findViewById(R.id.cityweather_refresh);
    }

    public void clickCityweather_add(){
        mSolo.clickOnView(cityweather_add);
    }

    public void clickCityweather_refresh(){
        mSolo.clickOnView(cityweather_refresh);
    }

}
