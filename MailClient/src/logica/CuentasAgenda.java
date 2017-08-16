package logica;

import java.util.ArrayList;
import java.util.Iterator;

public class CuentasAgenda {
	
private ArrayList<CuentaAgenda> setCuentas;
	
	public CuentasAgenda() {
		this.setCuentas = new ArrayList<>();
	}
	
	public void Insertar(CuentaAgenda cuenta){
		this.setCuentas.add(cuenta);
	}
	
	public void imprimir(){
		Iterator<CuentaAgenda> auxCuentas = setCuentas.iterator();
		CuentaAgenda auxCuenta;
		System.out.println("CUENTAS");
		while(auxCuentas.hasNext()){
			auxCuenta = auxCuentas.next();
			System.out.println(auxCuenta.getCuentaNom() +" "+ auxCuenta.getCuentaDom());
		}
	}
	
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
	
	public ArrayList<CuentaAgenda> getCollection(){
		return this.setCuentas;
	}
	
	public boolean Borrar(String cuenta){
		Iterator<CuentaAgenda> auxCuentas = setCuentas.iterator();
		CuentaAgenda auxCuenta;
		int cont=0;
		String cuentaNom = cuenta.substring(0, cuenta.indexOf("@"));
		String cuentaDom = cuenta.substring(cuenta.indexOf("@")+1);
		while(auxCuentas.hasNext()){
			auxCuenta = auxCuentas.next();
			System.out.println("CUENTASAG "+auxCuenta.getCuentaNom()+" "+cuentaNom+" "+auxCuenta.getCuentaDom()+" "+cuentaDom);
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
