package logica;

import java.util.Hashtable;

public class Usuarios {
	
	private Hashtable<String, Usuario> hUsu;
	
	
	/**
	 * Método constructor de la colección usuarios.
	 */
	public Usuarios() {
		this.hUsu = new Hashtable<>();
	}
	
	/**
	 * Método que carga los usuarios desde la capa de persistencia.
	 */
	public void cargaUsuarios(){
		
	}

}
