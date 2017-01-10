package net.derohimat.samplebasemvp;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import net.derohimat.samplebasemvp.data.remote.APIService;
import net.derohimat.samplebasemvp.data.remote.model.weather.WeatherPojo;
import net.derohimat.samplebasemvp.view.activity.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String CITY_NAME = "Bandung";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    APIService apiService;

    @Before
    public void setUp() {
        apiService = ((BaseApplication) activityTestRule.getActivity().getApplication()).getApplicationComponent().apiService();
    }

    @Test
    public void correctWeatherDataDisplayed() {
        WeatherPojo weatherData = apiService.getWeatherForCity(CITY_NAME, "metric").toBlocking().first();
        //onView(withId(R.id.button_get_weather)).perform(click());
        onView(withId(R.id.textview_main_city)).check(matches(withText(containsString("Bandung"))));
    }

    @Test
    public void correctOtherShit() {
        WeatherPojo weatherData = apiService.getWeatherForCity(CITY_NAME, "metric").toBlocking().first();
        onView(withId(R.id.button_main_next_days)).perform(click());
    }
}