package application;

public class Silueta {
	private String textura;
	private String nombre;
	private boolean ave;
	Silueta(String textura, String nombre, boolean ave){
		this.textura = textura;
		this.nombre = nombre;
		this.ave = ave;
	}
	
	public String getTextura() {
		return textura;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean getAve() {
		return ave;
	}
}
