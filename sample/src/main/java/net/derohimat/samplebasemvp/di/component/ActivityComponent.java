package net.derohimat.samplebasemvp.di.component;

import net.derohimat.samplebasemvp.di.ActivityScope;
import net.derohimat.samplebasemvp.view.activity.main.MainActivity;
import net.derohimat.samplebasemvp.view.activity.settings.SettingsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(SettingsActivity settingsActivity);
}