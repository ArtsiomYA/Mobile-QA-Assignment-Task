package app.com.mobileassignment.views.pageObjects;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThrows;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matcher;

import app.com.mobileassignment.views.utils.Waiters;

public class BaseScreen {
    private final long DEFAULT_TIMEOUT = 5000;

    public void checkIsVisibleElementById(Integer resourceId) {
        onView(isRoot()).perform(Waiters.waiter(resourceId, DEFAULT_TIMEOUT));
        onView(allOf(withId(resourceId), isDisplayed()));
    }

    public void checkIsVisibleElementByText(String text) {
        onView(isRoot()).perform(Waiters.waiter(text, DEFAULT_TIMEOUT));
        onView(allOf(withText(text), isDisplayed()));
    }

    public void checkIsNotVisibleElementById(Integer resourceId) {
        onView(allOf(withId(resourceId), not(isDisplayed())));
    }

    public void performText(Integer resourceId, String inputText) {
        onView(isRoot()).perform(Waiters.waiter(resourceId, DEFAULT_TIMEOUT));
        onView(withId(resourceId))
                .perform(typeText(inputText), closeSoftKeyboard())
                .check(matches(withText(inputText)));
    }

    public void checkNotExistSearchCitiesInList(Integer resourceId) {
        onView(withId(resourceId)).check(matches(not(isDisplayed())));
    }

    public void checkContainsStringInTheChild(Integer resourceCityList,
                                                     Integer resourceCityName,
                                                     String text, int position) {
        onView(isRoot()).perform(Waiters.waiter(resourceCityList, DEFAULT_TIMEOUT));
        onData(anything()).inAdapterView(withId(resourceCityList))
                .atPosition(position)
                .onChildView(withId(resourceCityName))
                .check(matches(withText(containsString(text))));
    }

    public boolean checkExceptionOccurrence(Integer resourceId, String data) {
        String message = null;
        try {
            assertThrows(RuntimeException.class, () -> performText(resourceId, data));
        } catch (Exception exception) {
            message = exception.getMessage();
        }
        return message == null;
    }

    public void clickOnCityInListOfCityByPosition(Integer resourceId, int position) {
        onView(isRoot()).perform(Waiters.waiter(resourceId, DEFAULT_TIMEOUT));
        onData(anything()).inAdapterView(withId(resourceId)).atPosition(position)
                .perform(click());
    }

    public ViewInteraction getViewById(Integer resourceId) {
        return onView(withId(resourceId));
    }

    public String getText(ViewInteraction matcher){
        final String[] text = new String[1];
        ViewAction va = new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription(){
                return "Text of the view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                text[0] = tv.getText().toString();
            }
        };

        matcher.perform(va);

        return text[0];
    }

    public boolean netConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static void lockPhoneScreen(UiDevice device) {
        try {
            device.sleep();
            device.waitForIdle(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unLockPhoneScreen(UiDevice device) {
        try {
            device.wakeUp();
            device.waitForIdle(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
