package net.derohimat.samplebasemvp.view.fragment.detail;

import net.derohimat.baseapp.view.BaseView;
import net.derohimat.samplebasemvp.data.remote.model.forecast.Forecast;

public interface DetailView extends BaseView {

    void showForecast(Forecast forecast);

}