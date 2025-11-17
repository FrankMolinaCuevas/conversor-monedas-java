import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HttpClient cliente = HttpClient.newHttpClient();
        String url = "https://v6.exchangerate-api.com/v6/ae60e7f11c808f766aa28436/latest/USD";

        try {
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> respuesta = cliente.send(
                    solicitud,
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonObject jsonObject = JsonParser.parseString(respuesta.body()).getAsJsonObject();
            JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");

            double USD_ARS = tasas.get("ARS").getAsDouble();
            double USD_BOB = tasas.get("BOB").getAsDouble();
            double USD_BRL = tasas.get("BRL").getAsDouble();

            Scanner sc = new Scanner(System.in);

            int opcion = 0;

            while (opcion != 7) {
                Conversor.mostrarMenu();
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese cantidad en USD: ");
                        double cantidad = sc.nextDouble();
                        double resultado = cantidad * USD_ARS;
                        System.out.println(cantidad + " USD = " + resultado + " ARS");
                    }
                    case 2 -> {
                        System.out.print("Ingrese cantidad en ARS: ");
                        double cantidad = sc.nextDouble();
                        double resultado = cantidad / USD_ARS;
                        System.out.println(cantidad + " ARS = " + resultado + " USD");
                    }
                    case 3 -> {
                        System.out.print("Ingrese cantidad en USD: ");
                        double cantidad = sc.nextDouble();
                        double resultado = cantidad * USD_BOB;
                        System.out.println(cantidad + " USD = " + resultado + " BOB");
                    }
                    case 4 -> {
                        System.out.print("Ingrese cantidad en BOB: ");
                        double cantidad = sc.nextDouble();
                        double resultado = cantidad / USD_BOB;
                        System.out.println(cantidad + " BOB = " + resultado + " USD");
                    }
                    case 5 -> {
                        System.out.print("Ingrese cantidad en USD: ");
                        double cantidad = sc.nextDouble();
                        double resultado = cantidad * USD_BRL;
                        System.out.println(cantidad + " USD = " + resultado + " BRL");
                    }
                    case 6 -> {
                        System.out.print("Ingrese cantidad en BRL: ");
                        double cantidad = sc.nextDouble();
                        double resultado = cantidad / USD_BRL;
                        System.out.println(cantidad + " BRL = " + resultado + " USD");
                    }
                    case 7 -> System.out.println("Saliendo…");
                    default -> System.out.println("Opción inválida");
                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
