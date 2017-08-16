package logica;

import persistencia.FachadaPers;

/** Clase para el alta de un usuario en la agenda.
 * @author
 *
 */
public class Usuario {
	
	private int id_usuario;
	private String nombre;
	private String apellido;
	private boolean habilitado;
	private CuentasAgenda cuentas;
	
	FachadaPers BD = FachadaPers.getInstancia();
	
	/** Constructor vacío.
	 */
	public Usuario() {
	}
	
	/** Constructor de la clase.
	 * @param id_usuario
	 * @param nombre
	 * @param apellido
	 * @param cuenta
	 * @param habilitado
	 */
	public Usuario(int id_usuario, String nombre, String apellido, CuentaAgenda cuenta, boolean habilitado){
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.habilitado = habilitado;
		this.cuentas = new CuentasAgenda();
		this.cuentas.Insertar(cuenta);
	}
	
	/** Método para persistir el usuario.
	 * @param nombre
	 * @param cuenta
	 */
	public void guardarUsuarioBD(String nombre, String cuenta){
		BD.GuardaUsuariosAgendaCliente(nombre, cuenta);
	}
	
	/** Método que retorna el id de usuario (local de la agenda).
	 * @return id_usuario
	 */
	public int getId_usuario() {
		return id_usuario;
	}
	
	/** Método que retorna el nombre del usuario.
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/** Método que retorna el apellido del usuario.
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}
	
	/** Método que retorna la habilitación del usuario
	 * @return True habilitado. False NO habilitado.
	 */
	public boolean getHabilitado() {
		return habilitado;
	}
	
	/** Método que retorna las cuentas del usuario.
	 * @return
	 */
	public CuentasAgenda getCuentas() {
		return cuentas;
	}
	
	/** Método para modificar la habilitación del usuario.
	 * @param habilitado
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}