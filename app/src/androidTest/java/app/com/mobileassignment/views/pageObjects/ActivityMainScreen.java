package app.com.mobileassignment.views.pageObjects;


import app.com.mobileassignment.R;

public class ActivityMainScreen extends BaseScreen {

    public int getSearchElement() { return R.id.search; }

    public int getResultsSearch() {
        return R.id.results;
    }

    public int getInputPoint() {
        return R.id.insert_point;
    }

    public int getCitiesListElement() {
        return R.id.citiesList;
    }

    public int getCityName() {
        return R.id.cityName;
    }

}
