package net.derohimat.samplebasemvp.di.component;

import net.derohimat.samplebasemvp.BaseApplication;
import net.derohimat.samplebasemvp.data.local.PreferencesHelper;
import net.derohimat.samplebasemvp.data.remote.APIService;
import net.derohimat.samplebasemvp.data.remote.UnauthorisedInterceptor;
import net.derohimat.samplebasemvp.di.module.ApplicationModule;
import net.derohimat.samplebasemvp.view.activity.main.MainPresenter;
import net.derohimat.samplebasemvp.view.fragment.detail.DetailPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainPresenter mainPresenter);

    void inject(DetailPresenter detailPresenter);

    void inject(BaseApplication baseApplication);

    void inject(UnauthorisedInterceptor unauthorisedInterceptor);

    APIService apiService();

    EventBus eventBus();

    PreferencesHelper prefsHelper();

}