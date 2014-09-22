package fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import data.Marker;
import hr.mc2.worriesover.app.MainActivity;
import hr.mc2.worriesover.app.R;


public class MyMapFragment extends Fragment {
    private ImageView decrease;
    private ImageView navigateMe;
    private View view;
    private MainActivity mainActivity;
    private Marker marker;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        mainActivity = (MainActivity) getActivity();
        marker = bundle.getParcelable("Marker");
        mainActivity.setTitle("Map");
        double latitude = marker.getLatitude();
        double longitude = marker.getLongitude();
        GoogleMap map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.clear();
        /*CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(latitude, longitude))   //set center
                .radius(200)   //set radius in meters
                .fillColor(Color.parseColor("#500084d3")).strokeWidth(0); */


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
       // map.addCircle(circleOptions);
        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(marker.getIcon()));
        map.addMarker(markerOptions
                .title(marker.getNazivObjekta())
                .position(new LatLng(latitude, longitude)));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.layout_mapa, container, false);
        }
        decrease = (ImageView) view.findViewById(R.id.button_decrease);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.back();
            }
        });

        navigateMe = (ImageView) view.findViewById(R.id.button_navigation);
        navigateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainActivity.getGpsLocation() != null ) {
                    LatLng l = mainActivity.getGpsLocation();
                        double latitude = l.latitude;
                        double longitude = l.longitude;
                        String path = "http://maps.google.com/maps?saddr=" + latitude + ", " + longitude + "&daddr=" + marker.getLatitude() + ", " + marker.getLongitude();
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse(path));
                        startActivity(intent);
                }else{
                    mainActivity.checkGps();
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SupportMapFragment f = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            getFragmentManager().beginTransaction().remove(f).commit();
        }
        if (view != null) {
            ViewGroup parentViewGroup = (ViewGroup) view.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }
}
