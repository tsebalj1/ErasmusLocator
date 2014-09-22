package ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import data.MenuRowItem;
import hr.mc2.worriesover.app.R;

/**
 * Created by zubak on 25.4.2014..
 */
public class MyMenuGridAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<MenuRowItem> menuList;


    public MyMenuGridAdapter(Context context, ArrayList<MenuRowItem> menuList)
    {
        super(context, 0);
        this.context=context;
        this.menuList = menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {


        View listItem = convertView;

        if (listItem == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItem = inflater.inflate(R.layout.grid_row, parent, false);
        }
            TextView text = (TextView) listItem.findViewById(R.id.text_meni);
            ImageView img = (ImageView) listItem.findViewById(R.id.image_main_grid);
            text.setText(menuList.get(position).getNaslov());
            text.setBackgroundResource(menuList.get(position).getBoja());
            img.setImageResource(menuList.get(position).getSlika());

        return listItem;
    }
}