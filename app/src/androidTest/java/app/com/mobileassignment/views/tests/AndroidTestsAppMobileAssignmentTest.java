package app.com.mobileassignment.views.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.views.MainActivity;
import app.com.mobileassignment.views.pageObjects.ActivityMainScreen;
import app.com.mobileassignment.views.pageObjects.ActivityMapScreen;
import io.qameta.allure.Description;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AndroidTestsAppMobileAssignmentTest extends TestCase {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    @Description("Checking the display of the search field on the main screen")
    public void testCheckDisplayedSearchFieldIsPresent() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getSearchElement());
    }

    @Test
    @Description("Checking the display of the list of cities on the main screen")
    public void testCheckCitiesListElementIsPresent() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getCitiesListElement());
    }

    @Test
    @Description("Checking data entry in the search field on the main page")
    public void testCheckInputCityInTheSearchField() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getSearchElement());
        ActivityMainScreen.performText(ActivityMainScreen.getSearchElement(), "Minsk");
        ActivityMainScreen.checkIsVisibleElementByText("Minsk");
    }

    @Test
    @Description("Displaying search results on valid data on the main page")
    public void testCheckDisplayInputDataInTheSearchFieldOnResult() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getSearchElement());
        ActivityMainScreen.performText(ActivityMainScreen.getSearchElement(), "testing");
        ActivityMainScreen.waiter(3000);
        ActivityMainScreen.checkContainsStringInTheChild(ActivityMainScreen.getCitiesListElement(),
                ActivityMainScreen.getCityName(), "testing", 0);
    }

    @Test
    @Description("Displaying search results on invalid data on the main page")
    public void testCheckDisplayInputInvalidDataInTheSearchFieldOnResult() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getSearchElement());
        ActivityMainScreen.performText(ActivityMainScreen.getSearchElement(), "11112222333");
        ActivityMainScreen.waiter(2000);
        ActivityMainScreen.checkNotExistSearchCitiesInList(ActivityMainScreen.getCitiesListElement());
    }

    @Test
    @Description("Checking the city selection from the list")
    public void testCheckClickOnFoundCityInListOfCities() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getSearchElement());
        ActivityMainScreen.performText(ActivityMainScreen.getSearchElement(), "Minsk");
        ActivityMainScreen.waiter(3000);
        ActivityMainScreen.clickOnCityInListOfCityByPosition(ActivityMainScreen
                .getCitiesListElement(), 0);
        ActivityMainScreen.checkIsNotVisibleElementById(ActivityMainScreen.getSearchElement());
        ActivityMapScreen.checkIsVisibleElementById(ActivityMapScreen.getInsertPoint());
    }

    @Test
    @Description("Checking invalid data entry in the search field on the main page")
    public void checkInputInvalidDataInSearchField() {
        ActivityMainScreen.checkIsVisibleElementById(ActivityMainScreen.getSearchElement());
        ActivityMainScreen.performText(ActivityMainScreen.getSearchElement(), "11112222333");
        ActivityMainScreen.checkIsVisibleElementByText("11112222333");
    }

}
