package forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Recibo;
import componentes.ReciboJTable;
import reciboDao.ReciboDao;

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

public class FrmBuscaRecibo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txbBusca;
	private ReciboJTable tblRecibo;
	private Recibo rb;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			FrmBuscaRecibo dialog = new FrmBuscaRecibo();
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
	public FrmBuscaRecibo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmBuscaRecibo.class.getResource("/resources/appIcon.png")));
		setTitle("IwaRecibos - Pesquisa de Recibos");
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
		
		tblRecibo = new ReciboJTable();
		tblRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					rb = new Recibo();
					Recibo rbt = tblRecibo.getReciboSelecionado();
					rb.setCpf(String.valueOf(rbt.getCpf()));
					rb.setCpfQuemRecebe(String.valueOf(rbt.getCpfQuemRecebe()));
					rb.setDataEmissao(String.valueOf(rbt.getDataEmissao()));
					rb.setDataEscrita(String.valueOf(rbt.getDataEscrita()));
					rb.setId(String.valueOf(rbt.getId()));
					rb.setLocal(String.valueOf(rbt.getLocal()));
					rb.setObs(String.valueOf(rbt.getObs()));
					rb.setqFp(String.valueOf(rbt.getqFp()));
					rb.setQuemRecebe(String.valueOf(rbt.getQuemRecebe()));
					rb.setReferente(String.valueOf(rbt.getReferente()));
					rb.setTipoRecibo(String.valueOf(rbt.getTipoRecibo()));
					rb.setValor(Double.valueOf(rbt.getValor()));
					rb.setValorEscrito(String.valueOf(rbt.getValorEscrito()));
					setRecibo(rb);
					
					dispose();
				}
			}
		});
		carregarTabela(tblRecibo);
		for(int i = 0; i <= 12; i++){
			if(i == 0 || i == 3 || i == 8){
				continue;
			}
			
			tblRecibo.getColumnModel().getColumn(i).setMinWidth(0);
			tblRecibo.getColumnModel().getColumn(i).setMaxWidth(0);
		}
		
		tblRecibo.getColumnModel().getColumn(0).setHeaderValue("ID");
		tblRecibo.getColumnModel().getColumn(3).setHeaderValue("Quem fez Pagamento");
		tblRecibo.getColumnModel().getColumn(8).setHeaderValue("Data de Emiss�o");
		scrollPane.setViewportView(tblRecibo);
		{
			JLabel lblProcurar = new JLabel("Procurar:");
			panel.add(lblProcurar);
		}
		{
			txbBusca = new JTextField();
			txbBusca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ReciboDao rbd = new ReciboDao();
					if(txbBusca.getText().length() < 4){
						JOptionPane.showMessageDialog(null, "Informe um nome ou um cpf para buscar !");
					}else{
						try{
							tblRecibo.load(rbd.procuraRecibo(txbBusca.getText()));
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "N�o foi possivel carregar o banco de dados");
						}finally{
							if(tblRecibo.getRowCount() < 1){
								JOptionPane.showMessageDialog(null, "Nenhum cadastro foi encontrado !");
								txbBusca.setText("");
								carregarTabela(tblRecibo);
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
				ReciboDao rbd = new ReciboDao();
				if(txbBusca.getText().length() < 4){
					JOptionPane.showMessageDialog(null, "Informe um nome ou um cpf para buscar !");
				}else{
					try{
						tblRecibo.load(rbd.procuraRecibo(txbBusca.getText()));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "N�o foi possivel carregar o banco de dados");
					}finally{
						if(tblRecibo.getRowCount() < 1){
							JOptionPane.showMessageDialog(null, "Nenhum cadastro foi encontrado !");
							txbBusca.setText("");
							carregarTabela(tblRecibo);
						}
					}
				}
			}
		});
		panel.add(btnBuscar);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void setRecibo(Recibo rb){
		
	}
	
	public Recibo getRecibo(){
		return rb;
	}
	
	public void carregarTabela(ReciboJTable tabela){
		try{
			tabela.load(new ReciboDao().listaTodos());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "N�o foi possivel carregar o banco de dados");
		}	
	}
}
