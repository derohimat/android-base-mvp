package net.derohimat.samplebasemvp.view.activity.main;

import net.derohimat.baseapp.view.BaseView;
import net.derohimat.samplebasemvp.data.remote.model.weather.WeatherPojo;

public interface MainView extends BaseView {

    void showWeather(WeatherPojo weatherPojo);

    void showProgress();

    void hideProgress();
}