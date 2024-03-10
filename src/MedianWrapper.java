import java.util.ArrayList;

public class MedianWrapper {


    private ArrayList<Readout> medianElems = new ArrayList<>();

    private double medianVal;

    // parzysta dlugosc
    public MedianWrapper(Readout elem) {
        medianElems.add(elem);
        medianVal = elem.getValue();
    }

    //nieparzysta dlugosc
    public MedianWrapper(Readout elem1, Readout elem2) {
        // Dodanie elementów do listy w odpowiedniej kolejności
        if (elem1.getValue() < elem2.getValue()) {
            medianElems.add(elem1);
            medianElems.add(elem2);
        } else {
            medianElems.add(elem2);
            medianElems.add(elem1);
        }
        // Obliczenie wartości mediany
        medianVal = (elem1.getValue() + elem2.getValue()) / 2;
    }

    // Przesłonięta metoda toString() zwracająca reprezentację tekstową obiektu
    @Override
    public String toString() {
        String medianStr = String.format("%.3f source: ", medianVal);
        medianStr += (medianElems.size() > 1)?
                medianElems.get(0) + "::" + medianElems.get(1):
                medianElems.get(0).toString();
        return medianStr;
    }
}
