import java.util.Comparator;
public class MalarFargComparatorProduktTyp implements Comparator<MalarFarg> {
    @Override
    public int compare(MalarFarg o1, MalarFarg o2) {

        if  (o1 == null && o2 == null){
            return 0;  // Båda objekten anses lika
        } else if ( o1 == null) {
            return -1; // Nullobjekt kommer före icke-nullobjekt
        } else if (o2 == null) {
            return 1; // Nullobjekt kommer efter icke-nullobjekt
        }

        // Få Produkt Type från MalarFarg getProduktTyp

        String produktTyp1 = o1.getProdukttyp();
        String produktTyp2 = o2.getProdukttyp();

        // Jämför pris utan skiftläge
        return produktTyp1.compareToIgnoreCase(produktTyp2) ;

    }// end of compare metod
} // end of class 
