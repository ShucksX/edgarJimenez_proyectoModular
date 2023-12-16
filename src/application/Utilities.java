package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Utilities {
	public static int windowWidth = 1400;
	public static int windowHeight = 750;
	private static String baseUrl = "http://localhost";
	public static String stringToUTF(String textToEncode) {
		String codedString = textToEncode.replace(" ", "%20");
		return codedString;
	}
	
	public static int calcularEdad(String userID) throws IOException, InterruptedException, ParseException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				("http://shucksopage.000webhostapp.com/Alz/Resultado/obtenerfechanac.php?iduser="
		+userID)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if(!response.body().contains("noe#")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechanac = LocalDate.parse(response.body(), formatter);
			LocalDate date = LocalDate.now();
			Period periodo = Period.between(fechanac, date);
			return periodo.getYears();
		}
		else {
			return -1;
		}
	}
	
	public static String getBaseURL() {
		return baseUrl;
	}
}
