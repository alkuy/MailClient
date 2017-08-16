package logica;

public class CuentaAgenda {
	
	private String cuentaNom;
	private String cuentaDom;
	
	public CuentaAgenda() {		
	}
	
	public CuentaAgenda(String cuentaNom, String cuentaDom) {
		this.cuentaNom = cuentaNom;
		this.cuentaDom = cuentaDom;
	}
	
	public String getCuentaDom() {
		return cuentaDom;
	}
	
	public String getCuentaNom() {
		return cuentaNom;
	}
}
