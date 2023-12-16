package application;

import javafx.beans.property.SimpleStringProperty;

public class Palabra {
	private SimpleStringProperty id;
	private SimpleStringProperty idusuario;
	private SimpleStringProperty palabra1;
	private SimpleStringProperty palabra2;
	private SimpleStringProperty palabra3;
	private SimpleStringProperty status;
	private SimpleStringProperty fechaaccesible;
	private SimpleStringProperty fecharesultado;
	private SimpleStringProperty puntos;
	public Palabra(String id, String idusuario, String palabra1,String palabra2,String palabra3,String status,String fechaaccesible,  String fecharesultado, String puntos) {
		this.id = new SimpleStringProperty(id);
		this.idusuario = new SimpleStringProperty(idusuario);
		this.palabra1 = new SimpleStringProperty(palabra1);
		this.palabra2 = new SimpleStringProperty(palabra2);
		this.palabra3 = new SimpleStringProperty(palabra3);
		if(status.equals("1")) {
			this.status = new SimpleStringProperty("Completado");
		}
		else {
			this.status = new SimpleStringProperty("Sin responder");
		}
		this.fechaaccesible = new SimpleStringProperty(fechaaccesible);
		if (fecharesultado.equals("")) {
			this.fecharesultado = new SimpleStringProperty("N/A");
		}
		else {
			this.fecharesultado = new SimpleStringProperty(fecharesultado);
		}
		if (puntos.equals("")) {
			this.puntos = new SimpleStringProperty("N/A");
		}
		else {
			this.puntos = new SimpleStringProperty(puntos);
		}
	}
	
	public SimpleStringProperty idProperty() {
		return id;
	}
	public String getID() {
		return id.get();
	}
	public void setID(String id) {
		this.id.set(id);
	}
	
	public String getIdUsuario() {
		return idusuario.get();
	}
	public void setIdUsuario(String idusuario) {
		this.idusuario.set(idusuario);
	}
	public SimpleStringProperty idUsuarioProperty() {
		return idusuario;
	}
	
	public String getPalabra1() {
		return palabra1.get();
	}
	public void setPalabra1(String palabra1) {
		this.palabra1.set(palabra1);
	}
	public SimpleStringProperty palabra1Property() {
		return palabra1;
	}

	public String getPalabra2() {
		return palabra2.get();
	}
	public void setPalabra2(String palabra2) {
		this.palabra2.set(palabra2);
	}
	public SimpleStringProperty palabra2Property() {
		return palabra2;
	}
	
	public String getPalabra3() {
		return palabra3.get();
	}
	public void setPalabra3(String palabra3) {
		this.palabra3.set(palabra3);
	}
	public SimpleStringProperty palabra3Property() {
		return palabra3;
	}
	
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	public SimpleStringProperty statusProperty() {
		return status;
	}
	
	public String getFechaAcc() {
		return fechaaccesible.get();
	}
	public void setFechaAcc(String fechaaccesible) {
		this.fechaaccesible.set(fechaaccesible);
	}
	public SimpleStringProperty fechaAccProperty() {
		return fechaaccesible;
	}
	
	public String getFechaRes() {
		return fecharesultado.get();
	}
	public void setFechaRes(String fecharesultado) {
		this.fecharesultado.set(fecharesultado);
	}
	
	public SimpleStringProperty fechaResProperty() {
		return fecharesultado;
	}
	
	public String getPuntos() {
		return puntos.get();
	}
	public void setPuntos(String puntos) {
		this.puntos.set(puntos);
	}
	
	public SimpleStringProperty puntosProperty() {
		return puntos;
	}
}
