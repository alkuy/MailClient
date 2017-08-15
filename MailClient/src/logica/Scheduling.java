package logica;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.FachadaPers;

public class Scheduling {

	private FachadaPers BD = FachadaPers.getInstancia();
	private String [] Dominios = BD.GetDominio();
	private String [] Cuentas = BD.GetCuentas();
	private String [] Dominio;
	private boolean Dom, cta;
	private Correos correos = Correos.getInstancia();
//	Dom = false;
//	cta = false;
	
	public Scheduling(){
		
	}
	
	public DefaultTableModel ReOrdenar(){
		int i;
		String col[] = {"NomEmisor","PassEmisor", "CuentaEmisor", "CuentaDest", "Asunto", "Texto", "Prioridad", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		DefaultTableModel modeloOrdenado = new DefaultTableModel(col,0);
		modelo = correos.DevTablaSalidaPri();
		JTable T = new JTable(modelo);
		
		for (i = 0; i < modelo.getRowCount(); i++){
			if (Integer.valueOf(modelo.getValueAt(i, 6).toString()) == 1){
				String Carga [] = {modelo.getValueAt(i, 0).toString(), modelo.getValueAt(i, 1).toString(), modelo.getValueAt(i, 2).toString(), modelo.getValueAt(i, 3).toString(), modelo.getValueAt(i, 4).toString(), modelo.getValueAt(i, 5).toString(), modelo.getValueAt(i, 6).toString(),  modelo.getValueAt(i, 7).toString()};
				modeloOrdenado.addRow(Carga);
				modelo.removeRow(i);
				i--;
			}		
		}
		for (i = 0; i < modelo.getRowCount(); i++){
			if (Integer.valueOf(modelo.getValueAt(i, 6).toString()) == 2){
				String Carga [] = {modelo.getValueAt(i, 0).toString(), modelo.getValueAt(i, 1).toString(), modelo.getValueAt(i, 2).toString(), modelo.getValueAt(i, 3).toString(), modelo.getValueAt(i, 4).toString(), modelo.getValueAt(i, 5).toString(), modelo.getValueAt(i, 6).toString(),  modelo.getValueAt(i, 7).toString()};
				modeloOrdenado.addRow(Carga);
				modelo.removeRow(i);
				i--;
			}		
		}
		for (i = 0; i < modelo.getRowCount(); i++){
			if (Integer.valueOf(modelo.getValueAt(i, 6).toString()) == 3){
				String Carga [] = {modelo.getValueAt(i, 0).toString(), modelo.getValueAt(i, 1).toString(), modelo.getValueAt(i, 2).toString(), modelo.getValueAt(i, 3).toString(), modelo.getValueAt(i, 4).toString(), modelo.getValueAt(i, 5).toString(), modelo.getValueAt(i, 6).toString(),  modelo.getValueAt(i, 7).toString()};
				modeloOrdenado.addRow(Carga);
				modelo.removeRow(i);
				i--;
			}		
		}
		
		return modeloOrdenado;
	}
	
	public DefaultTableModel AlgoritmoPrioridad(){
		int contador, i;
		boolean dos = false;
		boolean tres = false;
		String col[] = {"NomEmisor","PassEmisor", "CuentaEmisor", "CuentaDest", "Asunto", "Texto","Prioridad", "Fecha"};
		String colo[] = {"NomEmisor","PassEmisor", "CuentaEmisor", "CuentaDest", "Asunto", "Texto", "Fecha"};
		DefaultTableModel modeloOrdenado = new DefaultTableModel(col,0);
		DefaultTableModel Schedule = new DefaultTableModel(colo,0);
		modeloOrdenado = this.ReOrdenar();
		
		while (modeloOrdenado.getRowCount() != 0){
			contador = 0;
			i = 0;
			while ((i < modeloOrdenado.getRowCount() || contador < 4) && (modeloOrdenado.getRowCount() != 0)){
				i=0;
				String Carga [] = {modeloOrdenado.getValueAt(i, 0).toString(), modeloOrdenado.getValueAt(i, 1).toString(), modeloOrdenado.getValueAt(i, 2).toString(), modeloOrdenado.getValueAt(i, 3).toString(), modeloOrdenado.getValueAt(i, 4).toString(), modeloOrdenado.getValueAt(i, 5).toString(), modeloOrdenado.getValueAt(i, 7).toString()};
				Schedule.addRow(Carga);
				modeloOrdenado.removeRow(i);
				contador++;
				i++;
				if (contador == 3){
					while ((!dos && i < modeloOrdenado.getRowCount()) && (modeloOrdenado.getRowCount() != 0)){
						i=0;
						if (Integer.valueOf(modeloOrdenado.getValueAt(i, 6).toString()) == 2){
							String CargaDos [] = {modeloOrdenado.getValueAt(i, 0).toString(), modeloOrdenado.getValueAt(i, 1).toString(), modeloOrdenado.getValueAt(i, 2).toString(), modeloOrdenado.getValueAt(i, 3).toString(), modeloOrdenado.getValueAt(i, 4).toString(), modeloOrdenado.getValueAt(i, 5).toString(), modeloOrdenado.getValueAt(i, 7).toString()};
							Schedule.addRow(CargaDos);
							modeloOrdenado.removeRow(i);
							i++;
							contador++;
							dos = true;
						}
						else{
							i++;
						}
					}
					dos = false;
				}
				if (contador == 4){
					i=0;
					while ((!tres && i < modeloOrdenado.getRowCount()) && (modeloOrdenado.getRowCount() != 0)){
						if (Integer.valueOf(modeloOrdenado.getValueAt(i, 6).toString()) == 3){
							String CargaDos [] = {modeloOrdenado.getValueAt(i, 0).toString(), modeloOrdenado.getValueAt(i, 1).toString(), modeloOrdenado.getValueAt(i, 2).toString(), modeloOrdenado.getValueAt(i, 3).toString(), modeloOrdenado.getValueAt(i, 4).toString(), modeloOrdenado.getValueAt(i, 5).toString(), modeloOrdenado.getValueAt(i, 7).toString()};
							Schedule.addRow(CargaDos);
							modeloOrdenado.removeRow(i);
							i++;
							contador++;
							tres = true;
						}
						else{
							i++;
						}
					}
					tres = false;
				}
			}
		}
		
		return Schedule;
		
	}
}
