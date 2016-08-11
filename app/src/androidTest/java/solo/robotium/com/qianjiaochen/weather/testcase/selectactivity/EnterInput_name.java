package solo.robotium.com.qianjiaochen.weather.testcase.selectactivity;

import solo.robotium.com.qianjiaochen.weather.ApplicationTest;
import solo.robotium.com.qianjiaochen.weather.activity.AddCityActivity;
import solo.robotium.com.qianjiaochen.weather.elements.SelectActivityElement;

/**
 * Created by qianjiaochen on 16/8/12.
 */
public class EnterInput_name extends ApplicationTest {

    public void testEnterInput_name(){
        SelectActivityElement.getInstance(solo).enterInput_name("zhuhai");
        SelectActivityElement.getInstance(solo).clickSearch_city();
        SelectActivityElement.getInstance(solo).clickCitylistItem(0);
        assertTrue(solo.waitForActivity(AddCityActivity.class, 1000));
    }


}
