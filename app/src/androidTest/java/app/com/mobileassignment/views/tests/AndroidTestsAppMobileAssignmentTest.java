package app.com.mobileassignment.views.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.views.utils.ConfigProvider;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AndroidTestsAppMobileAssignmentTest extends BaseTest {

    @Test
    public void testCheckDisplayedSearchFieldOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
    }

    @Test
    public void testCheckDisplayCitiesListElementOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getCitiesList());
    }

    @Test
    public void testCheckInputCityInTheSearchFieldOnTheMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(
                activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.minsk"));
        activityMainScreen.checkIsVisibleElementByText(
                ConfigProvider.readConfig().getString("testData.city.minsk"));
        assertEquals(ConfigProvider.readConfig().getString("testData.city.minsk"),
                activityMainScreen.getText(activityMainScreen.getViewById(
                        activityMainScreen.getSearch())));
    }

    @Test
    public void testCheckDisplaySearchResultsWithValidDataOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(
                activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.hofke"));
        activityMainScreen.checkContainsStringInTheChild(
                activityMainScreen.getCitiesList(),
                activityMainScreen.getCityName(),
                ConfigProvider.readConfig().getString("testData.city.hofke"), 0);
    }

    @Test
    public void testCheckDisplaySearchResultsWithInvalidDataOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.invalidCity"));
        activityMainScreen.checkNotExistSearchCitiesInList(activityMainScreen.getCitiesList());
    }

    @Test
    public void testCheckSelectCityFromSearchResult() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.kiewa"));
        activityMainScreen.clickOnCityInListOfCityByPosition(
                activityMainScreen.getCitiesList(), 0);
        activityMainScreen.checkIsNotVisibleElementById(activityMainScreen.getSearch());
        activityMapScreen.checkIsVisibleElementById(activityMapScreen.getInsertPoint());
    }

    @Test
    public void testCheckInputInvalidDataInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.invalidCity"));
        activityMainScreen.checkIsVisibleElementByText(
                ConfigProvider.readConfig().getString("testData.city.invalidCity"));
    }

    @Test()
    public void testCheckInputDataOnCyrillicSymbolInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        assertTrue(activityMainScreen.checkExceptionOccurrence(
                activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.vitebskCyrillicCity")));
    }

    @Test
    public void checkProgressBarOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getProgressBar());
    }

    @Test
    public void checkTitleOnMainScreen() {
        activityMainScreen.checkIsVisibleElementByText(
                ConfigProvider.readConfig().getString("testData.city.titleMainScreen"));
    }

    @Test
    public void checkCountrySearch() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(),
                ConfigProvider.readConfig().getString("testData.city.unitedArabEmirates"));
        activityMainScreen.checkNotExistSearchCitiesInList(activityMainScreen.getCitiesList());
    }

    @Test
    public void checkPackageName() {
        assertEquals(ConfigProvider.readConfig().getString("testData.city.packageName"),
                appContext.getPackageName());
    }

    @Test
    public void checkIfInternetOnDevice() {
        assertTrue(activityMainScreen.netConnect(appContext));
    }

}
