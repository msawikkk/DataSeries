import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class IOHelper {

    // Metoda dodająca odczyt do odpowiedniego czujnika,
    private static void addReadoutToSensor(ArrayList<Sensor> sensorList, String sensorName, Readout readout){

        Sensor existingOne = null;

        // jezeli lista jest pusta, dodajemy nowy czujnik
        if (sensorList.isEmpty()) {
            existingOne = new Sensor(sensorName);
            sensorList.add(existingOne);
        }

        // Szukamy czujnika o podanej nazwie w liście
        for (int i = 0; i < sensorList.size(); i++) {
            Sensor sensor = sensorList.get(i);
            if (sensor.getName().equals(sensorName)) {
                existingOne = sensor;
                break;
            }
        }
        // Jeśli nie znaleziono czujnika, dodajemy nowy
        if (existingOne == null) {
            existingOne = new Sensor(sensorName);
            sensorList.add(existingOne);
        }
        // Dodajemy odczyt do czujnika
        existingOne.addReadout(readout);
    }

    // Metoda odczytująca plik i zwracająca zawartość pliku
    static FileContent readFile(String filename, Logger logger) throws IOException {

        FileReader fr = new FileReader(filename);

        ArrayList<Sensor> sensors = new ArrayList<>();

        int noOfInvalidRecords = 0;

        String dummyName = "<N/A>";

        // Odczytujemy plik linia po linii
        try (BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] currentline = line.split(" ");

                    double value = Double.valueOf(currentline[0]);

                    // W zależności od liczby elementów w linii, dodajemy odpowiedni odczyt
                    if (currentline.length == 2) {
                        String uuid = currentline[1].replace("id:", "");
                        addReadoutToSensor(sensors, dummyName, new ReadoutWithUuid(value, uuid));

                    } else if (currentline.length == 1) {
                        addReadoutToSensor(sensors, dummyName, new Readout(value));

                    } else {
                        String uuid = currentline[1].replace("id:", "");
                        addReadoutToSensor(sensors, currentline[2], new ReadoutWithUuid(value, uuid));
                    }
                } catch (NumberFormatException ex) {

                    // Jeśli wystąpił błąd, zwiększamy liczbę nieprawidłowych rekordów
                    noOfInvalidRecords += 1;
                    logger.log("Faulty record in " + filename + ": " + line.toString());
                }
            }
        }
        // Zwracamy zawartość pliku
        return new FileContent(sensors, noOfInvalidRecords);
    }


    static String getOutputInfo(FileContent fContent, String title) {
        String output = "";

        for (Sensor sensor: fContent.getSensors()){
            output += String.format("\n------------\nSensor name: %s\n" +
                            "Length of the series: %d\nMax value: %s\nMin value: %s\nMean value: %.3f\n" +
                            "Median: %s\nNumber of central elements: %d",
                    sensor.getName(), sensor.getLengthOfData(), sensor.getMax(), sensor.getMin(),
                    sensor.getMean(), sensor.getMedian(), sensor.noOfCentralElements(sensor.getMean(),
                            (sensor.getMax().getValue() - sensor.getMin().getValue()) / 100));
        }
        // informacje wyjściowe
        return String.format("%s\nMarta Sawikowska, 285291", title) + output +
                String.format("\nNumber of invalid records: %d\n------------", fContent.getNoOfInvalidRecords());
    }
}
