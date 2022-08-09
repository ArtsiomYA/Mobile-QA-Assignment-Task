package app.com.mobileassignment.views.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.views.MainActivity;
import app.com.mobileassignment.views.pageObjects.ActivityMainScreen;
import app.com.mobileassignment.views.pageObjects.ActivityMapScreen;
import app.com.mobileassignment.views.utils.TestData;
import io.qameta.allure.Description;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AndroidTestsAppMobileAssignmentTest {

    private ActivityMainScreen activityMainScreen;
    private ActivityMapScreen activityMapScreen;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void init() {
        activityMainScreen = new ActivityMainScreen();
        activityMapScreen = new ActivityMapScreen();
    }

    @Test
    @Description("Checking the display of the search field on the main screen")
    public void testCheckDisplayedSearchFieldIsPresent() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
    }

    @Test
    @Description("Checking the display of the list of cities on the main screen")
    public void testCheckCitiesListElementIsPresent() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getCitiesListElement());
    }

    @Test
    @Description("Checking data entry in the search field on the main page")
    public void testCheckInputCityInTheSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getMinskCity());
        activityMainScreen.checkIsVisibleElementByText(TestData.getMinskCity());
    }

    @Test
    @Description("Displaying search results on valid data on the main page")
    public void testCheckDisplayInputDataInTheSearchFieldOnResult() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getHofkeCity());
        activityMainScreen.waiter(3000);
        activityMainScreen.checkContainsStringInTheChild(activityMainScreen.getCitiesListElement(),
                activityMainScreen.getCityName(), TestData.getHofkeCity(), 0);
    }

    @Test
    @Description("Displaying search results on invalid data on the main page")
    public void testCheckDisplayInputInvalidDataInTheSearchFieldOnResult() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getInvalidCity());
        activityMainScreen.waiter(2000);
        activityMainScreen.checkNotExistSearchCitiesInList(activityMainScreen.getCitiesListElement());
    }

    @Test
    @Description("Checking the city selection from the list")
    public void testCheckClickOnFoundCityInListOfCities() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getMinskCity());
        activityMainScreen.waiter(3000);
        activityMainScreen.clickOnCityInListOfCityByPosition(activityMainScreen
                .getCitiesListElement(), 0);
        activityMainScreen.checkIsNotVisibleElementById(activityMainScreen.getSearchElement());
        activityMapScreen.checkIsVisibleElementById(activityMapScreen.getInsertPoint());
    }

    @Test
    @Description("Checking invalid data entry in the search field on the main page")
    public void checkInputInvalidDataInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getInvalidCity());
        activityMainScreen.checkIsVisibleElementByText(TestData.getInvalidCity());
    }

    @After
    public void destroy() {
        mActivityScenarioRule.getScenario().close();
    }

}
