package solo.robotium.com.qianjiaochen.weather.util;

import java.util.Arrays;
import java.util.List;

import solo.robotium.com.qianjiaochen.weather.R;

/**
 * Created by qianjiaochen on 16/6/12.
 */
public class PictureUtil {
    //晴天
    private static List<String> list1 = Arrays.asList("n0.gif");
    //多云
    private static List<String> list2 = Arrays.asList("n2.gif","n1.gif");
    //阴天
    private static List<String> list3 = Arrays.asList("n20.gif","n29.gif","n30.gif","n31.gif");
    //雨水
    private static List<String> list4 = Arrays.asList("n3.gif","n9.gif","n5.gif","n6.gif","n7.gif","n8.gif","n10.gif","n11.gif","n99.gif",
            "n12.gif","n19.gif","n21.gif","n22.gif","n23.gif","n24.gif","n25.gif");
    //下雪
    private static List<String> list5 = Arrays.asList("n13.gif","n14.gif","n15.gif","n16.gif","n17.gif","n26.gif","n27.gif","n28.gif");

    //雷雨
    private static List<String> list6 = Arrays.asList("n4.gif");


    /**
     * 天气图
     * @param imageId
     * @return
     */
    public static int getImageId(String imageId,int imageSize){

        if(list1.indexOf(imageId)!=-1){
            if(imageSize==1){
                return R.mipmap.sun_1;
            }else if(imageSize==2){
                return R.mipmap.sun_2;
            }else if(imageSize==3){
                return R.mipmap.sun_3;
            }else if(imageSize==4){
                return R.mipmap.big_sun;
            }
        }else if(list2.indexOf(imageId)!=-1){
            if(imageSize==1){
                return R.mipmap.cloudy_1;
            }else if(imageSize==2){
                return R.mipmap.cloudy_2;
            }else if(imageSize==3){
                return R.mipmap.cloudy_3;
            }else if(imageSize==4){
                return R.mipmap.big_cloudy;
            }
        }else if(list3.indexOf(imageId)!=-1){
            if(imageSize==1){
                return R.mipmap.cloudy_rain_1;
            }else if(imageSize==2){
                return R.mipmap.cloudy_rain_2;
            }else if(imageSize==3){
                return R.mipmap.cloudy_rain_3;
            }else if(imageSize==4){
                return R.mipmap.big_cloudy_rain;
            }
        }else if(list4.indexOf(imageId)!=-1){
            if(imageSize==1){
                return R.mipmap.rain_1;
            }else if(imageSize==2){
                return R.mipmap.rain_2;
            }else if(imageSize==3){
                return R.mipmap.rain_3;
            }else if(imageSize==4){
                return R.mipmap.big_rain;
            }
        }else if(list5.indexOf(imageId)!=-1){
            if(imageSize==1){
                return R.mipmap.snow_1;
            }else if(imageSize==2){
                return R.mipmap.snow_2;
            }else if(imageSize==3){
                return R.mipmap.snow_3;
            }else if(imageSize==4){
                return R.mipmap.big_snow;
            }
        }else if(list6.indexOf(imageId)!=-1){
            if(imageSize==1){
                return R.mipmap.mine_rain_1;
            }else if(imageSize==2){
                return R.mipmap.mine_rain_2;
            }else if(imageSize==3){
                return R.mipmap.mine_rain_3;
            }else if(imageSize==4){
                return R.mipmap.big_rain;
            }
        }else{
            if(imageSize==1){
                return R.mipmap.sun_1;
            }else if(imageSize==2){
                return R.mipmap.sun_2;
            }else if(imageSize==3){
                return R.mipmap.sun_3;
            }else if(imageSize==4){
                return R.mipmap.big_sun;
            }
        }

        return -1;

    }

}
