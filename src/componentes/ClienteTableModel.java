package componentes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Clientes;

public class ClienteTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Clientes> pessoas = new ArrayList<>();
	
	private String[] columnNames = {"ID", "Nome", "CPF", "RG", "DataNascimento", "Habilitacao", "Endereco", "Cidade", "Estado", "CEP", "Pais", "Obs"};
	private Class<?>[] columnTypes = {String.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
	
	public void load(List<Clientes> pessoas){
		this.pessoas = pessoas;
		fireTableDataChanged();
	}
	
	public Clientes getPessoaAt(int index){
		if(this.pessoas.size() <= 0){
			return null;
		}
		return this.pessoas.get(index);
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.pessoas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(this.pessoas.size() <= 0){
			return null;
		}
		Clientes p = this.pessoas.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getCpf();
		case 3:
			return p.getRg();
		case 4:
			return p.getDtNasc();
		case 5:
			return p.getHabilitacao();
		case 6:
			return p.getEndereco();
		case 7:
			return p.getCidade();
		case 8:
			return p.getEstado();
		case 9:
			return p.getCep();
		case 10:
			return p.getPais();
		case 11:
			return p.getObs();
		}
		return null;
	}
	
}
