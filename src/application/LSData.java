package application;

import javafx.beans.property.SimpleStringProperty;

public class LSData {
	private SimpleStringProperty id;
	private SimpleStringProperty idusuario;
	private SimpleStringProperty tiempo;
	private SimpleStringProperty fecharesultado;
	public LSData(String id, String idusuario, String tiempo,  String fecharesultado) {
		this.id = new SimpleStringProperty(id);
		this.idusuario = new SimpleStringProperty(idusuario);
		this.tiempo = new SimpleStringProperty(tiempo);
		this.fecharesultado = new SimpleStringProperty(fecharesultado);
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
	
	public String getTiempo() {
		return tiempo.get();
	}
	public void setTiempo(String tiempo) {
		this.tiempo.set(tiempo);
	}
	
	public SimpleStringProperty tiempoProperty() {
		return tiempo;
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
}
