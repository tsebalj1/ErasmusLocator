package data;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zubak on 8.4.2014..
 */
@DatabaseTable
public class Marker implements Parcelable {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private String nazivObjekta;
    @DatabaseField
    private double latitude;
    @DatabaseField
    private double longitude;
    @DatabaseField
    private String tip;
    @DatabaseField
    private String adresa;
    @DatabaseField
    private int img;
    @DatabaseField
    private String telefon;
    @DatabaseField
    private String eMail;
    @DatabaseField
    private String url;
    @DatabaseField
    private String radnoVrijeme;
    private int icon;



    public Marker() {
    }

    public Marker(Parcel source) {
        this.tip = source.readString();
        this.nazivObjekta = source.readString();
        this.latitude = source.readDouble();
        this.longitude = source.readDouble();
        this.adresa = source.readString();
        this.img = source.readInt();
        this.telefon = source.readString();
        this.eMail = source.readString();
        this.url = source.readString();
        this.radnoVrijeme = source.readString();
    }


    public Marker(String tip, String nazivObjekta, double latitude, double longitude,
                  String adresa,  int img, String telefon,String eMail, String url,String radnoVrijeme) {
        this.tip = tip;
        this.nazivObjekta = nazivObjekta;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adresa = adresa;
        this.img = img;
        this.telefon = telefon;
        this.eMail = eMail;
        this.url = url;
        this.radnoVrijeme = radnoVrijeme;

    }

    public String getTelefon() {
        return telefon;
    }


    public int getImg() {
        return img;
    }

    public int get_id() {
        return _id;
    }

    public String getTip() {
        return tip;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getId() {
        return _id;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNazivObjekta() {
        return nazivObjekta;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return String.format("Id = %d, tip = %s, naziv = %s latitude = %f, longitude = %f, opis = %s", _id, tip, nazivObjekta, latitude, longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Marker> CREATOR = new Parcelable.Creator<Marker>() {
        @Override
        public Marker createFromParcel(Parcel source) {
            return new Marker(source);
        }

        @Override
        public Marker[] newArray(int size) {
            return new Marker[size];
        }

    };

    public String geteMail() {
        return eMail;
    }

    public String getUrl() {
        return url;
    }

    public String getRadnoVrijeme() {
        return radnoVrijeme;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tip);
        dest.writeString(nazivObjekta);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(adresa);
        dest.writeInt(img);
        dest.writeString(telefon);
        dest.writeString(eMail);
        dest.writeString(url);
        dest.writeString(radnoVrijeme);
    }
}
