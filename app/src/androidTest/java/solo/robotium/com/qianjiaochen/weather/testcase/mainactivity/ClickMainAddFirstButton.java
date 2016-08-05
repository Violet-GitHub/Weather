package solo.robotium.com.qianjiaochen.weather.testcase.mainactivity;

import solo.robotium.com.qianjiaochen.weather.ApplicationTest;
import solo.robotium.com.qianjiaochen.weather.activity.AddCityActivity;
import solo.robotium.com.qianjiaochen.weather.activity.SelectActivity;
import solo.robotium.com.qianjiaochen.weather.elements.MainActivityElement;

/**
 * Created by qianjiaochen on 16/7/20.
 */
public class ClickMainAddFirstButton extends ApplicationTest{

    public void testClickMainAddFirstButton(){
        MainActivityElement.getInstance(solo).clickMain_add_firstcity();
        assertTrue(solo.waitForActivity(SelectActivity.class, 1000));
    }
}
