package solo.robotium.com.qianjiaochen.weather;

import android.os.PowerManager;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

import solo.robotium.com.qianjiaochen.weather.activity.MainActivity;
import solo.robotium.com.qianjiaochen.weather.util.NetworkUtilTest;
import solo.robotium.com.qianjiaochen.weather.util.Util;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public Solo solo=null;
    public PowerManager.WakeLock wakelockobject;
    public UIHelper uiHelper;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        try {
            super.setUp();
            init();
        } catch (Throwable tr) {
            solo.takeScreenshot(this.getClass().getSimpleName());
            throw new SetUpException(tr);
        }

    }

    @Override
    protected void runTest() throws Throwable {
        try {
            super.runTest();
        } catch (Throwable tr) {
            solo.takeScreenshot(this.getClass().getSimpleName());
            throw new RunTestException(tr);
        }

    }

    public void init(){
        solo=new Solo(getInstrumentation(),getActivity());

        //获取屏幕
        if (wakelockobject==null) {
            wakelockobject= Util.wakeScreen(this);
        }
        //解锁
        Util.unlock(getInstrumentation());
        //连接网络
        NetworkUtilTest.setAirplaneModeOffAndNetworkOn(getInstrumentation().getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        try {
            if (wakelockobject != null ) {
                wakelockobject.release();
                wakelockobject=null;
            }
            solo.finishOpenedActivities();
            uiHelper=null;
            super.tearDown();
        } catch (Throwable tr) {
            solo.takeScreenshot(this.getClass().getSimpleName());
            throw new TearDownException(tr);
        }
    }


    @Override
    public void runBare() throws Throwable {
        try {
            super.runBare();
        } catch (SetUpException sue) {
            tearDown();
            throw sue;
        }catch (RunTestException rte) {
            tearDown();
            throw rte;
        }catch (TearDownException tde) {
            tearDown();
            throw tde;
        }
    }


    class SetUpException extends Exception{
        public static final long serialVersionUID = 1L;
        public SetUpException(Throwable e) {
            super("Errer in BasicTestCase.setUp()! ",e);
        }
    }

    class RunTestException extends Exception{
        public static final long serialVersionUID = 2L;
        public RunTestException(Throwable e) {
            super("Errer in BasicTestCase.runTest()!",e);
        }
    }
    class TearDownException extends Exception{
        public static final long serialVersionUID = 3L;
        public TearDownException(Throwable e) {
            super("Errer in BasicTestCase.tearDown()!", e);
        }
    }

}