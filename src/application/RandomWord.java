package application;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomWord {
	private Map<String,String> words;
	private Random rand;
	public RandomWord() {
		words = new HashMap<>();
		rand= new Random();
		words.put("COCINA", "Zona en un hogar donde se manejan alimentos");
		words.put("LAPIZ", "Objeto con punta de grafito utilizado para escribir");
		words.put("PLUMA", "El pelaje de los pájaros");
		words.put("AZUL", "Color que se ve comúnmente en el cielo");
		words.put("DESECHO", "Todo objeto que ya no se necesita");
		words.put("OIDO", "Órgano del cuerpo humano usado para escuchar");
		words.put("NARIZ", "Órgano del cuerpo humano usado para oler");
		words.put("OJO", "Órgano utilizado para la vista");
		words.put("FRESA", "Fruta de color rojo con semillas en su exterior");
		words.put("TECHO", "Parte superior de una casa, comúnmente cubierto de tejas");
		words.put("AGUA", "Elemento del que está cubierto la mayoría de la superficie de la Tierra");
		words.put("COLUMPIO" ,"Estructura para diversión en con un asiento colgante");
		words.put("PAPEL", "Material obtenido tras procesar árboles, encontrado en cuadernos");
		words.put("PUERTA", "Abertura que permita acceder al interior de un lugar");
		words.put("HUESO", "Pieza dura y resistente del esqueleto de varios seres vivos");
	}
	
	public String getRandomWord() {
		return words.keySet().toArray()[rand.nextInt(words.size())].toString();
	}
}
