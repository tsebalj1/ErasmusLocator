package MyDatabeseOrm;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.Marker;

/**
 * Created by zubak on 15.4.2014..
 */
public class DataSource {
    private DatabaseHelper helper;
    private MarkerLoader loader;
    private String filter;


    public DataSource(Context context) {
        helper = new DatabaseHelper( context );
        loader = new MarkerLoader( context, this );
    }

    public void insertMarker( Marker marker ) {
        try {
            helper.getMarkerDao().create( marker );
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
    }

    public Cursor getMarkers(int id) {
        String selection = null;
        String[] selectionArgs = null;
        switch (id){
            case 1:
                selection = "tip LIKE 'Hrana'";
                break;
            case 2:
                selection = "tip LIKE 'Smještaj'";
                break;
            case 3:
                selection = "tip LIKE 'Visoko učilište'";
                break;
            case 4:
                selection = "tip LIKE 'Učenje'";
                break;
            case 5:
                selection = "tip LIKE 'Ambasada'";
                break;
            case 6:
                selection = "tip LIKE 'Kafić'";
                break;
            case 7:
                selection = "tip LIKE 'Muzej'";
                break;
            case 8:
                selection = "tip LIKE 'Noćni klub'";
                break;
            case 9:
                selection = "tip LIKE 'Kino'";
                break;
        }
        if ( !TextUtils.isEmpty(filter) ) {
            if(selection != null){
                selection += " AND ";
                selection += "nazivObjekta LIKE ?";
            }
            else{
                selection = "nazivObjekta LIKE ?";
            }

            selectionArgs = new String[] {"%"+ filter+ "%"};
        }

        return helper.getReadableDatabase().query(
                "Marker", new String[]{ "_id", "tip","nazivObjekta","latitude","longitude","adresa","img","telefon","eMail","url","radnoVrijeme"} ,
                selection, selectionArgs,
                null, null, null );

    }


    public MarkerLoader getLoader() {
        return loader;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter( String filter ) {
        this.filter = filter;
    }
}
