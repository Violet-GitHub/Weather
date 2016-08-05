package solo.robotium.com.qianjiaochen.weather.runner;

import com.zutubi.android.junitreport.JUnitReportTestRunner;

import junit.framework.TestSuite;

import solo.robotium.com.qianjiaochen.weather.testcase.mainactivity.ClickAddButton;
import solo.robotium.com.qianjiaochen.weather.testcase.mainactivity.ClickMainAddFirstButton;
import solo.robotium.com.qianjiaochen.weather.testcase.mainactivity.ClickRefreshButton;

/**
 * Created by qianjiaochen on 16/7/20.
 */
public class WeatherRunner extends JUnitReportTestRunner{
    public TestSuite getTestSuite(){
        TestSuite suite=new TestSuite();
        suite.addTestSuite(ClickMainAddFirstButton.class);
        //suite.addTestSuite(ClickAddButton.class);
        //suite.addTestSuite(ClickRefreshButton.class);
        return suite;
    }
}
