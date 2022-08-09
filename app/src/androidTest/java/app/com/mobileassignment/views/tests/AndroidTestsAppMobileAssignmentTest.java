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
    public void testCheckDisplayedSearchFieldOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
    }

    @Test
    public void testCheckDisplayCitiesListElementOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getCitiesListElement());
    }

    @Test
    public void testCheckInputCityInTheSearchFieldOnTheMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getMinskCity());
        activityMainScreen.checkIsVisibleElementByText(TestData.getMinskCity());
    }

    @Test
    public void testCheckDisplaySearchResultsWithValidDataOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getHofkeCity());
        activityMainScreen.waiter(3000);
        activityMainScreen.checkContainsStringInTheChild(activityMainScreen.getCitiesListElement(),
                activityMainScreen.getCityName(), TestData.getHofkeCity(), 0);
    }

    @Test
    public void testCheckDisplaySearchResultsWithInvalidDataOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getInvalidCity());
        activityMainScreen.waiter(2000);
        activityMainScreen.checkNotExistSearchCitiesInList(activityMainScreen.getCitiesListElement());
    }

    @Test
    public void testCheckSelectCityFromSearchResult() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getMinskCity());
        activityMainScreen.waiter(3000);
        activityMainScreen.clickOnCityInListOfCityByPosition(activityMainScreen
                .getCitiesListElement(), 0);
        activityMainScreen.checkIsNotVisibleElementById(activityMainScreen.getSearchElement());
        activityMapScreen.checkIsVisibleElementById(activityMapScreen.getInsertPoint());
    }

    @Test
    public void testCheckInputInvalidDataInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getInvalidCity());
        activityMainScreen.checkIsVisibleElementByText(TestData.getInvalidCity());
    }

    @Test(expected = RuntimeException.class)
    public void testCheckInputDataOnCyrillicSymbolInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearchElement());
        activityMainScreen.performText(activityMainScreen.getSearchElement(), TestData.getVitebskCyrillicCity());
    }

    @After
    public void destroy() {
        mActivityScenarioRule.getScenario().close();
    }

}
