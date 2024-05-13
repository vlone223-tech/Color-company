import java.util.Comparator;
public class MalarFargComparatorPris implements Comparator<MalarFarg> {
    @Override
    public int compare(MalarFarg o1, MalarFarg o2) {

        if  (o1 == null && o2 == null){
            return 0;  // Båda objekten anses lika
        } else if ( o1 == null) {
            return -1; // Nullobjekt kommer före icke-nullobjekt
        } else if (o2 == null) {
            return 1; // Nullobjekt kommer efter icke-nullobjekt
        }

        // Få pris från MalarFarg getPris

        int pris1 = o1.getPris();
        int pris2 = o2.getPris();

        // Jämför pris utan skiftläge
        return  Integer.compare(pris1,pris2);
    }// end of compare metod
} //end class
