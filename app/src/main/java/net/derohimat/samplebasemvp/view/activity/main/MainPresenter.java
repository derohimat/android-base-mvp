package net.derohimat.samplebasemvp.view.activity.main;

import android.content.Context;

import net.derohimat.baseapp.presenter.BasePresenter;
import net.derohimat.samplebasemvp.BaseApplication;
import net.derohimat.samplebasemvp.R;
import net.derohimat.samplebasemvp.data.remote.APIService;
import net.derohimat.samplebasemvp.events.MessagesEvent;
import net.derohimat.samplebasemvp.model.weather.WeatherPojo;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class MainPresenter implements BasePresenter<MainMvpView> {

    @Inject
    public MainPresenter(Context context) {
        ((BaseApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Inject
    APIService mAPIService;
    @Inject
    EventBus mEventBus;

    private MainMvpView mMainMvpView;
    private Subscription mSubscription;
    private WeatherPojo mWeatherPojo;

    @Override
    public void attachView(MainMvpView view) {
        mMainMvpView = view;
    }

    @Override
    public void detachView() {
        mMainMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadWeather(String from_where) {

        String weatherFromWhere = from_where.trim();
        if (weatherFromWhere.isEmpty()) return;

        mMainMvpView.showProgress();
        if (mSubscription != null) mSubscription.unsubscribe();

        BaseApplication baseApplication = BaseApplication.get(mMainMvpView.getContext());

        mSubscription = mAPIService.getWeatherForCity(weatherFromWhere, "metric")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(new Subscriber<WeatherPojo>() {
                    @Override
                    public void onCompleted() {
                        Timber.i("Weather loaded " + mWeatherPojo);
                        mMainMvpView.showWeather(mWeatherPojo);
                        mMainMvpView.hideProgress();
                        mMainMvpView.showWeather(mWeatherPojo);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e("Error loading weather", error);
                        if (isHttp404(error)) {
                            mEventBus.post(new MessagesEvent(false, baseApplication.getString(R.string.error_not_found)));
                        } else {
                            mEventBus.post(new MessagesEvent(false, baseApplication.getString(R.string.error_loading_weather)));
                        }

                        mMainMvpView.hideProgress();
                    }

                    @Override
                    public void onNext(WeatherPojo weatherPojo) {
                        mWeatherPojo = weatherPojo;
                    }
                });
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }
}