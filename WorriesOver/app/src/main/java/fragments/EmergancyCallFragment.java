package fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hr.mc2.worriesover.app.MainActivity;
import hr.mc2.worriesover.app.R;

/**
 * Created by zubak on 27.4.2014..
 */
public class EmergancyCallFragment extends Fragment {
    private View view;
    private MainActivity mainActivity;
    Context context;
    private TextView naslov;
    private TextView urgentHelpText1;
    private TextView urgentHelpText2;
    private TextView policeText1;
    private TextView policeText2;
    private TextView ambulanceText1;
    private TextView ambulanceText2;
    private TextView firefightersText1;
    private TextView firefightersText2;
    private TextView informationsText1;
    private TextView informationsText2;
    private Button urgentHelp;
    private Button police;
    private Button ambulance;
    private Button firefighters;
    private Button informations;

    Typeface type1;
    Typeface type3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        urgentHelp = (Button) view.findViewById(R.id.urgent_help_button);
        urgentHelp.setTypeface(type3);
        urgentHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String number = getActivity().getResources().getString(R.string.urgent_help);
                callIntent.setData(Uri.parse("tel:" + number));
                getActivity().startActivity(callIntent);
            }
        });
        police = (Button) view.findViewById(R.id.police_button);
        police.setTypeface(type3);
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String number = getActivity().getResources().getString(R.string.police);
                callIntent.setData(Uri.parse("tel:" + number));
                getActivity().startActivity(callIntent);
            }
        });
        ambulance = (Button) view.findViewById(R.id.ambulance_button);
        ambulance.setTypeface(type3);
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String number = getActivity().getResources().getString(R.string.ambulance);
                callIntent.setData(Uri.parse("tel:" + number));
                getActivity().startActivity(callIntent);
            }
        });
        firefighters = (Button) view.findViewById(R.id.firefighters_button);
        firefighters.setTypeface(type3);
        firefighters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String number = getActivity().getResources().getString(R.string.firefighters);
                callIntent.setData(Uri.parse("tel:" + number));
                getActivity().startActivity(callIntent);
            }
        });
        informations= (Button) view.findViewById(R.id.information_button);
        informations.setTypeface(type3);
        informations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String number = getActivity().getResources().getString(R.string.informations);
                callIntent.setData(Uri.parse("tel:" + number));
                getActivity().startActivity(callIntent);
            }
        });
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.emergency_calls,container,false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle("Emergancy Call");

        type1 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/RobotoSlab-Light.ttf");
        type3 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/Roboto-Light.ttf");

        naslov = (TextView) view.findViewById(R.id.naslov);
        urgentHelpText1 = (TextView) view.findViewById(R.id.urgent_help_text1);
        urgentHelpText2 = (TextView) view.findViewById(R.id.urgent_help_text2);
        policeText1 = (TextView) view.findViewById(R.id.police_text1);
        policeText2 = (TextView) view.findViewById(R.id.police_text2);
        ambulanceText1 = (TextView) view.findViewById(R.id.ambulance_text1);
        ambulanceText2 = (TextView) view.findViewById(R.id.ambulance_text2);
        firefightersText1 = (TextView) view.findViewById(R.id.firefighters_text1);
        firefightersText2 = (TextView) view.findViewById(R.id.firefighters_text2);
        informationsText1 = (TextView) view.findViewById(R.id.information_text1);
        informationsText2 = (TextView) view.findViewById(R.id.information_text2);

        naslov.setTypeface(type1);
        urgentHelpText1.setTypeface(type3);
        urgentHelpText2.setTypeface(type3);
        policeText1.setTypeface(type3);
        policeText2.setTypeface(type3);
        ambulanceText1.setTypeface(type3);
        ambulanceText2.setTypeface(type3);
        firefightersText1.setTypeface(type3);
        firefightersText2.setTypeface(type3);
        informationsText1.setTypeface(type3);
        informationsText2.setTypeface(type3);

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);


    }
}
