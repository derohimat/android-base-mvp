package net.derohimat.samplebasemvp;

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