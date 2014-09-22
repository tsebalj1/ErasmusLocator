package MyDatabeseOrm;

/**
 * Created by zubak on 15.4.2014..
 */

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import data.Marker;
import hr.mc2.worriesover.app.R;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Marker, Integer> markersDao = null;

    public static final String DATABASE_NAME = "markers.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Marker.class);
            getMarkerDao().create(new Marker("Visoko učilište", "Polytechnic of Zagreb", 45.795229, 15.969386, "Vrbik 8, 10000 Zagreb", R.drawable.tehnicko_veleuciliste_u_zagrebu, "+38516451926", "tvz@tvz.hr", "https://moj.tvz.hr/", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Visoko učilište", "Faculty of Agriculture", 45.831562, 16.029551, "Ulica Tvrtka Miloša, 10000 Zagreb", R.drawable.agronomija, "+38512393777", "dekanat@agr.hr", "http://www.agr.unizg.hr/en", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Visoko učilište", "Faculty of Graphic Arts", 45.806001, 16.029909, "Getaldićeva 2, 10000 Zagreb", R.drawable.graficki, "+385 12371080", "info@grf.hr", "http://www.grf.unizg.hr/en/", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Visoko učilište", "Faculty of Kinesiology", 45.784891, 15.944583, "Horvaćanski zavoj 15, 10000 Zagreb", R.drawable.kinezioloski, "+38513658666", "dekanat@kif.hr", "http://www.kif.unizg.hr/en", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Visoko učilište", "Faculty of Law", 45.810577, 15.969774, "Trg maršala Tita 14/I, 10000 Zagreb", R.drawable.pravni, "+38514564302", "dekanat@pravo.hr", "http://www.pravo.unizg.hr/en", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Visoko učilište", "Faculty of Dentistry", 45.812417, 15.972102, "Gundulićeva 5, 10000 Zagreb", R.drawable.stomatoloski, "+38514802111", "dekanat@sfzg.hr", "http://www.sfzg.unizg.hr/en", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Smještaj", "Student dorm Cvjetno naselje", 45.784857, 15.947357, "Cvjetna cesta 11C, 10000 Zagreb", R.drawable.sd_cvijetno_naselje, "+38516191245 ", "smjestaj@sczg.hr", "http://www.sczg.unizg.hr/smjestaj/domovi/cvjetno-naselje/", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Smještaj", "Student dorm dr.Ante Starčević", 45.794975, 15.955624, "Zagrebačka avenija 2, 10000 Zagreb", R.drawable.sn_dr_ante_starcevic, "+38513095437 ", "smjestaj@sczg.hr", "http://www.sczg.unizg.hr/smjestaj/domovi/dr-ante-starcevic/", "9:00 - 20:00"));
            getMarkerDao().create(new Marker("Ambasada", "Canadian Embassy", 45.810353, 15.968221, "Prilaz Gjure Deželića 4, 10000 Zagreb", R.drawable.ambasada_kanada, "+38514881200", "zagrb@international.gc.ca", "http://www.canadainternational.gc.ca/croatia-croatie/index.aspx?lang=eng", "10.00 - 12.00 & 13.00 - 15.00"));
            getMarkerDao().create(new Marker("Ambasada","Slovakia Embassy",45.810387, 15.967505,"Prilaz Gjure Deželića 10, 10000 Zagreb",R.drawable.ambasada_slovacka,"+385 14877070","emb.zagreb@mzv.sk","http://www.zagreb.mfa.sk/","9:00-12:00"));
            getMarkerDao().create(new Marker("Ambasada", "Italian Embassy", 45.810888, 15.966816, "Medulićeva ulica 22, 10000 Zagreb", R.drawable.ambasada_italia, "+38514846386", "amb.zagabria@esteri.it ", "http://www.ambzagabria.esteri.it/ambasciata_zagabria", "8:30-18:00"));
            getMarkerDao().create(new Marker("Učenje", "National and University Library", 45.796634, 15.977499, "Hrvatske Bratske Zajednice 4, 10000 Zagreb", R.drawable.nsk, "+38516164078", "zmajstorovic@nsk.hr", "http://www.nsk.hr/en/", "8:00-21:00"));
            getMarkerDao().create(new Marker("Učenje", "Library of the Faculty of Philosophy", 45.796560, 15.971303, "Ulica Ivana Lučića 3, 10000 Zagreb", R.drawable.knjiznica_filozofski, "+38516002396", "knjiznica@ffzg.hr", "http://koha.ffzg.unizg.hr/", "7:30-20:00"));
            getMarkerDao().create(new Marker("Kafić", "Krivi put", 45.804560, 15.965700, "Savska cesta 14, 10000 Zagreb", R.drawable.krivi_put, "+381 00000100", null, "http://www.spottedbylocals.com/zagreb/krivi-put/", "9:00am-02:00am"));
            getMarkerDao().create(new Marker("Kino", "Cinestar Branimir Centar", 45.805507, 15.984709, "Ulica kneza Branimira 29, 10000 Zagreb", R.drawable.cinestar_branimir, "+38516396720", "", "http://www.blitz-cinestar.hr/cinestar-zagreb", "00:00 - 24:00"));
            getMarkerDao().create(new Marker("Kino", "Cinestar Avenue Mall", 45.805507, 15.984709, "Avenija Dubrovnik 16 , 10000 Zagreb", R.drawable.cinestar_avenue_mall, "+38514686600", "", "http://www.blitz-cinestar.hr/cinestar-novi-zagreb", "08:00 - 22:00"));
            getMarkerDao().create(new Marker("Ambasada", "Embassy of Sweden", 45.810964, 15.969117, "Frankopanska 22, 10000 Zagreb", R.drawable.milja, "+385 14925100", "ambassaden.zagreb@gov.se", "http://www.swedenabroad.com/en-GB/Embassies/Zagreb/About-us/Opening-hours/", "10:00 - 12:00"));
            getMarkerDao().create(new Marker("Učenje", "Library of FER", 45.800718, 15.971137, "Unska 3, 10000 Zagreb", R.drawable.knjiznica_fer, "+385 1 6129 886", "ferlib@fer.hr", "http://www.fer.unizg.hr/knjiznica", "8:00 - 19:00"));
            getMarkerDao().create(new Marker("Kafić", "Caffe Filozofija", 45.796341, 15.971189, "Ivana Lučića 3, 10000 Zagreb", R.drawable.caffe_filozofija, "095 5919627", "no mail", "https://www.facebook.com/pages/Caffe-Filozofija/348772215264419", "8:00 - 20:00"));
            getMarkerDao().create(new Marker("Kafić", "22000 milja", 45.810964, 15.969117, "Frankopanska 22, 10000 Zagreb", R.drawable.milja, "+385 14817007", "no mail", "http://www.spottedbylocals.com/zagreb/krivi-put/", "9:00am-02:00am"));
            getMarkerDao().create(new Marker("Kino", "Cinestar Arena Centar", 45.770261, 15.939992, "Lanište 32 , 10000 Zagreb", R.drawable.cinestar_arena, "+385 16396720", "info@arenacentar.hr", "http://www.blitz-cinestar.hr/cinestar-arena-imax", "14:00 – 00:30"));
            getMarkerDao().create(new Marker("Hrana","Restaurant Savska",45.803766, 15.966378,"Savska cesta 25, 10000 Zagreb",R.drawable.sc_menza,"+385 14593555","prehrana@sczg.hr","http://www.sczg.unizg.hr/prehrana/restorani/savska/","9:00 - 21:00"));
            getMarkerDao().create(new Marker("Hrana","Restaurant Stjepan Radić",45.784857, 15.947357,"Jarunska 2, 10000 Zagreb",R.drawable.sava_menza,"+385 14593555","prehrana@sczg.hr","http://www.sczg.unizg.hr/prehrana/restorani/sd-s-radic/","7:00 - 21:30"));
            getMarkerDao().create(new Marker("Hrana","Restaurant Cvjetno naselje",45.793538, 15.963254,"Cvjetna cesta 11C, 10000 Zagreb",R.drawable.sd_cvijetno_naselje,"+38514593555","prehrana@sczg.hr","http://www.sczg.unizg.hr/prehrana/restorani/sd-s-radic/","7:00 - 21:00"));
            getMarkerDao().create(new Marker("Muzej", "Museum of broken relationships", 45.814872, 15.973383, "Ćirilometodska ulica 2, 10000 Zagreb", R.drawable.muzej_prekinutih_veza, "+38514851021", "info@brokenships.com", "http://brokenships.com/en", "09:00 - 22:30"));
            getMarkerDao().create(new Marker("Noćni klub","Katran",45.804049, 16.001746,"Radnička cesta 27bb, 10000 Zagreb",R.drawable.katran,"095 4188 388","industry1972@gmail.com","https://www.facebook.com/ShockShowIndustry","20:00 - 05:00"));
            getMarkerDao().create(new Marker("Noćni klub","Roko and Academy",45.783941,15.947803,"Jarunska ulica 2B , 10000 Zagreb",R.drawable.roko_akademija,"095/5472278","info@clubroko","http://www.clubroko.hr/#!/home","9:00am - 05:00am"));
            getMarkerDao().create(new Marker("Muzej", "The Archaeological Museum in Zagreb",45.810919, 15.977239,"19 Nikola Subic Zrinski Square, 10000 Zagreb",R.drawable.arheoloski_muzej,"+385 14873101","amz@amz.hr","http://www.amz.hr/home.aspx","10:00 - 18:00"));
            getMarkerDao().create(new Marker("Muzej", "Technical Museum in Zagreb",45.803794, 15.964960,"Savska cesta 18, 10000 Zagreb",R.drawable.tehnicki_muzej,"+385 14844050","info@tehnicki-muzej.hr","http://tehnicki-muzej.hr/en/","9:00 - 17:00"));
            getMarkerDao().create(new Marker("Muzej", "Ethnographic Museum Zagreb",45.808394, 15.969200,"Trg Ivana Mažuranića 14, 10000 Zagreb",R.drawable.etnografski_muzej,"+385 14826220","EMZ@emz.hr","http://www.emz.hr/intro.html","10:00 - 18:00"));
            getMarkerDao().create(new Marker("Kino","Kino Europa",45.811910, 15.973277,"Varšavska 3, 10000 Zagreb",R.drawable.kino_europa,"+385 14872888","info@kinoeuropa.hr ","http://www.kinoeuropa.hr/","11:00 - 21:00"));
            getMarkerDao().create(new Marker("Kafić","Kino Europa",45.811910, 15.973277,"Varšavska 3, 10000 Zagreb",R.drawable.kino_europa,"+385 14872888","info@kinoeuropa.hr ","http://www.kinoeuropa.hr/","08:30-00:00"));
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int arg2, int arg3) {
        try {
            TableUtils.dropTable(connectionSource, Marker.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Marker, Integer> getMarkerDao() throws SQLException {
        if (markersDao == null) {
            markersDao = getDao(Marker.class);
        }
        return markersDao;
    }

    @Override
    public void close() {
        super.close();
        markersDao = null;
    }
}