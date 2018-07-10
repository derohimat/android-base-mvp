package net.derohimat.samplebasemvp.view.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.derohimat.samplebasemvp.R;
import net.derohimat.samplebasemvp.data.remote.model.weather.WeatherPojo;
import net.derohimat.samplebasemvp.events.MessagesEvent;
import net.derohimat.samplebasemvp.util.DialogFactory;
import net.derohimat.samplebasemvp.util.UnitLocale;
import net.derohimat.samplebasemvp.view.MvpActivity;
import net.derohimat.samplebasemvp.view.activity.settings.SettingsActivity;
import net.derohimat.samplebasemvp.view.fragment.detail.DetailFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends MvpActivity implements MainView {

    private static ProgressBar mProgressBar = null;
    @BindView(R.id.textview_main_city)
    TextView textview_main_city;
    @BindView(R.id.textView_main_conditions)
    TextView textView_main_conditions;
    @BindView(R.id.textView_main_current_temperature)
    TextView textView_main_current_temperature;
    @BindView(R.id.textView_main_min_max)
    TextView textView_main_min_max;
    @BindView(R.id.textView_main_pressure)
    TextView textView_main_pressure;
    @BindView(R.id.textView_main_humidity)
    TextView textView_main_humidity;
    @BindView(R.id.textView_main_wind)
    TextView textView_main_wind;
    @BindView(R.id.imageView_main_icon)
    ImageView imageView_main_icon;
    @BindView(R.id.button_main_next_days)
    Button button_main_next_days;
    @Inject
    EventBus eventBus;
    private MainPresenter mMainPresenter;

    //http://openweathermap.org/weather-conditions
    public static int getIcon(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_thunderstorm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_thunderstorm;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear;
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_cloudy;
        }
        return -1;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.main_activity;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.attachView(this);

        getBaseActionBar().setElevation(0);

        getBaseFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getBaseActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getBaseActionBar().setTitle(getString(R.string.app_name));
                getBaseActionBar().setDisplayHomeAsUpEnabled(false);
            }
        });

        mMainPresenter.loadWeather("Bandung");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @OnClick(R.id.button_main_next_days)
    void onClick_button_main_next_days() {
        getBaseActionBar().setTitle("Next days");
        getBaseFragmentManager().beginTransaction().replace(R.id.container_rellayout, DetailFragment.newInstance(1)).addToBackStack(null).commit();
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_exit:
                finish();
                return true;
            case R.id.action_refresh:
                if (getBaseFragmentManager().getBackStackEntryCount() > 0) {
                    //--- we are in the details fragment.

                } else {
                    //--- we are here
                    mMainPresenter.loadWeather("Bandung");
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        eventBus.unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(MessagesEvent event) {
        if (event.ismSuccess()) {
            DialogFactory.createSimpleOkDialog(mContext, getString(R.string.app_name), event.getMessage()).show();
        } else {
            DialogFactory.showErrorSnackBar(mContext, findViewById(android.R.id.content), new Throwable(event.getMessage())).show();
        }
    }

    @Override
    public void showWeather(WeatherPojo weatherPojo) {

        Timber.d("show Weather %s", weatherPojo.toString());

        textview_main_city.setText(weatherPojo.getName());
        textView_main_current_temperature.setText(String.format("%.1f°", weatherPojo.getMain().getTemp()));
        textView_main_min_max.setText(String.format("%.1f°  %.1f°", weatherPojo.getMain().getTempMin(), weatherPojo.getMain().getTempMax()));
        textView_main_conditions.setText(weatherPojo.getWeather().get(0).getDescription());
        textView_main_humidity.setText(getString(R.string.humidity) + " " + weatherPojo.getMain().getHumidity() + "%");

        String wind_suffix = getResources().getString(R.string.wind_suffix_metric);
        if (UnitLocale.getDefault().equals(UnitLocale.Imperial))
            wind_suffix = getResources().getString(R.string.wind_suffix_imperial);
        textView_main_wind.setText(getString(R.string.wind) + " " + String.valueOf(weatherPojo.getWind().getSpeed()) + wind_suffix);

        textView_main_pressure.setText(getString(R.string.pressure) + " " + weatherPojo.getMain().getPressure() + "hPa");
        imageView_main_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), getIcon(weatherPojo.getWeather().get(0).getId())));
    }

    @Override
    public void showProgress() {
        if (mProgressBar == null) {
            mProgressBar = DialogFactory.DProgressBar(mContext);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
