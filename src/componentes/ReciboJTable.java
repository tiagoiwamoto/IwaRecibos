package componentes;

import java.util.List;

import javax.swing.JTable;

import model.Recibo;

public class ReciboJTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7309522795073409377L;
	private ReciboTableModel tableModel;
	
	public ReciboJTable(){
		this.tableModel = new ReciboTableModel();
		setModel(this.tableModel);
	}

	public void load(List<Recibo> recibo){
		this.tableModel.load(recibo);
	}
	
	public Recibo getReciboSelecionado(){
		int index = getSelectedRow();
		return this.tableModel.getPessoaAt(index);
	}
}
