package solo.robotium.com.qianjiaochen.weather.model;

import java.io.Serializable;

/**
 * Created by qianjiaochen on 16/6/14.
 */
public class CityInfo implements Serializable{

    public String cityNmame;
    public String cityID;

    public String getCityNmame() {
        return cityNmame;
    }

    public void setCityNmame(String cityNmame) {
        this.cityNmame = cityNmame;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }
}
