package fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hr.mc2.worriesover.app.MainActivity;
import hr.mc2.worriesover.app.R;

/**
 * Created by zubak on 29.4.2014..
 */
public class ImportantInformations extends Fragment {
    private View view;
    private MainActivity mainActivity;
    private TextView naslov;
    private TextView beforeArrival;
    private TextView beforeArrivalText;
    private TextView afterArrival;
    private TextView afterArrivalText;

    Typeface type1;
    Typeface type2;
    Typeface type3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
        view = inflater.inflate(R.layout.important_informations, container, false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle("Important informations");

        type1 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/RobotoSlab-Light.ttf");
        type2 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/RobotoSlab-Bold.ttf");
        type3 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/Roboto-Light.ttf");

        naslov = (TextView) view.findViewById(R.id.naslov);
        beforeArrival = (TextView) view.findViewById(R.id.before_arrival);
        beforeArrivalText = (TextView) view.findViewById(R.id.before_arrival_text);
        afterArrival = (TextView) view.findViewById(R.id.after_arrival);
        afterArrivalText = (TextView) view.findViewById(R.id.after_arrival_text);

        naslov.setTypeface(type1);
        beforeArrival.setTypeface(type2);
        beforeArrivalText.setTypeface(type3);
        afterArrival.setTypeface(type2);
        afterArrivalText.setTypeface(type3);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }
}
