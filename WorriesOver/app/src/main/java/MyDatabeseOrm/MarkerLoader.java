package MyDatabeseOrm;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by zubak on 15.4.2014..
 */
public class MarkerLoader extends CursorLoader {
    private DataSource dataSource;

    public MarkerLoader( Context context, DataSource datasource ) {
        super( context );
        this.dataSource = datasource;

    }

    @Override
    protected Cursor onLoadInBackground() {

        return dataSource.getMarkers(getId());
    }
}
