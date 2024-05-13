import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MalarFargGui {
    private JPanel mainPanel;
    private JTextField txtproduktid;
    private JLabel lblproduktid;
    private JLabel lblprodukttyp;
    private JTextField txtprodukttyp;
    private JLabel lbltillverkare;
    private JTextField txttillverkare;
    private JLabel lblpris;
    private JTextField txtpris;
    private JLabel lblfarg;
    private JTextField txtfarg;
    private JLabel lblprodukt_mangd_i_liter;
    private JTextField txtprodukt_mangd_i_liter;
    private JLabel lblanvandings_omrade;
    private JTextField txtanvandings_omrade;
    private JTextArea textArea1;
    private JButton btnAdd;
    private JButton btnupdate;
    private JButton btndelete;
    private JTextField txtsokPris;
    private JTextField txtSokProdukttype;
    private JButton btnsokPris;
    private JButton btnSokProdukttype;
    private JLabel lblAntal;
    private JTextField txtAntalILagar;
    private JScrollPane scrollPanelTextArea;
    private JButton btnVisaAlla;
    private JLabel lblTotaltAntal;
    private JLabel lblAntalILagar;
    private JButton btnSearchColor;
    private JButton btnPriceAscending;
    private JButton btnPriceDescending;
    private JButton btn;
    private JButton btnLowStock;
    private JTextField txtSearchColor;
    private JTextField txtAreaOfUse;
    private JButton btnAreaOfUse;
    private JTextField txtantal;
    private List<MalarFarg> malarFargLista;
    private int currentIndex;



    public MalarFargGui() {


        malarFargLista = new ArrayList<>();
        malarFargLista.add( new MalarFarg("NSJ-AM-001"," väggfärg","Nordsjö",230,"White Darling",1,"Utomhus",5));
        malarFargLista.add( new MalarFarg("NSJ-AM-002"," väggfärg","Nordsjö",500,"Light Le Mans",4,"Inomhus",10));

        currentIndex = 0;

        showMalarFargInTextArea();
        // Uppdatera totalt antal produkter
        updateTotalAntalLabel();
        showMalarFargInTextField(malarFargLista.get(currentIndex));


        scrollPanelTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelTextArea.setViewportView(textArea1);



        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Hämta värdena från textfält
                String produktid = txtproduktid.getText().trim();

                // Kontrollera om produktid redan finns i listan
                boolean exists = malarFargLista.stream().anyMatch(fargen -> fargen != null && fargen.getProduktid().equals(produktid));

                if (exists) {
                    // Produkt med samma produktid finns redan i listan
                    JOptionPane.showMessageDialog(null, "Produkt-id  " + produktid + " finns redan i er samling");
                } else {
                    // Hämta övriga värden från textfält
                    String produkttyp = txtprodukttyp.getText();
                    String tillverkare = txttillverkare.getText();
                    int pris = Integer.parseInt(txtpris.getText());
                    String farg = txtfarg.getText();
                    int produkt_mangd_i_liter = Integer.parseInt(txtprodukt_mangd_i_liter.getText());
                    String anvandings_omrade = txtanvandings_omrade.getText();
                    int antalILagar = Integer.parseInt(txtAntalILagar.getText());

                    // Skapa ett farg objekt
                    MalarFarg fargen = new MalarFarg(
                            produktid, produkttyp, tillverkare, pris, farg,
                            produkt_mangd_i_liter, anvandings_omrade, antalILagar);

                    // Lägg till fargen på listan
                    malarFargLista.add(fargen);


                    currentIndex = malarFargLista.size() - 1; // Ställ in index till det sista objektet
                    showMalarFargInTextArea();
                    // Uppdatera totalt antal produkter
                    updateTotalAntalLabel();
                }// end if

            }
        }); //End btnadd
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uppdateraProduktid = txtproduktid.getText().trim();
                for (MalarFarg uppdateraID : malarFargLista){
                    if (uppdateraID != null && uppdateraID.getProduktid().equals(uppdateraProduktid)){
                        uppdateraID.setPris(Integer.parseInt(txtpris.getText()));
                        uppdateraID.setAntalILagar(Integer.parseInt(txtAntalILagar.getText()));
                        JOptionPane.showMessageDialog(null, "Produkt med ID "+txtproduktid.getText() + " är uppdaterad!");
                        showMalarFargInTextArea();
                        // Uppdatera totalt antal produkter
                        updateTotalAntalLabel();

                    }//end if

                }//end for
            }
        }); // end btnupadate
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taBortPorudktid = txtproduktid.getText().trim();
                Iterator<MalarFarg> iterator = malarFargLista.iterator();
                boolean hittat = false;

                while (iterator.hasNext()) {
                    MalarFarg bortTagenFarg = iterator.next();
                    if (bortTagenFarg != null && bortTagenFarg.getProduktid().equals(taBortPorudktid)){
                        iterator.remove();
                        JOptionPane.showMessageDialog(null, "Produkt-id "+txtproduktid.getText() + " har tagits bort");
                        showMalarFargInTextArea();
                        // Uppdatera totalt antal produkter
                        updateTotalAntalLabel();
                        hittat=true;
                        break;  //Gå ur while loopen efter att ha tagit bort eleven
                    } // end while
                }// end while
                if (!hittat) {
                    JOptionPane.showMessageDialog(null, "Du försökte ta bort produkten med ID " + txtproduktid.getText() + " men den fanns inte. ");
                    showMalarFargInTextArea();

                } // end if
            }
        }); // end btndelete
        btnsokPris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textArea1.setText(""); // Rensa textområdet

                // Få det maxpris som användaren har angett
                int soktMaximumPris;
                try {
                    soktMaximumPris = Integer.parseInt(txtsokPris.getText());
                    // Gå igenom malarfarglistan och visa produkter med ett pris som är lägre än eller lika med angivet maxpris
                    for (MalarFarg soktpris : malarFargLista) {
                        if (soktpris != null && soktpris.getPris() <= soktMaximumPris) {
                            textArea1.append(soktpris.toString() + "\n");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ogiltig input för maxpris. Var vänlig skriv in ett giltigt nummer.");
                    return;
                }



            }
        }); // END BTN SÖK PRIS
        btnSokProdukttype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textArea1.setText(""); // Rensa textområdet

                // Få produkttypen inskriven av användaren
                String soktProdukttyp = txtSokProdukttype.getText().trim();

                // Check if the entered product type is registered
                if (arProduktTypRegistrerad(soktProdukttyp)) {
                    // Gå igenom produktlistan och visa produkter med matchande produkttyp
                    for (MalarFarg soktProdukt : malarFargLista) {
                        if (soktProdukt != null && soktProdukt.getProdukttyp().trim().equalsIgnoreCase(soktProdukttyp)) {
                            textArea1.append(soktProdukt.toString() + "\n");
                        }// end if
                    } // end for
                } else {
                    JOptionPane.showMessageDialog(null, "Produkttypen är inte registrerad.");
                }

            }
        }); // end btn sök produkt type
        btnVisaAlla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMalarFargInTextArea();
                // Uppdatera totalt antal produkter
                updateTotalAntalLabel();
            }
        });
        btnLowStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (harLagtLager()) {
                    visaProdukterMedLagtILager();
                } else {
                    JOptionPane.showMessageDialog(null, "Inga produkter med lågt lager.");
                }
            }
        }); // end btn lowstock
        btnSearchColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String valdFarg = txtSearchColor.getText().trim();

                if (arfargRegistrerad(valdFarg)) {
                    sokProdukterEfterFarg(valdFarg);
                } else {
                    JOptionPane.showMessageDialog(null, "Färgen är inte registrerad.");
                }
            }
        });// end of btn search color


        btnAreaOfUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String soktomrade = txtAreaOfUse.getText().trim();


                if(soktomrade.equalsIgnoreCase("Inomhus")|| soktomrade.equalsIgnoreCase("Utomhus")){
                    sokProdukterEfterAnvandningsOmrade(soktomrade);
                } else  {
                    JOptionPane.showMessageDialog(null, "användningsområde kan bara vara: Inomhus eller Utomhus. skriv någon av dem.");
                }
            }
        });// end btn area use

        btnPriceAscending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorteraProdukterEfterPrisStigande();
            }
        });//end btn price asceding
        btnPriceDescending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorteraProdukterEfterPrisFallande();
            }
        });// btn price descending
    } // end Malar farg gui

    public static void main(String[] args) {
        JFrame frame = new JFrame("MalarFargGui");
        frame.setContentPane(new MalarFargGui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }// end of main panel

    private boolean isAnyTextFieldEmpty(){
        textArea1.setText("");
        return txtproduktid.getText().isEmpty()||
                txtprodukttyp.getText().isEmpty()||
                txttillverkare.getText().isEmpty()||
                txtpris.getText().isEmpty()||
                txtfarg.getText().isEmpty()||
                txtprodukt_mangd_i_liter.getText().isEmpty()||
                txtanvandings_omrade.getText().isEmpty() ||
                txtAntalILagar.getText().isEmpty();
    }// end of isAnyTextFieldEmpty metod
    private void showMalarFargInTextArea(){
        textArea1.setText(""); // Rensa textområdet
        for (MalarFarg fargen: malarFargLista){
            if(fargen != null){
                textArea1.append("Proudkt ID: " + fargen.getProduktid() + "\n"+
                        "Produkt Typ: " + fargen.getProdukttyp() + "\n"+
                        "Tillverkare: " + fargen.getTillverkare() + "\n"+
                        "Pris: " + fargen.getPris() + " SEK "+ "\n"+
                        "Farg: " + fargen.getFarg() + "\n"+
                        "Produktmängd: "  + fargen.getProdukt_mangd_i_liter() + " L "+"\n"+
                        "Anvandings Omrade: " + fargen.getAnvandings_omrade() + "\n" +
                        "Antal i lager:"+ fargen.getAntalILagar()+" st"+"\n"+
                        "-*-* *-*-* *-*-* *-*-* *-*-* *-*-* *-" + "\n" + "\n");


            }//end if
        }// end for
    }// end of showMalarFargInTextField metod
    private void showMalarFargInTextField(MalarFarg farg){
        if (farg != null){
            txtproduktid.setText(farg.getProduktid());
            txtprodukttyp.setText(farg.getProdukttyp());
            txttillverkare.setText(farg.getTillverkare());
            txtpris.setText(String.valueOf(farg.getPris()));
            txtfarg.setText(farg.getFarg());
            txtprodukt_mangd_i_liter.setText(String.valueOf(farg.getProdukt_mangd_i_liter()));
            txtanvandings_omrade.setText(farg.getAnvandings_omrade());
            txtAntalILagar.setText(String.valueOf(farg.getAntalILagar()));
        } else {

            // Rensa fält om ingen farg är vald
            txtproduktid.setText("");
            txtprodukttyp.setText("");
            txttillverkare.setText("");
            txtpris.setText("");
            txtfarg.setText("");
            txtprodukt_mangd_i_liter.setText("");
            txtanvandings_omrade.setText("");
            txtAntalILagar.setText("");
        }//end if
    }// end of showMalarFargInTextField metod
    private int calculateTotalAntalILager() {
        // Beräkna den totala summan av "Antal i lager" för alla produkter
        int totalAntal = 0;
        for (MalarFarg fargen : malarFargLista) {
            if (fargen != null) {
                totalAntal += fargen.getAntalILagar();
            }
        }
        return totalAntal;
    }
    private void updateTotalAntalLabel() {
        // Uppdatera lblAntakILagar för att visa det totala antalet produkter
        lblAntalILagar.setText("" + calculateTotalAntalILager());
    }//end update total antal


    //  Visa produkter med lågt antal i lager
    private void visaProdukterMedLagtILager() {
        textArea1.setText("");
        for (MalarFarg produkt : malarFargLista) {
            //om antal i lagar är mindre än 5
            if (produkt != null && produkt.getAntalILagar() < 5) {
                textArea1.append(produkt.toString() + "\n");
            }//end if
        }// end for
    } // END Visa produkter med lågt antal i lager metod
    private boolean harLagtLager() {
        for (MalarFarg product : malarFargLista) {
            if (product != null && product.getAntalILagar() < 5) {
                return true; // Det finns produkter med lågt lager
            }
        }
        return false; // Det finns ingen produkter med lågt lager
    }// end har lågt antal



    private boolean arProduktTypRegistrerad(String produktType) {
        for (MalarFarg produkt : malarFargLista) {
            if (produkt != null && produkt.getProdukttyp().trim().equalsIgnoreCase(produktType)) {
                return true; // Produkttyp är registrerad
            }
        }
        return false; // Produkttyp är inte registrerad
    }//
    //Söka efter produkter efter färg
    private void sokProdukterEfterFarg(String color) {
        textArea1.setText("");
        for (MalarFarg produkt : malarFargLista) {
            if (produkt != null && produkt.getFarg().equalsIgnoreCase(color)) {
                textArea1.append(produkt.toString() + "\n");
            }//end if
        }//end for
    }//END OF Söka efter produkter efter färg metod
    private boolean arfargRegistrerad(String color) {
        for (MalarFarg produkt : malarFargLista) {
            if (produkt != null && produkt.getFarg().equalsIgnoreCase(color)) {
                return true; // Färg är registrerad
            }
        }
        return false; // Färg är inte registrerad
    }// end of boolean
    private void sokProdukterEfterAnvandningsOmrade(String anvandsTill){
        textArea1.setText("");

        for (MalarFarg produkt : malarFargLista){
            if (produkt != null && produkt.getAnvandings_omrade().equalsIgnoreCase(anvandsTill)){
                textArea1.append(produkt.toString() + "\n");
            }//end if
        }//end for
    }// end of SokProdukterEfterAnvandningsOmrade metod

    // Sortera i stigande ordning
    private void sorteraProdukterEfterPrisStigande() {
        Collections.sort(malarFargLista, new MalarFargComparatorPris());
        showMalarFargInTextArea();
    }

    // Sortera i fallande ordning
    private void sorteraProdukterEfterPrisFallande() {
        Collections.sort(malarFargLista, Collections.reverseOrder(new MalarFargComparatorPris()));
        showMalarFargInTextArea();
    }

} // end of class
