package fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import MyDatabeseOrm.DataSource;
import MyDatabeseOrm.DatabaseHelper;
import data.Marker;
import hr.mc2.worriesover.app.MainActivity;
import hr.mc2.worriesover.app.R;


/**
 * Created by zubak on 18.4.2014..
 */
public class PlacesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener {
    private MyCursorAdapter myCursorAdapter;
    private MainActivity mainActivity;
    private DataSource datasource;
    private View view;
    private static String menuText = "";
    public static int buttonColor = 0;
    private MenuItem searchItem;
    private int position;

    public PlacesFragment() {
    }

    @SuppressLint("ValidFragment")
    public PlacesFragment(int position) {
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        datasource = new DataSource(getActivity());
        setHasOptionsMenu(true);

        myCursorAdapter = new MyCursorAdapter(getActivity(), null, mainActivity);
        setListAdapter(myCursorAdapter);
       // getActivity().getSupportLoaderManager().initLoader(position, null, this);
        getActivity().getSupportLoaderManager().restartLoader(position, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().deleteDatabase(DatabaseHelper.DATABASE_NAME);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQuery(menuText, false);

        searchView.setOnQueryTextListener(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.locations_list, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parentViewGroup = (ViewGroup) view.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return datasource.getLoader();
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        myCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myCursorAdapter.swapCursor(null);
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        menuText = newText;
        datasource.setFilter(newText);
        datasource.getLoader().forceLoad();

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }
}

class MyCursorAdapter extends CursorAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private MainActivity mainActivity;
    private ImageView iconImageView;
    private Button details;
    private TextView distance;
    private Typeface type1;
    private Typeface type2;
    private String distanceText;
    private LatLng myLocation;

    public MyCursorAdapter(Context context, Cursor c, MainActivity mainActivity) {
        super(context, c, 0);
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mainActivity = mainActivity;
        myLocation = mainActivity.getGpsLocation();
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        String tip = cursor.getString(cursor.getColumnIndex("tip"));
        String naslov = cursor.getString(cursor.getColumnIndex("nazivObjekta"));
        double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
        double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
        String adresa = cursor.getString(cursor.getColumnIndex("adresa"));
        int img = cursor.getInt(cursor.getColumnIndex("img"));
        String telefon = cursor.getString(cursor.getColumnIndex("telefon"));
        String eMail = cursor.getString(cursor.getColumnIndex("eMail"));
        String url = cursor.getString(cursor.getColumnIndex("url"));
        String radnoVrijeme = cursor.getString(cursor.getColumnIndex("radnoVrijeme"));
        TextView naslovTextView = (TextView) view.findViewById(R.id._naslov);
        TextView adresaTextView = (TextView) view.findViewById(R.id._adresa);
        ImageView imgImageView = (ImageView) view.findViewById(R.id._slika);
        iconImageView = (ImageView) view.findViewById(R.id.icon_type);
        int icon_res = setIcon(tip);
        type1 = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoSlab-Light.ttf");
        type2 = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        naslovTextView.setTypeface(type1);
        adresaTextView.setTypeface(type2);
        naslovTextView.setText(naslov);
        adresaTextView.setText(adresa);
        imgImageView.setImageResource(img);
        View distanceLayout = view.findViewById(R.id.layout_distance);
        distance = (TextView) view.findViewById(R.id.distance);
        if (myLocation != null) {
            distanceLayout.setVisibility(View.VISIBLE);
            double myLat = myLocation.latitude;
            double myLng = myLocation.longitude;
            float d = (float) mainActivity.calculateDistance(myLat, myLng, latitude, longitude);
            distanceText = String.format("%.2f km", d);
            distance.setText(distanceText);
        }
        final Marker marker = new Marker(tip, naslov, latitude, longitude, adresa, img, telefon, eMail, url, radnoVrijeme);
        marker.setIcon(icon_res);
        details = (Button) view.findViewById(R.id._details);
        setButtonColor(tip);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.getDetails(marker, distanceText);
            }
        });
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.list_item, parent, false);

        return v;
    }

    public void setButtonColor(String type) {
        if (PlacesFragment.buttonColor == 0) {
            details.setBackgroundResource(R.drawable.custom_button_orange);
        }
        if (PlacesFragment.buttonColor == 1) {
            details.setBackgroundResource(R.drawable.custom_button_purple);
        }
        if (PlacesFragment.buttonColor == 2) {
            details.setBackgroundResource(R.drawable.custom_button_blue);
        }
        if (PlacesFragment.buttonColor == 3) {
            details.setBackgroundResource(R.drawable.custom_button_green);
        }

    }

    public int setIcon(String tip) {
        if (tip.equalsIgnoreCase("Visoko učilište")) {
            iconImageView.setImageResource(R.drawable.ico4);
            return R.drawable.ico4;
        } else if (tip.equalsIgnoreCase("Hrana")) {
            iconImageView.setImageResource(R.drawable.ico2);
            return R.drawable.ico2;
        } else if (tip.equalsIgnoreCase("Ambasada")) {
            iconImageView.setImageResource(R.drawable.ico7);
            return R.drawable.ico7;
        } else if (tip.equalsIgnoreCase("Smještaj")) {
            iconImageView.setImageResource(R.drawable.ico3);
            return R.drawable.ico3;
        } else if (tip.equalsIgnoreCase("Učenje")) {
            iconImageView.setImageResource(R.drawable.ico5);
            return R.drawable.ico5;
        } else if (tip.equalsIgnoreCase("Muzej")) {
            iconImageView.setImageResource(R.drawable.ico3);
            return R.drawable.ico3;
        } else if (tip.equalsIgnoreCase("Kafić")) {
            iconImageView.setImageResource(R.drawable.ico3);
            return R.drawable.ico3;
        } else if (tip.equalsIgnoreCase("Noćni klub")) {
            iconImageView.setImageResource(R.drawable.ico3);
            return R.drawable.ico3;
        } else if (tip.equalsIgnoreCase("Kino")) {
            iconImageView.setImageResource(R.drawable.ico3);
            return R.drawable.ico3;
        } else {
            return R.drawable.ico1;
        }
    }
}