package componentes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Recibo;

public class ReciboTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Recibo> recibo = new ArrayList<>();
	
	private String[] columnNames = {"ID", "Tipo de Recibo", "Valor", "Quem fez Pagamento", "CPF", "Valor por extenso", "Referente", "Observa��o", "Data de emiss�o", "Local", "Data Escrita", "Quem Recebeu", "CPF de quem Recebeu"};
	private Class<?>[] columnTypes = {String.class, String.class, Double.class, String.class, Long.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, Long.class};
	
	public void load(List<Recibo> recibo){
		this.recibo = recibo;
		fireTableDataChanged();
	}
	
	public Recibo getPessoaAt(int index){
		if(this.recibo.size() <= 0){
			return null;
		}
		return this.recibo.get(index);
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.recibo.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(this.recibo.size() <= 0){
			return null;
		}
		Recibo p = this.recibo.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getTipoRecibo();
		case 2:
			return p.getValor();
		case 3:
			return p.getqFp();
		case 4:
			return p.getCpf();
		case 5:
			return p.getValorEscrito();
		case 6:
			return p.getReferente();
		case 7:
			return p.getObs();
		case 8:
			return p.getDataEmissao();
		case 9:
			return p.getLocal();
		case 10:
			return p.getDataEscrita();
		case 11:
			return p.getQuemRecebe();
		case 12:
			return p.getCpfQuemRecebe();
		}
		return null;
	}
	
}
