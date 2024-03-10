import java.util.ArrayList;
import java.util.Collections;

public class Sensor {

    private String name;
    // Lista przechowująca odczyty z czujnika
    private ArrayList<Readout> data = new ArrayList<>();

    // konstruktor klasy Sensor
    public Sensor(String name){
        this.name = name;
    }

    // Metoda zwracająca nazwę czujnika
    public String getName() {
        return name;
    }

    // Metoda dodająca odczyt do listy odczytów
    public void addReadout(Readout readout){
        data.add(readout);
    }

    // maksymalny odczyt
    public Readout getMax() {
        Readout max = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getValue() > max.getValue()) {
                max = data.get(i);
            }
        }
        return max;
    }

    // minimalny odczyt
    public Readout getMin() {
        Readout min = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getValue() < min.getValue()) {
                min = data.get(i);
            }
        }
        return min;
    }

    // srednia
    public double getMean() {
        double suma = 0;
        for (int i = 0; i < data.size(); i++) {
            suma += data.get(i).getValue();
        }
        return suma / data.size();
    }

    // mediana
    public MedianWrapper getMedian() {
        Collections.sort(data);
        if (data.size() % 2 != 0) {
            Readout l1 = data.get(data.size() / 2);
            return new MedianWrapper(l1);
        } else {
            Readout l1 = data.get((data.size() / 2) - 1);
            Readout l2 = data.get(data.size() / 2);
            return new MedianWrapper(l1, l2);
        }
    }

    // liczba elementów centralnych
    public int noOfCentralElements(double mean, double eps) {
        int suma = 0;
        for (int i = 0; i < data.size(); i++) {
            if (Math.abs(mean - data.get(i).getValue()) < eps) {
                suma += 1;
            }
        }
        return suma;
    }

    //długość listy odczytów
    public int getLengthOfData(){
        return data.size();
    }
}
