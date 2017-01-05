package net.derohimat.samplebasemvp.view;

import android.os.Bundle;

import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.samplebasemvp.BaseApplication;
import net.derohimat.samplebasemvp.di.component.ActivityComponent;
import net.derohimat.samplebasemvp.di.component.DaggerActivityComponent;

public abstract class MvpActivity extends BaseActivity {

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder().applicationComponent(getApp().getApplicationComponent()).build();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    protected BaseApplication getApp() {
        return (BaseApplication) getApplicationContext();
    }

}
