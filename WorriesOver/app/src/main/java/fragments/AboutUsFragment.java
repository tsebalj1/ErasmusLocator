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
 * Created by zubak on 28.4.2014..
 */
public class AboutUsFragment extends Fragment {
    private View view;
    private MainActivity mainActivity;
    private TextView naslov;
    private TextView imeTima;
    private TextView textOpisni;


    private Typeface type1;
    private Typeface type2;
    private Typeface type3;

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
        view = inflater.inflate(R.layout.about_us,container,false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle("About us");

        type1 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/RobotoSlab-Light.ttf");
        type2 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/RobotoSlab-Bold.ttf");
        type3 = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/Roboto-Light.ttf");

        naslov = (TextView) view.findViewById(R.id.naslov);
        textOpisni = (TextView) view.findViewById(R.id.text_opisni);
        imeTima = (TextView) view.findViewById(R.id.ime_tima);

        naslov.setTypeface(type1);
        imeTima.setTypeface(type2);
        textOpisni.setTypeface(type3);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);


    }
}
