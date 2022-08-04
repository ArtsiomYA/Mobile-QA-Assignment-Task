package app.com.mobileassignment.views.pageObjects;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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
import static app.com.mobileassignment.views.utils.Delay.waitFor;

public class BaseScreen {

    public static void checkIsVisibleElementById(Integer resourceId) {
        onView(allOf(withId(resourceId), isDisplayed()));
    }

    public static void checkIsVisibleElementByText(String text) {
        onView(allOf(withText(text), isDisplayed()));
    }

    public static void checkIsNotVisibleElementById(Integer resourceId) {
        onView(allOf(withText(resourceId), not(isDisplayed())));
    }

    public static void checkIsNotVisibleElementByText(String text) {
        onView(allOf(withText(text), not(isDisplayed())));
    }

    public static void performText(Integer resourceId, String inputText) {
        onView(withId(resourceId))
                .perform(typeText(inputText), closeSoftKeyboard())
                .check(matches(withText(inputText)));
    }

    public static void checkVisibleCitiesInList(Integer resourceId) {
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    public static void checkNotExistSearchCitiesInList(Integer resourceId) {
        onView(withId(resourceId)).check(matches(not(isDisplayed())));
    }

    public static void checkContainsStringInTheChild(Integer resourceCityList,
                                                     Integer resourceCityName,
                                                     String text, int position) {
        onData(anything()).inAdapterView(withId(resourceCityList))
                .atPosition(position)
                .onChildView(withId(resourceCityName))
                .check(matches(withText(containsString(text))));
    }

    public static void waiter(long millis) {
        onView(isRoot()).perform(waitFor(millis));
    }

    public static void clickOnCityInListOfCityByPosition(Integer resourceId, int position) {
        onData(anything()).inAdapterView(withId(resourceId)).atPosition(position)
                .perform(click());
    }

    public static void scrollToElementWithText(String text) {
        onView(withText(containsString(text))).perform(scrollTo()).check(matches(isDisplayed()));
    }

}
