package logica;

import java.util.ArrayList;

public class Cuentas {
	
	private ArrayList<Cuenta> setCuentas;
	
	public Cuentas() {
		this.setCuentas = new ArrayList<>();
	}
	
	public void Insertar(Cuenta cuenta){
		this.setCuentas.add(cuenta);
	}

}
