package hr.mc2.worriesover.app;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import data.DrawerRowItem;
import data.Marker;
import fragments.AboutUsFragment;
import fragments.DetailsFragment;
import fragments.EmergancyCallFragment;
import fragments.GridMenuFragment;
import fragments.ImportantInformations;
import fragments.MyMapFragment;
import ListAdapters.PlacesAdapter;
import fragments.PlacesFragment;
import fragments.MySubMenuGrid;
import gps.GPSTracker;


public class MainActivity extends ActionBarActivity {
    private String[] drawerRowTitle;
    private int[] drawerRowImages = {R.drawable.ico1, R.drawable.ico2, R.drawable.ico3, R.drawable.ico5, R.drawable.ico4, R.drawable.ico6, R.drawable.ico7};
    private ArrayList<DrawerRowItem> drawerRowItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private MyMapFragment myMapFragment;
    private PlacesFragment placesFragment;
    private GridMenuFragment gridMenuFragment;
    private MySubMenuGrid mySubMenuGrid;
    private DetailsFragment detailsFragment;
    private EmergancyCallFragment emergancyCallFragment;
    private AboutUsFragment aboutUsFragment;
    private ImportantInformations importantInformations;
    private GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridMenuFragment = new GridMenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, gridMenuFragment).commit();
        mTitle = "ErasmusLocator";
        drawerRowTitle = getResources().getStringArray(R.array.titleSmall);
        mDrawerLayout = (DrawerLayout) findViewById(hr.mc2.worriesover.app.R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(hr.mc2.worriesover.app.R.id.left_drawer);
        // Set the adapter for the list view
        drawerRowItems = new ArrayList<DrawerRowItem>();
        for (int i = 0; i < 7; i++) {
            drawerRowItems.add(new DrawerRowItem(drawerRowTitle[i], drawerRowImages[i]));
        }
        mDrawerList.setAdapter(new PlacesAdapter(this, 0, drawerRowItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                hr.mc2.worriesover.app.R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                hr.mc2.worriesover.app.R.string.drawer_open,  /* "open drawer" description */
                hr.mc2.worriesover.app.R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }


    public LatLng getGpsLocation() {

        gps = new GPSTracker(this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        if (latitude != -1 && longitude != -1) {
            return new LatLng(latitude, longitude);
        }
        return null;
    }

    public void checkGps() {
        gps = new GPSTracker(this);
        gps.showSettingsAlert();
    }

    public double calculateDistance(double aLat, double aLng, double bLat, double bLng) {
        Location locationA = new Location("point A");
        locationA.setLatitude(aLat);
        locationA.setLongitude(aLng);

        Location locationB = new Location("point B");

        locationB.setLatitude(bLat);
        locationB.setLongitude(bLng);
        float distance = locationA.distanceTo(locationB) / 1000;
        return distance;
    }

    public void getAllLocations() {
        setTitle("All");
        getGpsLocation();
        placesFragment = new PlacesFragment(0);
        placesFragment.buttonColor = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getFoodLocations() {
        setTitle("FOOD");
        placesFragment = new PlacesFragment(1);
        placesFragment.buttonColor = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getFunLocations() {
        setTitle("FUN");
        mySubMenuGrid = new MySubMenuGrid();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, mySubMenuGrid).addToBackStack(null).commit();
    }

    public void getSmjestajLocations() {
        setTitle("DORM");
        placesFragment = new PlacesFragment(2);
        placesFragment.buttonColor = 2;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getVisokaUcilistaLocations() {
        setTitle("COLLEGE");
        placesFragment = new PlacesFragment(3);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getUcenjeLocations() {
        setTitle("STUDY");
        placesFragment = new PlacesFragment(4);
        placesFragment.buttonColor = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getAmbasadaLocations() {
        setTitle("EMBASSY");
        placesFragment = new PlacesFragment(5);
        placesFragment.buttonColor = 3;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getKaficiLocations() {
        setTitle("BAR");
        placesFragment = new PlacesFragment(6);
        placesFragment.buttonColor = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getMuzejiLocations() {
        setTitle("MUSEUM");
        placesFragment = new PlacesFragment(7);
        placesFragment.buttonColor = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getKluboviLocations() {
        setTitle("NIGHT CLUB");
        placesFragment = new PlacesFragment(8);
        placesFragment.buttonColor = 3;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }

    public void getKinaLocations() {
        setTitle("CINEMA");
        placesFragment = new PlacesFragment(9);
        placesFragment.buttonColor = 2;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, placesFragment).addToBackStack(null).commit();
    }


    public void getMap(Marker marker) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Marker", marker);
        myMapFragment = new MyMapFragment();
        myMapFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, myMapFragment).addToBackStack(null).commit();
    }

    public void getDetails(Marker marker, String distance) {
        detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Marker", marker);
        bundle.putString("Distance", distance);
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, detailsFragment).addToBackStack(null).commit();
    }

    public void back() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.emergency_calls:
                emergancyCallFragment = new EmergancyCallFragment();
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, emergancyCallFragment).addToBackStack(null).commit();
                break;
            case R.id.emergency_calls2:
                emergancyCallFragment = new EmergancyCallFragment();
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, emergancyCallFragment).addToBackStack(null).commit();
                break;
            case R.id.about_us:
                aboutUsFragment = new AboutUsFragment();
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, aboutUsFragment).addToBackStack(null).commit();
                break;
            case R.id.about_us2:
                aboutUsFragment = new AboutUsFragment();
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, aboutUsFragment).addToBackStack(null).commit();
                break;
            case R.id.important:
                importantInformations = new ImportantInformations();
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, importantInformations).addToBackStack(null).commit();
                break;
            case R.id.important2:
                importantInformations = new ImportantInformations();
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, importantInformations).addToBackStack(null).commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {

        mDrawerList.setItemChecked(position, true);
        switch (position) {
            case 0:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getAllLocations();
                break;
            case 1:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFoodLocations();
                break;
            case 2:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFunLocations();
                break;
            case 3:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSmjestajLocations();
                break;
            case 4:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getVisokaUcilistaLocations();
                break;
            case 5:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getUcenjeLocations();
                break;
            case 6:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getAmbasadaLocations();
                break;
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}