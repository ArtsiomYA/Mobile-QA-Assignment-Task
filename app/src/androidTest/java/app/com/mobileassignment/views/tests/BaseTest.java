package app.com.mobileassignment.views.tests;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import app.com.mobileassignment.views.MainActivity;
import app.com.mobileassignment.views.pageObjects.ActivityMainScreen;
import app.com.mobileassignment.views.pageObjects.ActivityMapScreen;

public class BaseTest {

    protected ActivityMainScreen activityMainScreen;
    protected ActivityMapScreen activityMapScreen;
    protected Context appContext;
    protected UiDevice device;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityMainScreen = new ActivityMainScreen();
        activityMapScreen = new ActivityMapScreen();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown() {
        mActivityScenarioRule.getScenario().close();
    }

}
