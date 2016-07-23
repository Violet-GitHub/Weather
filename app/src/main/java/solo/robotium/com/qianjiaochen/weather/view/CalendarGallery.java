package solo.robotium.com.qianjiaochen.weather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * Created by qianjiaochen on 16/7/10.
 */
public class CalendarGallery extends Gallery {


    public CalendarGallery(Context context) {
        super(context);
    }

    public CalendarGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float YFrom = e1.getY();
        float YTo = e2.getY();
        int kEvent;
        if(YTo>YFrom){
            //Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_DOWN;
        }else{
            //Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_UP;
        }
        onKeyDown(kEvent, null);
        return true;
    }

    public boolean onPreNextFling(int flag){
        int kEvent=KeyEvent.KEYCODE_DPAD_DOWN;
        if(flag==-1){
            //Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_UP;
        }else if(flag==1){
            //Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_DOWN;
        }
        onKeyDown(kEvent, null);
        return true;
    }
}
