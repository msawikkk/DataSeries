public class Readout implements Comparable <Readout> {

    private double value;


    public Readout(double value) {
        this.value = value;
    }


    public double getValue() {
        return value;
    }

    // Przesłonięta metoda compareTo() z interfejsu Comparable
    // Używana do porównywania obiektów klasy Readout
    @Override
    public int compareTo(Readout readout) {
        return Double.compare(this.value, readout.getValue());
    }


    @Override
    public String toString() {
        return String.format("%.3f", value);
    }
}
