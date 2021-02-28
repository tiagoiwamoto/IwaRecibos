package forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.DbConnect;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Color;

public class FrmRelatorios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txbData;
	private JFormattedTextField txbDataEntre;
	private JFormattedTextField txbDataAte;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			FrmRelatorios dialog = new FrmRelatorios();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 * @throws ParseException 
//	 */
	public FrmRelatorios() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmRelatorios.class.getResource("/resources/appIcon.png")));
		setResizable(false);
		setModal(true);
		setTitle("IwaRecibos - Relat\u00F3rios");
		setBounds(100, 100, 308, 314);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 261, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblGerarPorData = new JLabel("Gerar por DATA");
		
		MaskFormatter dataMask = new MaskFormatter("##/##/####");
		txbData = new JFormattedTextField(dataMask);
		txbData.setText(dataAtual());
		txbData.setColumns(10);
		
		JButton btnGerarData = new JButton("Gerar");
		btnGerarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				Connection conn = DbConnect.getConnection();
				HashMap hash = new HashMap();
				hash.put("dataEscolhida", txbData.getText());
				JasperPrint print = JasperFillManager.fillReport("relatorio/RelatorioReciboData.jasper", hash, conn);
				JasperViewer view = new JasperViewer(print, false);
				view.setTitle("IwaRecibos - Relatórios");
				view.setVisible(true);
				view.setDefaultLookAndFeelDecorated(isDefaultLookAndFeelDecorated());
				}catch(Exception e){
					System.out.println("Erro: " + e);
				}finally{
					fechaForm(false);
				}
			}
		});
		
		JLabel lblGerarPorTipo = new JLabel("Gerar por Tipo");
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Credor", "Devedor"}));
		
		JButton btnGerarTipo = new JButton("Gerar");
		btnGerarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechaForm(false);
				try{
				Connection conn = DbConnect.getConnection();
				HashMap hash = new HashMap();
				hash.put("tipoEscolhido", String.valueOf(cbTipo.getSelectedItem()));
				JasperPrint print = JasperFillManager.fillReport("relatorio/RelatorioReciboTipo.jasper", hash, conn);
				JasperViewer view = new JasperViewer(print, false);
				view.setTitle("IwaRecibos - Relatórios");
				view.setVisible(true);
				view.setDefaultLookAndFeelDecorated(isDefaultLookAndFeelDecorated());
				}catch(Exception e){
					System.out.println("Erro: " + e);
				}
			}
		});
		
		JLabel lblEntre = new JLabel("Entre");
		
		txbDataEntre = new JFormattedTextField(dataMask);
		txbDataEntre.setText(dataAtual());
		txbDataEntre.setColumns(10);
		
		txbDataAte = new JFormattedTextField(dataMask);
		txbDataAte.setText(dataAtual());
		txbDataAte.setColumns(10);
		
		JLabel lblAt = new JLabel("At\u00E9");
		
		JButton btnGerarEntreData = new JButton("Gerar");
		btnGerarEntreData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechaForm(false);
				try{
				Connection conn = DbConnect.getConnection();
				HashMap hash = new HashMap();
				hash.put("dataInicial", String.valueOf(txbDataEntre.getText()));
				hash.put("dataFinal", String.valueOf(txbDataAte.getText()));
				JasperPrint print = JasperFillManager.fillReport("relatorio/RelatorioReciboDataEntre.jasper", hash, conn);
				JasperViewer view = new JasperViewer(print, false);
				view.setTitle("IwaRecibos - Relatórios");
				view.setVisible(true);
				view.setDefaultLookAndFeelDecorated(isDefaultLookAndFeelDecorated());
				}catch(Exception e){
					System.out.println("Erro: " + e);
				}
			}
		});
		
		JLabel lblGerarRelatrioDe = new JLabel("Gerar Relat\u00F3rio de Clientes");
		
		JButton btnClientesComCpf = new JButton("Pessoa f\u00EDsica");
		btnClientesComCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechaForm(false);
				try{
				Connection conn = DbConnect.getConnection();
				HashMap hash = new HashMap();
				JasperPrint print = JasperFillManager.fillReport("relatorio/RelatorioClienteFisico.jasper", hash, conn);
				JasperViewer view = new JasperViewer(print, false);
				view.setTitle("IwaRecibos - Relatórios");
				view.setVisible(true);
				view.setDefaultLookAndFeelDecorated(isDefaultLookAndFeelDecorated());
				}catch(Exception e){
					System.out.println("Erro: " + e);
				}
			}
		});
		
		JButton btnPessoaJuridica = new JButton("Pessoa Juridica");
		btnPessoaJuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechaForm(false);
				try{
				Connection conn = DbConnect.getConnection();
				HashMap hash = new HashMap();
				JasperPrint print = JasperFillManager.fillReport("relatorio/RelatorioClienteJuridico.jasper", hash, conn);
				JasperViewer view = new JasperViewer(print, false);
				view.setTitle("IwaRecibos - Relatórios");
				view.setVisible(true);
				view.setDefaultLookAndFeelDecorated(isDefaultLookAndFeelDecorated());
				}catch(Exception e){
					System.out.println("Erro: " + e);
				}
			}
		});
		
		JLabel lblAviso1 = new JLabel("ATEN\u00C7\u00C3O: Pode demorar alguns segundos para o");
		lblAviso1.setForeground(Color.RED);
		
		JLabel lblAviso2 = new JLabel("relat\u00F3rio ser gerado. (m\u00E9dia de 5 segundos)");
		lblAviso2.setForeground(Color.RED);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGerarPorData)
								.addComponent(lblGerarPorTipo)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(cbTipo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txbData, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txbDataEntre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEntre))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAt)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnGerarTipo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(txbDataAte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnGerarEntreData, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
											.addComponent(btnGerarData))))
								.addComponent(lblGerarRelatrioDe))
							.addContainerGap(15, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnClientesComCpf)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPessoaJuridica)
							.addGap(28))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAviso1)
							.addContainerGap(216, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAviso2)
							.addContainerGap(216, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGerarPorData)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGerarData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGerarPorTipo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGerarTipo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAt)
						.addComponent(lblEntre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txbDataAte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnGerarEntreData))
						.addComponent(txbDataEntre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblGerarRelatrioDe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPessoaJuridica)
						.addComponent(btnClientesComCpf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAviso1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblAviso2))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void fechaForm(boolean valor){
		this.setVisible(valor);
		}
	
	public String dataAtual(){
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		String diaHoraAtual = dateFormat.format(date).toString();
		/*
		Calendar calendario = Calendar.getInstance();
		calendario.getTime();
		String diaHoraAtual = calendario.getTime().toString();
		*/
		return diaHoraAtual;
	}
}
