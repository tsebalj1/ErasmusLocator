package fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import data.Marker;
import hr.mc2.worriesover.app.MainActivity;
import hr.mc2.worriesover.app.R;

/**
 * Created by zubak on 21.4.2014..
 */
public class DetailsFragment extends Fragment {

    private View view;
    private Button guideMe;
    private Button call;
    private Button sendMail;
    private Button web;
    private MainActivity mainActivity;
    private Marker marker;
    private TextView naslov;
    private TextView adresa;
    private TextView udaljenost;
    private TextView telefonskiBroj;
    private TextView emailAdresa;
    private TextView webLink;
    private TextView radnoVrijeme;
    private ImageView slika;
    private String distance;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        Bundle bundle = getArguments();
        marker = bundle.getParcelable("Marker");
        distance = bundle.getString("Distance");
        mainActivity.setTitle("Details");
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details, container, false);
        slika = (ImageView) view.findViewById(R.id.top_image);
        slika.setImageResource(marker.getImg());
        guideMe = (Button) view.findViewById(R.id.expand_map);
        guideMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.getMap(marker);
            }
        });
        call = (Button) view.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + marker.getTelefon()));
                getActivity().startActivity(callIntent);
            }
        });
        sendMail = (Button) view.findViewById(R.id.send_mail);
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        web = (Button) view.findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = marker.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(i);
            }
        });
        setFonts();
        if(distance != null){
            udaljenost.setText(distance);
        }
        naslov.setText(marker.getNazivObjekta());
        adresa.setText(marker.getAdresa());
        telefonskiBroj.setText(marker.getTelefon());
        emailAdresa.setText(marker.geteMail());
        webLink.setText(marker.getUrl());
        radnoVrijeme.setText(marker.getRadnoVrijeme());
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

    public void sendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        String recipien = marker.geteMail();
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{recipien});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

    }


    public void setFonts() {
        naslov = (TextView) view.findViewById(R.id.naslov);
        adresa = (TextView) view.findViewById(R.id.adresa);
        udaljenost = (TextView) view.findViewById(R.id.udaljenost);
        telefonskiBroj = (TextView) view.findViewById(R.id.broj_telefona);
        emailAdresa = (TextView) view.findViewById(R.id.mailAdresa);
        webLink = (TextView) view.findViewById(R.id.webLink);
        radnoVrijeme = (TextView) view.findViewById(R.id.radno_vrijeme);
        Typeface type1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RobotoSlab-Bold.ttf");
        Typeface type3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-LightItalic.ttf");
        naslov.setTypeface(type1);
        adresa.setTypeface(type2);
        udaljenost.setTypeface(type2);
        telefonskiBroj.setTypeface(type2);
        emailAdresa.setTypeface(type2);
        webLink.setTypeface(type2);
        radnoVrijeme.setTypeface(type2);
        call.setTypeface(type3);
        guideMe.setTypeface(type3);
        web.setTypeface(type3);
        sendMail.setTypeface(type3);
    }
}
