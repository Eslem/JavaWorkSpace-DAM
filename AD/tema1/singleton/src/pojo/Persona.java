package pojo;

public class Persona {
	
	private String nombre;
	private Integer documento;
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(Integer documento) {
		this.documento = documento;
	}
	/**
	 * @return the documento
	 */
	public Integer getDocumento() {
		return documento;
	}
}
