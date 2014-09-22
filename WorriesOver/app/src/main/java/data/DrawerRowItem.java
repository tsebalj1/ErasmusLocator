package data;

/**
 * Created by zubak on 18.4.2014..
 */
public class DrawerRowItem {
    private String naslov;
    private int slika;


    public DrawerRowItem(String naslov, int slika) {
        this.naslov = naslov;
        this.slika = slika;


    }

    public String getNaslov() {
        return naslov;
    }

    public int getSlika() {
        return slika;
    }
}
