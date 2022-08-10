package app.com.mobileassignment.views.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import app.com.mobileassignment.views.MainActivity;
import app.com.mobileassignment.views.pageObjects.ActivityMainScreen;
import app.com.mobileassignment.views.pageObjects.ActivityMapScreen;

public class BaseTest {

    protected ActivityMainScreen activityMainScreen;
    protected ActivityMapScreen activityMapScreen;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void init() {
        activityMainScreen = new ActivityMainScreen();
        activityMapScreen = new ActivityMapScreen();
    }

    @After
    public void destroy() {
        mActivityScenarioRule.getScenario().close();
    }

}
