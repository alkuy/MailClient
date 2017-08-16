package logica;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import logica.Usuario;
import persistencia.FachadaPers;

/** Clase para la colecci�n de usuarios de la agenda de una cuenta.
 * @author 
 *
 */
public class Usuarios {
	
	private Hashtable<String, Usuario> hUsu;
	FachadaPers BD = FachadaPers.getInstancia(); 
	
	/** M�todo constructor de la colecci�n usuarios.
	 */
	public Usuarios() {
		this.hUsu = new Hashtable<>();
	}
	
	/** M�todo que da de alta un usuario en la agenda.
	 * @param usuario
	 */
	public void altaUsuAgenda(Usuario usuario){
		
		hUsu.put(String.valueOf(usuario.getId_usuario()), usuario);
		
	}
	
	/** M�todo que retorna el �ltimo ID del usuario en la colecci�n.
	 * Este id es local a la agenda del usuario.
	 * @return id_usuario
	 */
	public int ultimoID(){
		Enumeration<Usuario> auxUsus = hUsu.elements();
		int id=0;
		while(auxUsus.hasMoreElements()){
			Usuario usu = auxUsus.nextElement();
			id = usu.getId_usuario();
		}
		return id;
	}
	
	/** M�todo para imprimir datos del usuario, es para pruebas.
	 */
	public void imprimir(){
		Enumeration<Usuario> auxUsus = hUsu.elements();
		Usuario usu;
		while(auxUsus.hasMoreElements()){
			usu = auxUsus.nextElement();
			usu.getCuentas().imprimir();
		}
	}
	
	/** M�todo que retorna la colecci�n de usuarios.
	 * @return Hashtable<String, Usuario> colecci�n de usuarios.
	 */
	public Hashtable<String, Usuario> getCollection(){
		return this.hUsu;
	}
	
	/** M�todo que retorna la existencia del usuario con nombre, apellido y cuenta especificado.
	 * @param cuentaNom
	 * @param cuentaDom
	 * @return True si existe cuenta. False si NO existe cuenta.
	 */
	public boolean existeUsu(String cuentaNom, String cuentaDom){
		Enumeration<Usuario> auxUsus = hUsu.elements();
		Usuario usu;
		while(auxUsus.hasMoreElements()){
			usu = auxUsus.nextElement();
			return usu.getCuentas().existeCuenta(cuentaNom, cuentaDom);
		}
		return false;
	}
	
	/** M�todo que retorna el modelo para la tabla de agenda.
	 * @return DefaultTableModel modelo para la tabla agenda.
	 */
	public DefaultTableModel DevUsuAgenda(){
		String col[] = {"Nombre","Cuenta"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		
		Enumeration<Usuario> eUsu = hUsu.elements();
		Usuario usu;
		
		while(eUsu.hasMoreElements()){
			usu = eUsu.nextElement();
			
			Iterator<CuentaAgenda> auxCuentas = usu.getCuentas().getCollection().iterator();
			CuentaAgenda cuenta = auxCuentas.next();			
			String carga [] = {usu.getNombre()+" "+usu.getApellido(), cuenta.getCuentaNom()+"@"+cuenta.getCuentaDom()};
			modelo.addRow(carga);
		}
		return modelo;
	}
	
	/** M�todo que da de baja a un usuario con la cuenta especificada en la agenda
	 * @param usu
	 * @param cuenta
	 */
	public void Borrar(String usu, String cuenta){
		Enumeration<Usuario> eUsu = hUsu.elements();
		Usuario auxUsu;
		while(eUsu.hasMoreElements()){
			auxUsu = eUsu.nextElement();
			if(auxUsu.getCuentas().Borrar(cuenta)){ //Si es TRUE es porque elimino la cuenta
				String key;
			}
			if(!tieneCuentas(auxUsu)){
				hUsu.remove(String.valueOf(auxUsu.getId_usuario()));
				break;
			}
		}
	}
	
	/** M�todo que indica si el usuario en la agenda tiene cuentas o no.
	 * @param usu
	 * @return True si tiene cuentas. False si NO tiene cuentas.
	 */
	//True si tiene cuentas
	//False si NO tiene cuentas
	public boolean tieneCuentas(Usuario usu){
		return !(usu.getCuentas().getCollection().isEmpty());
	}
}
