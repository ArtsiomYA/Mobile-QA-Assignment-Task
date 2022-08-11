package app.com.mobileassignment.views.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.views.utils.TestData;

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
        activityMainScreen.performText(activityMainScreen.getSearch(), TestData.getMinskCity(), 3000);
        activityMainScreen.checkIsVisibleElementByText(TestData.getMinskCity());
        assertEquals(TestData.getMinskCity(), activityMainScreen.getText(
                activityMainScreen.getViewById(activityMainScreen.getSearch())));

    }

    @Test
    public void testCheckDisplaySearchResultsWithValidDataOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(), TestData.getHofkeCity(), 3000);
        activityMainScreen.checkContainsStringInTheChild(activityMainScreen.getCitiesList(),
                activityMainScreen.getCityName(), TestData.getHofkeCity(), 0, 3000);
    }

    @Test
    public void testCheckDisplaySearchResultsWithInvalidDataOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(), TestData.getInvalidCity(), 3000);
        activityMainScreen.checkNotExistSearchCitiesInList(activityMainScreen.getCitiesList());
    }

    @Test
    public void testCheckSelectCityFromSearchResult() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(), TestData.getKiewaPage(), 3000);
        activityMainScreen.clickOnCityInListOfCityByPosition(activityMainScreen
                .getCitiesList(), 0, 3000);
        activityMainScreen.checkIsNotVisibleElementById(activityMainScreen.getSearch());
        activityMapScreen.checkIsVisibleElementById(activityMapScreen.getInsertPoint());
    }

    @Test
    public void testCheckInputInvalidDataInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        activityMainScreen.performText(activityMainScreen.getSearch(), TestData.getInvalidCity(), 3000);
        activityMainScreen.checkIsVisibleElementByText(TestData.getInvalidCity());
    }

    @Test()
    public void testCheckInputDataOnCyrillicSymbolInSearchField() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getSearch());
        assertThrows(RuntimeException.class, () -> {
            activityMainScreen.performText(activityMainScreen.getSearch(), TestData.getVitebskCyrillicCity(), 3000);
        });
    }

    @Test
    public void checkProgressBarOnMainScreen() {
        activityMainScreen.checkIsVisibleElementById(activityMainScreen.getProgressBar());
    }

    @Test
    public void checkTitleOnMainScreen() {
        activityMainScreen.checkIsVisibleElementByText(TestData.getTitleMainScreen());
    }

}
