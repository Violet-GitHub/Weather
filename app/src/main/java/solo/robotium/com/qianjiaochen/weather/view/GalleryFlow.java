package solo.robotium.com.qianjiaochen.weather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class GalleryFlow extends Gallery{


    public GalleryFlow(Context context) {
        super(context);
    }
    public GalleryFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public GalleryFlow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
        float XFrom = e1.getX();
        float XTo = e2.getX();
        int kEvent;
        if(XTo>XFrom){
            //Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        }else{
            //Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
        return true;
    }

}
