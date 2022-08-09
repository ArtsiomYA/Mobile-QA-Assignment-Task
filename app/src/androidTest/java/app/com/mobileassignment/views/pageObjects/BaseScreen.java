package app.com.mobileassignment.views.pageObjects;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import app.com.mobileassignment.views.utils.Delay;

public class BaseScreen {

    private final Delay delay = new Delay();

    public void checkIsVisibleElementById(Integer resourceId) {
        onView(allOf(withId(resourceId), isDisplayed()));
    }

    public void checkIsVisibleElementByText(String text) {
        onView(allOf(withText(text), isDisplayed()));
    }

    public void checkIsNotVisibleElementById(Integer resourceId) {
        onView(allOf(withText(resourceId), not(isDisplayed())));
    }

    public void checkIsNotVisibleElementByText(String text) {
        onView(allOf(withText(text), not(isDisplayed())));
    }

    public void performText(Integer resourceId, String inputText) {
        onView(withId(resourceId))
                .perform(typeText(inputText), closeSoftKeyboard())
                .check(matches(withText(inputText)));
    }

    public void checkVisibleCitiesInList(Integer resourceId) {
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    public void checkNotExistSearchCitiesInList(Integer resourceId) {
        onView(withId(resourceId)).check(matches(not(isDisplayed())));
    }

    public void checkContainsStringInTheChild(Integer resourceCityList,
                                                     Integer resourceCityName,
                                                     String text, int position) {
        onData(anything()).inAdapterView(withId(resourceCityList))
                .atPosition(position)
                .onChildView(withId(resourceCityName))
                .check(matches(withText(containsString(text))));
    }

    public void waiter(long millis) {
        onView(isRoot()).perform(delay.waitFor(millis));
    }

    public void clickOnCityInListOfCityByPosition(Integer resourceId, int position) {
        onData(anything()).inAdapterView(withId(resourceId)).atPosition(position)
                .perform(click());
    }

}
