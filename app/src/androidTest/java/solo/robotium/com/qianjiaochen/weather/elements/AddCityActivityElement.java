package solo.robotium.com.qianjiaochen.weather.elements;

import android.view.View;
import android.widget.GridView;

import com.robotium.solo.Solo;

import java.util.ArrayList;

import solo.robotium.com.qianjiaochen.weather.R;
import solo.robotium.com.qianjiaochen.weather.view.AddCityView;

/**
 * Created by qianjiaochen on 16/7/20.
 */
public class AddCityActivityElement {
    public Solo solo=null;
    public GridView mGridView;
    public static AddCityActivityElement addCityActivityElement;

    public AddCityActivityElement(Solo mySolo){
        solo=mySolo;
        init();
    }

    public static AddCityActivityElement getInstance(Solo mySolo){
        if(addCityActivityElement==null){
            addCityActivityElement=new AddCityActivityElement(mySolo);
        }
        return addCityActivityElement;
    }

    public void init(){
        mGridView=(GridView)solo.getCurrentActivity().findViewById(R.id.mGridView);
    }

    public void clickGridViewItem(int index){
        //获得子view
        ArrayList<View> childView = solo.getViews(mGridView);
        View  addCityView = childView.get(index);
        solo.clickOnView(addCityView);
    }


}
