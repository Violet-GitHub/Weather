package solo.robotium.com.qianjiaochen.weather.testcase.mainactivity;

import solo.robotium.com.qianjiaochen.weather.ApplicationTest;
import solo.robotium.com.qianjiaochen.weather.activity.AddCityActivity;
import solo.robotium.com.qianjiaochen.weather.elements.MainActivityElement;

/**
 * Created by qianjiaochen on 16/7/20.
 */
public class ClickAddButton extends ApplicationTest{

    public void testClickAddButton(){
        MainActivityElement.getInstance(solo).clickCityweather_add();
        assertTrue(solo.waitForActivity(AddCityActivity.class, 1000));
    }
}
