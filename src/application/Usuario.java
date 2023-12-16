package application;

import javafx.beans.property.SimpleStringProperty;

public class Usuario {
	private SimpleStringProperty id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty correo;
	private SimpleStringProperty fechanac;
	private SimpleStringProperty pais;
	private SimpleStringProperty municipio;
	private SimpleStringProperty localidad;
	private SimpleStringProperty estado;
	public Usuario(String id, String nombre, String fechanac,  String correo,String pais, String municipio, String localidad, String estado) {
		this.id = new SimpleStringProperty(id);
		this.nombre = new SimpleStringProperty(nombre);
		this.fechanac = new SimpleStringProperty(fechanac);
		this.correo = new SimpleStringProperty(correo);
		this.pais = new SimpleStringProperty(pais);
		this.municipio = new SimpleStringProperty(municipio);
		this.localidad = new SimpleStringProperty(localidad);
		this.estado = new SimpleStringProperty(estado);
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
	
	public SimpleStringProperty nombreProperty() {
		return nombre;
	}
	public String getCorreo() {
		return correo.get();
	}
	public void setCorreo(String correo) {
		this.correo.set(correo);
	}
	
	public SimpleStringProperty correoProperty() {
		return correo;
	}
	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
	public SimpleStringProperty fechaNacProperty() {
		return fechanac;
	}
	public String getFechaNac() {
		return fechanac.get();
	}
	public void setFechaNac(String fechanac) {
		this.fechanac.set(fechanac);
	}
	
	public SimpleStringProperty paisProperty() {
		return pais;
	}
	public String getPais() {
		return pais.get();
	}
	public void setPais(String pais) {
		this.pais.set(pais);
	}
	
	public SimpleStringProperty municipioProperty() {
		return municipio;
	}
	public String getMunicipio() {
		return municipio.get();
	}
	public void setMunicipio(String municipio) {
		this.municipio.set(municipio);
	}
	
	public SimpleStringProperty localidadProperty() {
		return localidad;
	}
	public String getLocalidad() {
		return localidad.get();
	}
	public void setLocalidad(String localidad) {
		this.localidad.set(localidad);
	}
	
	public SimpleStringProperty estadoProperty() {
		return estado;
	}
	public String getEstado() {
		return estado.get();
	}
	public void setEstado(String estado) {
		this.estado.set(estado);
	}
}
