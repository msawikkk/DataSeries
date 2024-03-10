public class ReadoutWithUuid extends Readout {
    // identyfikator odczytu
    private final String uuid;

    // Konstruktor klasy ReadoutWithUuid
    public ReadoutWithUuid(double value, String uuid) {
        // Wywołanie konstruktora klasy nadrzędnej
        super(value);
        this.uuid = uuid;
    }


    // reprezentacja tekstową obiektu
    @Override
    public String toString() {
        return String.format("%.3f", getValue()) + " [" + uuid + "]";
    }
}
