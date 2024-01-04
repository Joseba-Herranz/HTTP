package http;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Introduce el nombre de la película para buscar en IMDB:");
        String movieName = inputScanner.nextLine();

        String query = movieName.replace(" ", "+");
        String urlString = "https://www.imdb.com/find?q=" + query;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(url.openStream());

            BufferedWriter writer = new BufferedWriter(new FileWriter("respuesta.html"));

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                writer.write(line);
                writer.newLine();
            }

            scanner.close();
            writer.close();
            inputScanner.close();
            System.out.println("La consulta se ha completado. Revisa el archivo respuesta.html");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
