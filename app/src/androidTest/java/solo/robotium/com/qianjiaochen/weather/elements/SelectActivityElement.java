package solo.robotium.com.qianjiaochen.weather.elements;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;

/**
 * Created by qianjiaochen on 16/7/20.
 */
public class SelectActivityElement {
    public Solo solo=null;
    public TextView input_name;
    public Button search_city;
    public GridView citylist;
    public static SelectActivityElement selectActivityElement;

    public SelectActivityElement(Solo mySolo){
        solo=mySolo;
        init();
    }

    public static SelectActivityElement getInstance(Solo mySolo){
        if(selectActivityElement==null){
            selectActivityElement=new SelectActivityElement(mySolo);
        }
        return selectActivityElement;
    }

    public void init(){
        input_name=(TextView)solo.getCurrentActivity().findViewById(R.id.input_name);
        search_city=(Button)solo.getCurrentActivity().findViewById(R.id.search_city);
        citylist=(GridView)solo.getCurrentActivity().findViewById(R.id.citylist);
    }

    public void clickCitylistItem(int index){
        //获得子view
        ArrayList<View> childView = solo.getViews(citylist);
        View  cityName = childView.get(index);
        solo.clickOnView(cityName);
    }

    public void enterInput_name(String str){
        solo.enterText((EditText) input_name,str);
    }

    public void clickSearch_city(){
        solo.clickOnView(search_city);
    }

}
