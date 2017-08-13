package logica;

public class Usuario {
	
	private int id_usuario;
	private String nombre;
	private String apellido;
	private String calle;
	private String nro_puerta;
	private String apto;
	private boolean habilitado;
	private Cuentas cuentas;
	private Telefonos tels;
	
	public Usuario() {
	}
	
	public Usuario(int id_usuario, String nombre, String apellido, String calle, 
			String nro_puerta, String apto, boolean habilitado, Cuentas cuentas, Telefonos tels){
		
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calle = calle;
		this.nro_puerta = nro_puerta;
		this.apto = apto;
		this.habilitado = habilitado;
		this.cuentas = cuentas;
		this.tels = tels;
	}
	
	public Usuario (int id_usuario, String nombre){
		this.id_usuario = id_usuario;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getCalle() {
		return calle;
	}
	
	public String getNro_puerta() {
		return nro_puerta;
	}
	
	public String getApto() {
		return apto;
	}
	
	public boolean getHabilitado() {
		return habilitado;
	}
	
	public Cuentas getCuentas() {
		return cuentas;
	}
	
	public Telefonos getTels() {
		return tels;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public void setNro_puerta(String nro_puerta) {
		this.nro_puerta = nro_puerta;
	}
	
	public void setApto(String apto) {
		this.apto = apto;
	}
	
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public Usuario cargaDesdeArch(int id_usuario){
		Usuario usu = new Usuario(id_usuario, nombre, apellido, calle, nro_puerta, apto, habilitado, cuentas, tels);
		
		return usu;
	}

}