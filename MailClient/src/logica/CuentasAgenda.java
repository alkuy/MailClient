package logica;

import java.util.ArrayList;
import java.util.Iterator;

/** Clase para la colección de cuentas de los usuarios en agenda.
 * @author Carlitox
 *
 */
public class CuentasAgenda {
	
private ArrayList<CuentaAgenda> setCuentas;
	
	/** Constructor de la clase.
	 */
	public CuentasAgenda() {
		this.setCuentas = new ArrayList<>();
	}
	
	/** Método que da de alta una cuenta en la colección.
	 * @param cuenta
	 */
	public void Insertar(CuentaAgenda cuenta){
		this.setCuentas.add(cuenta);
	}
	
	/** Método para imprimir la colección de cuentas, es para pruebas.
	 */
	public void imprimir(){
		Iterator<CuentaAgenda> auxCuentas = setCuentas.iterator();
		CuentaAgenda auxCuenta;
		while(auxCuentas.hasNext()){
			auxCuenta = auxCuentas.next();
		}
	}
	
	/** Método que retorna la existencia de una cuenta.
	 * @param cuentaNom
	 * @param cuentaDom
	 * @return True si existe cuenta. False si NO existe cuenta.
	 */
	//True si existe cuenta
	//False si NO existe cuenta
	public boolean existeCuenta(String cuentaNom, String cuentaDom){
		Iterator<CuentaAgenda> auxCuentas = setCuentas.iterator();
		CuentaAgenda auxCuenta;
		while(auxCuentas.hasNext()){
			auxCuenta = auxCuentas.next();
			if(auxCuenta.getCuentaNom().equals(cuentaNom) && auxCuenta.getCuentaDom().equals(cuentaDom))
				return true;
		}
		
		return false;
	}
	
	/** Método que retorna la colección de cuentas.
	 * @return ArrayList<CuentaAgenda>
	 */
	public ArrayList<CuentaAgenda> getCollection(){
		return this.setCuentas;
	}
	
	/** Método para dar de baja una cuenta del usuario en agenda.
	 * @param cuenta
	 * @return True si la baja fue satisfactoria. False si no se pudo realizar la baja.
	 */
	public boolean Borrar(String cuenta){
		Iterator<CuentaAgenda> auxCuentas = setCuentas.iterator();
		CuentaAgenda auxCuenta;
		int cont=0;
		String cuentaNom = cuenta.substring(0, cuenta.indexOf("@"));
		String cuentaDom = cuenta.substring(cuenta.indexOf("@")+1);
		while(auxCuentas.hasNext()){
			auxCuenta = auxCuentas.next();
			if(auxCuenta.getCuentaNom().equals(cuentaNom) && auxCuenta.getCuentaDom().equals(cuentaDom)){
				this.setCuentas.remove(cont);
				return true;
			}
			cont++;
		}
		this.imprimir();
		return false;
	}
}
