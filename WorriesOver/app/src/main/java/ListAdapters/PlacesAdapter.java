package ListAdapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import data.DrawerRowItem;
import data.Marker;
import hr.mc2.worriesover.app.R;

/**
 * Created by zubak on 7.4.2014..
 */
public class PlacesAdapter extends ArrayAdapter<DrawerRowItem> {
    private List<DrawerRowItem> list;
    private Typeface type1;

    public PlacesAdapter(Context context, int resource, List<DrawerRowItem> list) {
        super(context, resource, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);

        TextView nameOfPlace = (TextView) view.findViewById(R.id.name);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        type1 = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Regular.ttf");
        nameOfPlace.setTypeface(type1);
        nameOfPlace.setText(list.get(position).getNaslov());
        img.setImageResource(list.get(position).getSlika());

        return view;
    }
}
