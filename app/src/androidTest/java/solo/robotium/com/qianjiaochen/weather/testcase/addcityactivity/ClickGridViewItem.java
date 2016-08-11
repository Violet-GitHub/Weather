package solo.robotium.com.qianjiaochen.weather.testcase.addcityactivity;

import solo.robotium.com.qianjiaochen.weather.ApplicationTest;
import solo.robotium.com.qianjiaochen.weather.activity.MainActivity;
import solo.robotium.com.qianjiaochen.weather.elements.AddCityActivityElement;

/**
 * Created by qianjiaochen on 16/8/12.
 */
public class ClickGridViewItem extends ApplicationTest{
    public void testGridViewItem(){
        AddCityActivityElement.getInstance(solo).clickGridViewItem(0);
        assertTrue(solo.waitForActivity(MainActivity.class, 1000));
    }
}
