package componentes;

import java.util.List;

import javax.swing.JTable;

import model.Clientes;

public class ClienteJTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7309522795073409377L;
	private ClienteTableModel tableModel;
	
	public ClienteJTable(){
		this.tableModel = new ClienteTableModel();
		setModel(this.tableModel);
	}

	public void load(List<Clientes> pessoas){
		this.tableModel.load(pessoas);
	}
	
	public Clientes getPessoaSelecionado(){
		int index = getSelectedRow();
		return this.tableModel.getPessoaAt(index);
	}
}
