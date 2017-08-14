package persistencia;

public class Agenda {
	private String NomUsuario, CuentaUsuario;
	
	
	public Agenda (String NomUsuario, String CuentaUsuario){
		this.NomUsuario = NomUsuario;
		this.CuentaUsuario = CuentaUsuario;
	}
	
	public Agenda (){
	}

	/**
	 * @return el nomUsuario
	 */
	public String getNomUsuario() {
		return NomUsuario;
	}

	/**
	 * @param nomUsuario el nomUsuario a establecer
	 */
	public void setNomUsuario(String nomUsuario) {
		NomUsuario = nomUsuario;
	}

	/**
	 * @return el cuentaUsuario
	 */
	public String getCuentaUsuario() {
		return CuentaUsuario;
	}

	/**
	 * @param cuentaUsuario el cuentaUsuario a establecer
	 */
	public void setCuentaUsuario(String cuentaUsuario) {
		CuentaUsuario = cuentaUsuario;
	}
	
	
}
