import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Logger {
    // Lista przechowująca logi
    private ArrayList<String> logs = new ArrayList<>();

    // Konstruktor klasy Logger
    public Logger() {
    }

    // Metoda dodająca informacje do logów
    public void log(String info) {
        logs.add(info);
    }

    // Metoda czyszcząca logi
    public void clear(){
        logs.clear();
    }

    //  reprezentacja tekstową obiektu
    @Override
    public String toString() {
        String output = "";
        for (String log : logs) {
            output += log + "\n";
        }
        return output;
    }

    // Metoda zapisująca logi do pliku
    public void dumpToLogFile(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(this.toString());
        } catch (FileNotFoundException e) {
            // W przypadku błędu, rzucamy wyjątek
            throw new RuntimeException(e);
        }
    }
}
