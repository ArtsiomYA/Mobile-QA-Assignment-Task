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

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import app.com.mobileassignment.views.utils.Waiters;

public class BaseScreen {

    public void checkIsVisibleElementById(Integer resourceId) {
        onView(isRoot()).perform(Waiters.waiter(resourceId, 3000));
        onView(allOf(withId(resourceId), isDisplayed()));
    }

    public void checkIsVisibleElementByText(String text) {
        onView(isRoot()).perform(Waiters.waiter(text, 3000));
        onView(allOf(withText(text), isDisplayed()));
    }

    public void checkIsNotVisibleElementById(Integer resourceId) {
        onView(allOf(withId(resourceId), not(isDisplayed())));
    }

    public void performText(Integer resourceId, String inputText, long millis) {
        onView(isRoot()).perform(Waiters.waiter(resourceId, millis));
        onView(withId(resourceId))
                .perform(typeText(inputText), closeSoftKeyboard())
                .check(matches(withText(inputText)));
    }

    public void checkNotExistSearchCitiesInList(Integer resourceId) {
        onView(withId(resourceId)).check(matches(not(isDisplayed())));
    }

    public void checkContainsStringInTheChild(Integer resourceCityList,
                                                     Integer resourceCityName,
                                                     String text, int position, long millis) {
        onView(isRoot()).perform(Waiters.waiter(resourceCityList, millis));
        onData(anything()).inAdapterView(withId(resourceCityList))
                .atPosition(position)
                .onChildView(withId(resourceCityName))
                .check(matches(withText(containsString(text))));
    }

    public void clickOnCityInListOfCityByPosition(Integer resourceId, int position, long millis) {
        onView(isRoot()).perform(Waiters.waiter(resourceId, millis));
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

}
