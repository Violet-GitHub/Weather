package solo.robotium.com.qianjiaochen.weather.testcase.selectactivity;

import solo.robotium.com.qianjiaochen.weather.ApplicationTest;
import solo.robotium.com.qianjiaochen.weather.activity.AddCityActivity;
import solo.robotium.com.qianjiaochen.weather.elements.SelectActivityElement;

/**
 * Created by qianjiaochen on 16/8/12.
 */
public class ClickCityListItem extends ApplicationTest {

    public void testClickCityListItem(){
        SelectActivityElement.getInstance(solo).clickCitylistItem(0);
        assertTrue(solo.waitForActivity(AddCityActivity.class, 1000));
    }
}
