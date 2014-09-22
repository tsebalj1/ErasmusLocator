package data;

import java.util.ArrayList;

/**
 * Created by zubak on 21.4.2014..
 */
public class MenuRowItem {
    private String naslov;
    private int slika;
    private int boja;


    public MenuRowItem(String naslov,int slika, int boja) {

        this.slika = slika;
        this.boja = boja;
        this.naslov = naslov;
    }

    public String getNaslov() {
        return naslov;
    }

    public int getSlika() {
        return slika;
    }

    public int getBoja() {
        return boja;
    }
}
