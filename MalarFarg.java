public class MalarFarg {
    private String produktid; // set
    private String produkttyp;
    private String tillverkare;
    private int pris;
    private String farg; // set
    private int produkt_mangd_i_liter;
    private String anvandings_omrade;
    private int antalILagar;


    // Konstruktor

    public MalarFarg(String produktid, String produkttyp, String tillverkare, int pris, String farg, int produkt_mangd_i_liter, String anvandings_omrade, int antalILagar) {
        this.produktid = produktid;
        this.produkttyp = produkttyp;
        this.tillverkare = tillverkare;
        this.pris = pris;
        this.farg = farg;
        this.produkt_mangd_i_liter = produkt_mangd_i_liter;
        this.anvandings_omrade = anvandings_omrade;
        this.antalILagar = antalILagar;
    }


    // getter & setter

    public String getProduktid() {
        return produktid;
    }

    public void setProduktid(String produktid) {
        this.produktid = produktid;
    }

    public String getProdukttyp() {
        return produkttyp;
    }

    public void setProdukttyp(String produkttyp) {
        this.produkttyp = produkttyp;
    }

    public String getTillverkare() {
        return tillverkare;
    }

    public void setTillverkare(String tillverkare) {
        this.tillverkare = tillverkare;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }


    public String getFarg() {
        return farg;
    }

    public void setFarg(String farg) {
        this.farg = farg;
    }

    public int getProdukt_mangd_i_liter() {
        return produkt_mangd_i_liter;
    }

    public void setProdukt_storlek_i_liter(int produkt_storlek_i_liter) {
        this.produkt_mangd_i_liter = produkt_storlek_i_liter;
    }

    public String getAnvandings_omrade() {
        return anvandings_omrade;
    }

    public void setAnvandings_omrade(String anvandings_omrade) {
        this.anvandings_omrade = anvandings_omrade;
    }

    public int getAntalILagar() {
        return antalILagar;
    }

    public void setAntalILagar(int antalILagar) {
        this.antalILagar = antalILagar;
    }

    @Override
    public String toString() {
        return "ProudktID: " + produktid + "\n"+
                "Produkttyp: " + produkttyp + "\n"+
                "Tillverkare: " + tillverkare + "\n"+
                "Pris: " + pris + " SEK"+"\n"+
                "Färg: " + farg + "\n"+
                "Produktmängd: " + produkt_mangd_i_liter + "L"+"\n"+
                "Anvandings Omrade" + anvandings_omrade + "\n" +
                "Antal i lager: "+ antalILagar + " st" + "\n"+
                "-*-* *-*-* *-*-* *-*-* *-*-* *-*-* *-" + "\n" + "\n";
    } // end override toString metod
}// end class
