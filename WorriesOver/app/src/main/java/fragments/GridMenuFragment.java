package fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import ListAdapters.MyMenuGridAdapter;
import data.MenuRowItem;
import hr.mc2.worriesover.app.MainActivity;
import hr.mc2.worriesover.app.R;

/**
 * Created by zubak on 25.4.2014..
 */
public class GridMenuFragment extends Fragment {
    private int[] subMeniSlike = {R.drawable.sve, R.drawable.hrana, R.drawable.zabava, R.drawable.smjestaj, R.drawable.ucilista, R.drawable.ucenje, R.drawable.ambasade};
    private int[] boje = {R.color.Light_Orange, R.color.Light_Purple, R.color.Light_Green, R.color.Light_Blue, R.color.Light_Orange, R.color.Light_Purple, R.color.Light_Blue};
    private String[] naslovi;
    private ArrayList<MenuRowItem> rowItemList;
    private MyMenuGridAdapter myMenuGridAdapter;
    private MainActivity mainActivity;
    private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Resources res = getResources();
        mainActivity = (MainActivity) getActivity();
        mainActivity.setTitle("ErasmusLocator");
        naslovi = res.getStringArray(R.array.title);
        rowItemList = new ArrayList<MenuRowItem>();
        for (int i = 0; i < 7; i++) {
            MenuRowItem menuRowItem = new MenuRowItem(naslovi[i], subMeniSlike[i], boje[i]);
            rowItemList.add(menuRowItem);
        }
        myMenuGridAdapter = new MyMenuGridAdapter(getActivity(), rowItemList);
        gridView.setAdapter(myMenuGridAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        gridView = (GridView) inflater.inflate(R.layout.grid_layout, container, false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mainActivity.getAllLocations();
                        break;
                    case 1:
                        mainActivity.getFoodLocations();
                        break;
                    case 2:
                        mainActivity.getFunLocations();
                        break;
                    case 3:
                        mainActivity.getSmjestajLocations();
                        break;
                    case 4:
                        mainActivity.getVisokaUcilistaLocations();
                        break;
                    case 5:
                        mainActivity.getUcenjeLocations();
                        break;
                    case 6:
                        mainActivity.getAmbasadaLocations();
                        break;
                }
            }
        });
        return gridView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (gridView != null) {
            ViewGroup parentViewGroup = (ViewGroup) gridView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);


    }


}
