package solo.robotium.com.qianjiaochen.weather.testcase.mainactivity;

import android.content.res.Resources;

import solo.robotium.com.qianjiaochen.weather.ApplicationTest;
import solo.robotium.com.qianjiaochen.weather.activity.AddCityActivity;
import solo.robotium.com.qianjiaochen.weather.elements.MainActivityElement;

/**
 * Created by qianjiaochen on 16/7/23.
 */
public class ClickRefreshButton extends ApplicationTest {

    public void testRefreshButton(){
        MainActivityElement.getInstance(solo).clickCityweather_refresh();
        assertTrue(solo.waitForText("正在刷新..."));
    }
}
