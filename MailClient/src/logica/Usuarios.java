package logica;

import java.util.Hashtable;

public class Usuarios {
	
	private Hashtable<String, Usuario> hUsu;
	
	
	/**
	 * M�todo constructor de la colecci�n usuarios.
	 */
	public Usuarios() {
		this.hUsu = new Hashtable<>();
	}
	
	/**
	 * M�todo que carga los usuarios desde la capa de persistencia.
	 */
	public void cargaUsuarios(){
		
	}

}
