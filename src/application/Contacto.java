package application;

import javafx.beans.property.SimpleStringProperty;

public class Contacto {
	private SimpleStringProperty id;
	private SimpleStringProperty idusuario;
	private SimpleStringProperty nombre;
	private SimpleStringProperty parentesco;
	private SimpleStringProperty pais;
	private SimpleStringProperty municipio;
	private SimpleStringProperty localidad;
	private SimpleStringProperty direccion;
	private SimpleStringProperty numerotelefono;
	private SimpleStringProperty correo;
	public Contacto(String id, String idusuario, String nombre, String parentesco, String pais, String municipio, String localidad, String direccion, String numerotelefono,String correo) {
		this.id = new SimpleStringProperty(id);
		this.idusuario = new SimpleStringProperty(idusuario);
		this.nombre = new SimpleStringProperty(nombre);
		this.parentesco = new SimpleStringProperty(parentesco);
		this.pais = new SimpleStringProperty(pais);
		this.municipio = new SimpleStringProperty(municipio);
		this.localidad = new SimpleStringProperty(localidad);
		this.direccion = new SimpleStringProperty(direccion);
		this.numerotelefono = new SimpleStringProperty(numerotelefono);
		this.correo = new SimpleStringProperty(correo);
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
	
	public SimpleStringProperty idusuarioProperty() {
		return idusuario;
	}
	public String getIDusuario() {
		return idusuario.get();
	}
	public void setIDusuario(String id) {
		this.idusuario.set(id);
	}
	
	public SimpleStringProperty nombreProperty() {
		return nombre;
	}
	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
	public SimpleStringProperty parentescoProperty() {
		return parentesco;
	}
	public String getParentesco() {
		return parentesco.get();
	}
	public void setParentesco(String parentesco) {
		this.parentesco.set(parentesco);
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
	
	public String getDireccion() {
		return direccion.get();
	}
	public void setDireccion(String direccion) {
		this.direccion.set(direccion);
	}
	
	public SimpleStringProperty direccionProperty() {
		return direccion;
	}
	
	public String getNumTel() {
		return numerotelefono.get();
	}
	public void setNumTel(String numerotelefono) {
		this.numerotelefono.set(numerotelefono);
	}
	
	public SimpleStringProperty numTelProperty() {
		return numerotelefono;
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
	
}
