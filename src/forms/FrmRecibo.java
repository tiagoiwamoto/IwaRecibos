package forms;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Random;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ToolTipManager;
import javax.swing.text.MaskFormatter;

import model.Recibo;
import reciboDao.ReciboDao;
import utils.GeraHtml;
import utils.ImprimirArquivo;
import utils.LeitorData;
import utils.LeitorDinheiro;
import utils.MoedaFormat;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class FrmRecibo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblStatus;
	private String idtemp = "";
	private JFormattedTextField txbValor;
	private JTextField txbQeP;
	private JButton btnBuscaUser;
	private JComboBox cbTipoRecibo;
	private JTextField txbReferente;
	private JTextField txbObs;
	private JTextField txbDataExtenso;
	private JTextField txbValorExtenso;
	private JTextField txbLocal;
	private JTextField txbQrP;
	private JButton btnBuscaUserQrP;
	private JFormattedTextField txbDataEmissao;
	private JCheckBox chckDataEditar;
	private JCheckBox chckValorEditar;
	private JCheckBox chckbxCpf;
	private JCheckBox chckbxCnpj;
	private JFormattedTextField txbCpfCnpj;
	private JCheckBox chckbxCpf_1;
	private JCheckBox chckbxCnpj_1;
	private JFormattedTextField txbAssCpfCnpj;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JLabel lblNovoRecibo;
	private JLabel lblEditarRecibo;
	private JLabel lblDelete;
	private JLabel lblProcura;
	private JLabel lblFechar;
	private JLabel lblErroForm;
	private boolean botaoAcao;
	private String randID = "";
	private int salvarAcao;
	private JButton btnImprimir;
	private String cfgEmitente;
	private String cfgCpf;
	private String cfgCidade;
	private String cfgImg;
	private String cfgSkin;
	private String valorTemp;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			FrmRecibo dialog = new FrmRecibo();
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
	public FrmRecibo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmRecibo.class.getResource("/resources/appIcon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				getConfigRb();
				ToolTipManager ttm = ToolTipManager.sharedInstance();
				ttm.setInitialDelay(0);
			}
		});
		setTitle("IwaRecibos - Gerenciador de Recibos");
		setModal(true);
		setBounds(100, 100, 730, 490);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblTipoDeRecibo = new JLabel("Tipo de Recibo");
		lblTipoDeRecibo.setToolTipText("<html><p width=\"250\">Credor: Quando voc� recebe um pagamento.</p>"
				+ "<p width=\"250\"></p>"
				+ "<p width=\"250\">Devedor: Quando voc� efetua um pagamento.</p></html>");
		lblTipoDeRecibo.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/helpForm.png")));
		
		cbTipoRecibo = new JComboBox();
		cbTipoRecibo.setEnabled(false);
		cbTipoRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipoRecibo(cbTipoRecibo.getSelectedItem().toString());
			}
		});
		cbTipoRecibo.setModel(new DefaultComboBoxModel(new String[] {"SELECIONE", "Credor", "Devedor"}));
		
		//txbValor = new JFormattedTextField();
		//txbValor = new JFormattedTextField(new DecimalFormat("#,###,###.00"));
		
		txbValor = new JFormattedTextField();

		txbValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if((!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.')){
					e.consume();
				}
			}
		});
		String valor = txbValor.getText();
		txbValor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txbValor.getText().equals("")){
					txbValor.setToolTipText("AQUI");
					txbValor.setText("1");
					lblErroForm.setText("Informe um valor para continuar");
				}else{
					valorTemp = txbValor.getText();
					lblErroForm.setText("");
					LeitorDinheiro cw = LeitorDinheiro.getInstance();					
					txbValorExtenso.setText(cw.write(new BigDecimal(txbValor.getText())));	
					
					MoedaFormat m = new MoedaFormat();
					txbValor.setText(m.mascaraDinheiro(Double.parseDouble(valorTemp), MoedaFormat.DINHEIRO_REAL));
					
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				txbValor.setText("");
				txbValor.setFormatterFactory(null);
			}
		});
		txbValor.setEnabled(false);
		
		JLabel lblValor = new JLabel("Valor");

		lblValor.setToolTipText("<html><p width=\"200\">Para 10.00 Reais digite: 10</p>"
				+ "<p width=\"200\">Para 100.00 Reais digite: 100</p>"
				+ "<p width=\"200\">Para 1000.00 Reais digite: 1000</p>"
				+ "<p width=\"200\">Para 1000.50 Reais digite: 1000.50</p></html>");
		lblValor.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/helpForm.png")));
		
		txbQeP = new JTextField();
		txbQeP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txbQeP.getText().equals("") || txbQeP.getText().length() < 5){
					txbQeP.setToolTipText("AQUI");
					
					
					
					lblErroForm.setText("Informe quem efetuou o pagamento!");
				}else{
					lblErroForm.setText("");
				}
			}
		});
		txbQeP.setEnabled(false);
		txbQeP.setColumns(10);
		
		JLabel lblRecebiDequem = new JLabel("Recebi de (Quem efetuou o pagamento)");
		
		btnBuscaUser = new JButton("Procurar Usu\u00E1rio");
		btnBuscaUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmBuscaCliente bc = new FrmBuscaCliente();
				//fechaForm();
				bc.setVisible(true);
				
				txbQeP.setText(String.valueOf(bc.getPessoaSelecionada().getNome()));
				if(String.valueOf(bc.getPessoaSelecionada().getCpf()).length() > 11){
					chckbxCnpj.setSelected(true);
					try {
						txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					chckbxCpf.setSelected(true);
					try {
						txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
				
				txbCpfCnpj.setText(String.valueOf(bc.getPessoaSelecionada().getCpf()));
			}
		});
		btnBuscaUser.setEnabled(false);
		
		JLabel lblReferente = new JLabel("Referente \u00E0");
		
		txbReferente = new JTextField();
		txbReferente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbReferente.getText().length() < 5){
					
					lblErroForm.setText("Informe o motivo da emiss�o do recibo");
				}else{
					lblErroForm.setText("");
				}
			}
		});
		txbReferente.setEnabled(false);
		txbReferente.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		
		txbObs = new JTextField();
		txbObs.setEnabled(false);
		txbObs.setColumns(10);
		
		JLabel lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o");
		
		txbDataEmissao = new JFormattedTextField();
		txbDataEmissao.setEnabled(false);
		
		txbDataExtenso = new JTextField();
		txbDataExtenso.setEnabled(false);
		txbDataExtenso.setColumns(10);
		
		JLabel lblDataPorExtenso = new JLabel("Data por extenso");
		
		JLabel lblImportanciaDe = new JLabel("A import\u00E2ncia de");
		
		txbValorExtenso = new JTextField();
		txbValorExtenso.setEnabled(false);
		txbValorExtenso.setColumns(10);
		
		JLabel lblLocal = new JLabel("Local");
		
		txbLocal = new JTextField();
		txbLocal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbLocal.getText().length() < 5){
					
					lblErroForm.setText("Informe um local com pelo menos 5 letras");
				}else{
					lblErroForm.setText("");
				}
			}
		});
		txbLocal.setEnabled(false);
		txbLocal.setColumns(10);
		
		chckDataEditar = new JCheckBox("Editar");
		chckDataEditar.setEnabled(false);
		chckDataEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckDataEditar.isSelected()){
					txbDataExtenso.setEnabled(true);
				}else{
					txbDataExtenso.setEnabled(false);
				}
			}
		});
		
		chckValorEditar = new JCheckBox("Editar");
		chckValorEditar.setEnabled(false);
		chckValorEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckValorEditar.isSelected()){
					txbValorExtenso.setEnabled(true);
				}else{
					txbValorExtenso.setEnabled(false);
				}
			}
		});
		
		txbQrP = new JTextField();
		txbQrP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbQrP.getText().length() < 5){
					lblErroForm.setText("Informe o nome de quem recebeu o pagamento");
					
				}else{
					lblErroForm.setText("");
				}
			}
		});
		txbQrP.setEnabled(false);
		txbQrP.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Assinante do recibo (Quem recebeu o pagamento)");
		
		btnBuscaUserQrP = new JButton("Procurar Usu\u00E1rio");
		btnBuscaUserQrP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				FrmBuscaCliente bc = new FrmBuscaCliente();
				//fechaForm();
				bc.setVisible(true);
				
				txbQrP.setText(String.valueOf(bc.getPessoaSelecionada().getNome()));
				if(String.valueOf(bc.getPessoaSelecionada().getCpf()).length() > 11){
					chckbxCnpj_1.setSelected(true);
					chckbxCpf_1.setSelected(false);
					
						txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
					
				}else{
					chckbxCpf_1.setSelected(true);
					chckbxCnpj_1.setSelected(false);

						txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
					} 
				
					
				
				txbAssCpfCnpj.setText(String.valueOf(bc.getPessoaSelecionada().getCpf()));
			}catch (ParseException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			}
		});
		btnBuscaUserQrP.setEnabled(false);
		
		chckbxCpf = new JCheckBox("CPF");
		chckbxCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxCpf.isSelected()){
					chckbxCnpj.setSelected(false);
					//txbCpfCnpj.setText("");
				}
				try {
					txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chckbxCpf.setSelected(true);
			}
		});
		chckbxCpf.setEnabled(false);
		
		chckbxCnpj = new JCheckBox("CNPJ");
		chckbxCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxCnpj.isSelected()){
					chckbxCpf.setSelected(false);
					//txbCpfCnpj.setText("");
				}
				try {
					txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chckbxCnpj.setSelected(true);
			}
		});
		chckbxCnpj.setEnabled(false);
		
		txbCpfCnpj = new JFormattedTextField();
		txbCpfCnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbCpfCnpj.getText().replaceAll("[.-/]", "").length() <= 11){
					
					lblErroForm.setText("Voc� precisa informar um CPF ou CNPJ");
				}else{
					lblErroForm.setText("");
				}
			}
			
		});
		txbCpfCnpj.setEnabled(false);
		
		chckbxCpf_1 = new JCheckBox("CPF");
		chckbxCpf_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxCpf_1.isSelected()){
					chckbxCnpj_1.setSelected(false);
				}
				try {
					txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chckbxCpf_1.setSelected(true);
				chckbxCnpj_1.setEnabled(true);
				}
		});
		chckbxCpf_1.setEnabled(false);
		
		chckbxCnpj_1 = new JCheckBox("CNPJ");
		chckbxCnpj_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxCnpj_1.isSelected()){
					chckbxCpf_1.setSelected(false);
				}
				try {
					txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chckbxCnpj_1.setSelected(true);
				
				chckbxCnpj_1.setEnabled(true);
				}
		});
		chckbxCnpj_1.setEnabled(false);
		
		txbAssCpfCnpj = new JFormattedTextField();
		txbAssCpfCnpj.setEnabled(false);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitaCampos(false);
				limpaCampos(true);
				txbCpfCnpj.setEnabled(false);
				txbAssCpfCnpj.setEnabled(false);
				btnBuscaUser.setEnabled(false);
				btnBuscaUserQrP.setEnabled(false);
				habilitaMenu("habilita");
				botaoAcao = false;
			}
		});
		btnCancelar.setEnabled(false);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!lblErroForm.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Ops! voc� esqueceu de informar algo importante!");
				}else{
					Recibo rb = new Recibo();
					ReciboDao rbD = new ReciboDao();
					rb.setCpf(txbCpfCnpj.getText().replaceAll("[.,-/]", ""));
					rb.setCpfQuemRecebe(txbAssCpfCnpj.getText());
					rb.setDataEmissao(txbDataEmissao.getText());
					rb.setDataEscrita(txbDataExtenso.getText());
					rb.setLocal(txbLocal.getText());
					rb.setObs(txbObs.getText());
					rb.setqFp(txbQeP.getText());
					rb.setQuemRecebe(txbQrP.getText());
					rb.setReferente(txbReferente.getText());
					rb.setTipoRecibo(cbTipoRecibo.getSelectedItem().toString());
					rb.setValor(Double.parseDouble(valorTemp));
					rb.setValorEscrito(txbValorExtenso.getText());
					switch(salvarAcao){
					case 1:
						idtemp = geraID();
						rb.setId(idtemp);
						
						try {
							JOptionPane.showMessageDialog(null, rbD.cadastraRecibo(rb));
							GeraHtml.printRecibo(rb, cfgSkin, cfgImg);
							ImprimirArquivo.imprimir("recibos/" + randID + ".html");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case 2:
						rb.setId(idtemp);
						JOptionPane.showMessageDialog(null, rbD.alteraRecibo(rb));
						GeraHtml.printRecibo(rb, cfgSkin, cfgImg);
						ImprimirArquivo.imprimir("recibos/" + idtemp + ".html");
						break;
					}
					
					btnImprimir.setEnabled(true);
					limpaCampos(true);
					habilitaCampos(false);
					habilitaMenu("habilita");
					botaoAcao = false;
				}
				}
				
		});
		btnSalvar.setEnabled(false);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(idtemp.equals("")){
					JOptionPane.showMessageDialog(null, "Selecione um recibo para imprimir");
				}else{
					ImprimirArquivo.imprimir("recibos/" + idtemp + ".html");
				}
				
			}
		});
		btnImprimir.setEnabled(false);
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnImprimir)
							.addGap(18)
							.addComponent(btnSalvar)
							.addGap(18)
							.addComponent(btnCancelar))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txbQrP)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscaUserQrP)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(chckbxCpf_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxCnpj_1)
									.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE))
								.addComponent(txbAssCpfCnpj, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblImportanciaDe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckValorEditar))
						.addComponent(txbObs, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txbDataEmissao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(lblDataDeEmisso, Alignment.LEADING))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblDataPorExtenso)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckDataEditar))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(6)
									.addComponent(txbDataExtenso, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))))
						.addComponent(txbReferente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addComponent(txbValorExtenso, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txbCpfCnpj, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(chckbxCpf)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxCnpj)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblLocal)
									.addPreferredGap(ComponentPlacement.RELATED, 268, Short.MAX_VALUE))
								.addComponent(txbLocal, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(cbTipoRecibo, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipoDeRecibo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txbValor, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValor))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblRecebiDequem)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscaUser)
									.addGap(17))
								.addComponent(txbQeP, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)))
						.addComponent(lblReferente)
						.addComponent(lblObservaes))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeRecibo)
						.addComponent(lblValor)
						.addComponent(lblRecebiDequem)
						.addComponent(btnBuscaUser))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbTipoRecibo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbQeP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCpf)
						.addComponent(chckbxCnpj)
						.addComponent(lblLocal))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbCpfCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbLocal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImportanciaDe)
						.addComponent(chckValorEditar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txbValorExtenso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblReferente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txbReferente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblObservaes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txbObs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeEmisso)
						.addComponent(lblDataPorExtenso)
						.addComponent(chckDataEditar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbDataEmissao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbDataExtenso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnBuscaUserQrP)
						.addComponent(chckbxCpf_1)
						.addComponent(chckbxCnpj_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbQrP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbAssCpfCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnImprimir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(24))
		);
		
		lblErroForm = new JLabel("");
		panel_3.add(lblErroForm);
		lblErroForm.setForeground(Color.RED);
		panel_2.setLayout(gl_panel_2);
		lblNovoRecibo = new JLabel("");
		lblNovoRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNovoRecibo.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/novoOver.png")));
				lblNovoRecibo.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Adiciona novo Recibo");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNovoRecibo.setIcon(new ImageIcon(App.class.getResource("/resources/novo.png")));
				lblNovoRecibo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(botaoAcao){
					return;
				}else{
					lblErroForm.setText("Informe todos os CAMPOS !");
					salvarAcao = 1;
					botaoAcao = true;
					btnImprimir.setEnabled(false);
					habilitaMenu("Novo");
					limpaCampos(false);
					habilitaCampos(true);
					txbDataEmissao.setText(LeitorData.getDataNumero());
					txbDataExtenso.setText(LeitorData.getData());
				}
				
			}
		});
		lblNovoRecibo.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/novo.png")));
		
		lblEditarRecibo = new JLabel("");
		lblEditarRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEditarRecibo.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/editarOver.png")));
				lblEditarRecibo.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Editar Recibo Atual");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEditarRecibo.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/editar.png")));
				lblEditarRecibo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(botaoAcao){
					return;
				}
				
				if(idtemp.equals("")){
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um RECIBO para editar.");
				}else{
					habilitaCampos(true);
					botaoAcao = true;
					habilitaMenu("Editar");
					salvarAcao = 2;
				}
			}
		});
		lblEditarRecibo.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/editar.png")));
		
		lblDelete = new JLabel("");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDelete.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/deletarOver.png")));
				lblDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Excluir Recibo Atual");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblDelete.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/deletar.png")));
				lblDelete.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(botaoAcao){
					return;
				}
				if(idtemp.equals("")){
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um RECIBO para excluir.");
				}else{
					botaoAcao = true;
					lblNovoRecibo.setEnabled(false);
					lblEditarRecibo.setEnabled(false);
					lblProcura.setEnabled(false);
						if(JOptionPane.showConfirmDialog(null, "Confirma a remo��o ? ") == JOptionPane.YES_OPTION){
							ReciboDao rbd = new ReciboDao();
							Recibo rb = new Recibo();
							rb.setId(idtemp);
							JOptionPane.showMessageDialog(null, rbd.excluiRecibo(rb));
							habilitaMenu("habilita");
							botaoAcao = false;
							limpaCampos(true);
							habilitaCampos(false);
						}else{
							habilitaMenu("habilita");
							botaoAcao = false;
						}
							
				}
			}
		});
		lblDelete.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/deletar.png")));
		
		lblProcura = new JLabel("");
		lblProcura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblProcura.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/procurarOver.png")));
				lblProcura.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Procura Recibo");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblProcura.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/procurar.png")));
				lblProcura.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(botaoAcao){
					return;
				}else{
					FrmBuscaRecibo brb = new FrmBuscaRecibo();
					brb.setVisible(true);
					cbTipoRecibo.setSelectedItem(String.valueOf(brb.getRecibo().getTipoRecibo()));
					
					if(brb.getRecibo().getCpfQuemRecebe().replaceAll("[-/ ]", "").length() > 11){
						chckbxCnpj_1.setSelected(true);
						chckbxCpf_1.setSelected(false);
						try {
							txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						chckbxCpf_1.setSelected(true);
						chckbxCnpj_1.setSelected(false);
						try {
							txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					txbAssCpfCnpj.setText(String.valueOf(brb.getRecibo().getCpfQuemRecebe()));
					
					if(brb.getRecibo().getCpf().replaceAll("[-/ ]", "").length() > 11){
						chckbxCnpj.setSelected(true);
						chckbxCpf.setSelected(false);
						try {
							txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						chckbxCpf.setSelected(true);
						chckbxCnpj.setSelected(false);
						try {
							txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					txbCpfCnpj.setText(String.valueOf(brb.getRecibo().getCpf()));
					txbDataEmissao.setText(String.valueOf(brb.getRecibo().getDataEmissao()));
					txbDataExtenso.setText(String.valueOf(brb.getRecibo().getDataEscrita()));
					txbLocal.setText(String.valueOf(brb.getRecibo().getLocal()));
					txbObs.setText(String.valueOf(brb.getRecibo().getObs()));
					txbQeP.setText(String.valueOf(brb.getRecibo().getqFp()));
					txbQrP.setText(String.valueOf(brb.getRecibo().getQuemRecebe()));
					txbReferente.setText(String.valueOf(brb.getRecibo().getReferente()));
					valorTemp = String.valueOf(brb.getRecibo().getValor());
					MoedaFormat m = new MoedaFormat();
					txbValor.setText(m.mascaraDinheiro(Double.parseDouble(valorTemp), MoedaFormat.DINHEIRO_REAL));
					txbValorExtenso.setText(String.valueOf(brb.getRecibo().getValorEscrito()));
					btnBuscaUser.setEnabled(false);
					btnImprimir.setEnabled(true);
					idtemp = String.valueOf(brb.getRecibo().getId());
				}
				
			}
		});
		lblProcura.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/procurar.png")));
		
		lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Chama metodo que fecha o form
				fechaForm();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFechar.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/closeOver.png")));
				lblFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Fechar Janela");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFechar.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/close.png")));
				lblFechar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
		});
		lblFechar.setIcon(new ImageIcon(FrmRecibo.class.getResource("/resources/close.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEditarRecibo)
						.addComponent(lblFechar)
						.addComponent(lblProcura)
						.addComponent(lblDelete)
						.addComponent(lblNovoRecibo))
					.addGap(21))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNovoRecibo)
					.addGap(13)
					.addComponent(lblEditarRecibo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDelete)
					.addGap(18)
					.addComponent(lblProcura)
					.addGap(18)
					.addComponent(lblFechar)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		lblStatus = new JLabel("");
		panel.add(lblStatus);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void limpaCampos(boolean valor){
		idtemp = "";
		cbTipoRecibo.setSelectedItem(0);
		txbQeP.setText("");
		txbQrP.setText("");
		txbAssCpfCnpj.setText("");
		txbCpfCnpj.setText("");
		txbDataEmissao.setText("");
		txbDataExtenso.setText("");
		txbLocal.setText("");
		txbObs.setText("");
		txbReferente.setText("");
		txbValor.setText("");
		txbValorExtenso.setText("");
	}
	
	public void habilitaCampos(boolean valor){
		cbTipoRecibo.setEnabled(valor);
		txbCpfCnpj.setEnabled(valor);
		txbAssCpfCnpj.setEnabled(valor);
		txbDataEmissao.setEnabled(valor);
		txbLocal.setEnabled(valor);
		txbObs.setEnabled(valor);
		txbReferente.setEnabled(valor);
		txbValor.setEnabled(valor);
		txbQeP.setEnabled(valor);
		txbQrP.setEnabled(valor);
		chckbxCnpj.setEnabled(valor);
		chckbxCnpj_1.setEnabled(valor);
		chckbxCpf.setEnabled(valor);
		chckbxCpf_1.setEnabled(valor);
		btnCancelar.setEnabled(valor);
		btnSalvar.setEnabled(valor);
		btnImprimir.setEnabled(valor);
		chckValorEditar.setEnabled(valor);
		chckDataEditar.setEnabled(valor);
	}
	
	public void tipoRecibo(String valor){
		ReciboDao rbd = new ReciboDao();
		
		
		//Adiciona no campo CPF/CNPJ e Quem recebe
		if(valor.equals("Credor")){
			//Seta busca por usu�rio verdadeira
			txbQeP.setText("");
			txbAssCpfCnpj.setText("");
			txbQrP.setText(rbd.getConfig().get(0));
			if(rbd.getConfig().get(1).length() > 12){
				chckbxCnpj_1.setSelected(true);
				chckbxCpf_1.setSelected(false);
				try {
					txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				chckbxCpf_1.setSelected(true);
				chckbxCnpj_1.setSelected(false);
				try {
					txbAssCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			txbAssCpfCnpj.setText(rbd.getConfig().get(1));
			txbCpfCnpj.setText("");
			btnBuscaUser.setEnabled(true);
			btnBuscaUserQrP.setEnabled(false);
			
		}else{
			//Adiciona informa��es da empresa das configura��es
			txbQeP.setText(rbd.getConfig().get(0));
			if(rbd.getConfig().get(1).length() > 11){
				chckbxCnpj.setSelected(true);
				chckbxCpf.setSelected(false);
				try {
					txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				chckbxCpf.setSelected(true);
				chckbxCnpj.setSelected(false);
				try {
					txbCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			txbCpfCnpj.setText(rbd.getConfig().get(1));
			txbQrP.setText("");
			txbAssCpfCnpj.setText("");
			btnBuscaUser.setEnabled(false);
			btnBuscaUserQrP.setEnabled(true);
		}
	}
	
	public void getConfigRb(){
		ReciboDao rbd = new ReciboDao();
		cfgEmitente = rbd.getConfig().get(0);
		cfgCpf = rbd.getConfig().get(1);
		cfgCidade = rbd.getConfig().get(2);
		cfgImg = rbd.getConfig().get(3);
		cfgSkin = rbd.getConfig().get(4);
	}
	
	public void fechaForm(){
		this.setVisible(false);
	}	
	
	public String geraID(){
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWYX123456789".toCharArray();
		StringBuilder idGen = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    idGen.append(c);
		}
		randID = idGen.toString();
		return randID;
	}
	
	public void habilitaMenu(String valor){
		switch(valor){
			case "Novo":
				lblEditarRecibo.setEnabled(false);
				lblDelete.setEnabled(false);
				lblProcura.setEnabled(false);
				break;
			case "Editar":
				lblNovoRecibo.setEnabled(false);
				lblDelete.setEnabled(false);
				lblProcura.setEnabled(false);
				break;
			case "Deletar":
				lblNovoRecibo.setEnabled(false);
				lblEditarRecibo.setEnabled(false);
				lblProcura.setEnabled(false);
				break;
			case "Procurar":
				lblNovoRecibo.setEnabled(false);
				lblEditarRecibo.setEnabled(false);
				lblDelete.setEnabled(false);
				break;
			case "habilita":
				lblNovoRecibo.setEnabled(true);
				lblEditarRecibo.setEnabled(true);
				lblDelete.setEnabled(true);
				lblProcura.setEnabled(true);
				break;
			case "fecha":
				lblNovoRecibo.setEnabled(false);
				lblEditarRecibo.setEnabled(false);
				lblDelete.setEnabled(false);
				lblProcura.setEnabled(false);
		}
	}
}
