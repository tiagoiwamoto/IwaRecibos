package forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Clientes;
import componentes.ClienteJTable;
import reciboDao.ClienteDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FrmBuscaCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txbBusca;
	private ClienteJTable tblCliente;
	private int acaoTabela;
	private Clientes cl;
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			FrmBuscaCliente dialog = new FrmBuscaCliente();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 */
	public FrmBuscaCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmBuscaCliente.class.getResource("/resources/appIcon.png")));
		setTitle("IwaRecibos - Pesquisa de Clientes");
		setModal(true);
		setBounds(100, 100, 588, 418);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();

		
		JLabel lblCliquexPara = new JLabel("Clique 2x para visualizar as informa\u00E7\u00F5es no formul\u00E1rio");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblCliquexPara)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCliquexPara))
		);
		
		tblCliente = new ClienteJTable();
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2){
						
						cl = new Clientes();
						Clientes clt = tblCliente.getPessoaSelecionado();
						cl.setCep(String.valueOf(clt.getCep()));
						cl.setCidade(String.valueOf(clt.getCidade()));
						cl.setCpf(String.valueOf(clt.getCpf()));
						cl.setDtNasc(String.valueOf(clt.getDtNasc()));
						cl.setEndereco(String.valueOf(clt.getEndereco()));
						cl.setEstado(String.valueOf(clt.getEstado()));
						cl.setHabilitacao(String.valueOf(clt.getHabilitacao()));
						cl.setId(String.valueOf(clt.getId()));
						cl.setNome(String.valueOf(clt.getNome()));
						cl.setObs(String.valueOf(clt.getObs()));
						cl.setPais(String.valueOf(clt.getPais()));
						cl.setRg(String.valueOf(clt.getRg()));
						setPessoaSelecionada(cl);

					fechaForm();
					//recibo.setVisible(true);
				}
			}
		});
		for(int i = 0; i <= 11; i++){
			if(i == 0 || i == 1 || i == 2){
				continue;
			}
			
			tblCliente.getColumnModel().getColumn(i).setMinWidth(0);
			tblCliente.getColumnModel().getColumn(i).setMaxWidth(0);
		}
		
		tblCliente.getColumnModel().getColumn(0).setHeaderValue("ID");
		tblCliente.getColumnModel().getColumn(1).setHeaderValue("Nome");
		tblCliente.getColumnModel().getColumn(2).setHeaderValue("CPF");
		carregarTabela(tblCliente);
		scrollPane.setViewportView(tblCliente);
		{
			JLabel lblProcurar = new JLabel("Procurar:");
			panel.add(lblProcurar);
		}
		{
			txbBusca = new JTextField();
			txbBusca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ClienteDao cld = new ClienteDao();
					if(txbBusca.getText().length() < 4){
						JOptionPane.showMessageDialog(null, "Informe um nome ou um cpf para buscar !");
					}else{
						try{
							tblCliente.load(cld.procuraClientes(txbBusca.getText()));
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "N�o foi possivel carregar o banco de dados");
						}finally{
							if(tblCliente.getRowCount() < 1){
								JOptionPane.showMessageDialog(null, "Nenhum cadastro foi encontrado !");
								txbBusca.setText("");
								carregarTabela(tblCliente);
							}
						}
					}
				}
			});
			panel.add(txbBusca);
			txbBusca.setColumns(30);
		}
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteDao cld = new ClienteDao();
				if(txbBusca.getText().length() < 4){
					JOptionPane.showMessageDialog(null, "Informe um nome ou um cpf para buscar !");
				}else{
					try{
						tblCliente.load(cld.procuraClientes(txbBusca.getText()));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "N�o foi possivel carregar o banco de dados");
					}finally{
						if(tblCliente.getRowCount() < 1){
							JOptionPane.showMessageDialog(null, "Nenhum cadastro foi encontrado !");
							txbBusca.setText("");
							carregarTabela(tblCliente);
						}
					}
				}
				
				
			}
		});
		panel.add(btnBuscar);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void setPessoaSelecionada(Clientes p){
		
	}
	
	public Clientes getPessoaSelecionada(){
		return cl;
	}
	
	public void fechaForm(){
		this.setVisible(false);
	}
	
	public void carregarTabela(ClienteJTable tabela){
		try{
			tabela.load(new ClienteDao().listaTodos());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "N�o foi possivel carregar o banco de dados");
		}	
	}
}
