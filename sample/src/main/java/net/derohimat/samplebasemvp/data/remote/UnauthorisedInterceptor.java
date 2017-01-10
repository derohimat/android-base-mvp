package net.derohimat.samplebasemvp.data.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import net.derohimat.samplebasemvp.BaseApplication;
import net.derohimat.samplebasemvp.events.AuthenticationErrorEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnauthorisedInterceptor implements Interceptor {

    @Inject
    EventBus eventBus;

    public UnauthorisedInterceptor(Context context) {
        BaseApplication.get(context).getApplicationComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {
            new Handler(Looper.getMainLooper()).post(() -> eventBus.post(new AuthenticationErrorEvent()));
        }
        return response;
    }
}