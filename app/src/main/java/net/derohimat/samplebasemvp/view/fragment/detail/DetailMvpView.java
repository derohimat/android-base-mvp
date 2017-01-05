package net.derohimat.samplebasemvp.view.fragment.detail;

import net.derohimat.samplebasemvp.data.remote.model.forecast.Forecast;
import net.derohimat.samplebasemvp.view.MvpView;

public interface DetailMvpView extends MvpView {

    void showForecast(Forecast forecast);

}