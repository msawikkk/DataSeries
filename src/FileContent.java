import java.util.ArrayList;

public class FileContent {

    // Lista przechowująca obiekty Sensor
    private ArrayList<Sensor> sensors;
    // Liczba nieprawidłowych rekordów
    private int noOfInvalidRecords;

    // Konstruktor
    public FileContent(ArrayList<Sensor> sensors, int noOfInvalidRecords){
        this.sensors = sensors;
        this.noOfInvalidRecords = noOfInvalidRecords;
    }

    //  lista obiektów Sensor
    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    // liczba nieprawidłowych rekordów
    public int getNoOfInvalidRecords() {
        return noOfInvalidRecords;
    }
}
