package logica;

import java.util.ArrayList;

public class Telefonos {
	
	private ArrayList<Telefono> setTels;
	
	public Telefonos() {
		this.setTels = new ArrayList<>();
	}
	
	public void Insertar (Telefono tel){
		this.setTels.add(tel);
	}

}
