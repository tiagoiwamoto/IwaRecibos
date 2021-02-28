package forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Clientes;
import reciboDao.ClienteDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

public class FrmCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblAdicionar;
	private JLabel lblEditar;
	private JLabel lblRemover;
	private JLabel lblBusca;
	private JLabel lblFechar;
	private JLabel lblStatus;
	private JTextField txbNome;
	private JTextField txbDataNasc;
	private JTextField txbRG;
	private JFormattedTextField txbCpf;
	private JTextField txbHabilitacao;
	private JTextField txbEndereco;
	private JTextField txbCidade;
	private JTextField txbEstado;
	private JTextField txbCEP;
	private JTextField txbPais;
	private JTextField txbObs;
	private JCheckBox chckbxCpf;
	private JCheckBox chckbxCnpj;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private boolean menuAcao;
	private String idTempo = "";
	private String randID;
	private int salvarAcao;
	private JPanel panel_3;
	private JLabel lblFormMsg;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			FrmCliente dialog = new FrmCliente();
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
	public FrmCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCliente.class.getResource("/resources/appIcon.png")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("IwaRecibos - Gerenciador de Clientes");
		setModal(true);
		setBounds(100, 100, 730, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(40)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblNome = new JLabel("Nome");
		
		txbNome = new JTextField();
		txbNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbNome.getText().length() < 5){
					
					lblFormMsg.setText("Por favor informe o nome completo!");
				}else{
					lblFormMsg.setText("");
				}
			}
		});
		txbNome.setEnabled(false);
		txbNome.setColumns(10);
		
		MaskFormatter dataMask = null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txbDataNasc = new JFormattedTextField(dataMask);
		txbDataNasc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbDataNasc.getText().length() < 8){
					
					lblFormMsg.setText("Informe a data de nascimento!");
				}else{
					lblFormMsg.setText("");
				}
			}
		});
		txbDataNasc.setEnabled(false);
		txbDataNasc.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		
		JLabel lblRg = new JLabel("RG");
		
		MaskFormatter rgMask = null;
		try {
			rgMask = new MaskFormatter("#.###.###-#");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txbRG = new JFormattedTextField(rgMask);
		txbRG.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbRG.getText().length() < 8){
					
					lblFormMsg.setText("Informe o numero do RG!");
				}else{
					lblFormMsg.setText("");
				}
			}
		});
		txbRG.setEnabled(false);
		txbRG.setColumns(10);
		
		txbCpf = new JFormattedTextField();
		txbCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbCpf.getText().length() < 11){
					txbCpf.requestFocus();
					lblFormMsg.setText("Informe o numero do CPF ou CNPJ!");
				}else{
					lblFormMsg.setText("");
				}
			}
		});
		txbCpf.setEnabled(false);
		txbCpf.setColumns(10);
		
		chckbxCpf = new JCheckBox("CPF");
		chckbxCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxCnpj.isSelected()){
					chckbxCnpj.setSelected(false);
				}
				chckbxCpf.setSelected(true);
				try {
					txbCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		chckbxCpf.setEnabled(false);
		
		chckbxCnpj = new JCheckBox("CNPJ");
		chckbxCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxCpf.isSelected()){
					chckbxCpf.setSelected(false);
				}
				chckbxCnpj.setSelected(true);
				try {
					txbCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		chckbxCnpj.setEnabled(false);
		
		txbHabilitacao = new JTextField();
		txbHabilitacao.setEnabled(false);
		txbHabilitacao.setColumns(10);
		
		JLabel lblHabilitao = new JLabel("Habilita\u00E7\u00E3o");
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		
		txbEndereco = new JTextField();
		txbEndereco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txbEndereco.getText().length() < 5){
					
					lblFormMsg.setText("Informe o endere�o completo!");
				}else{
					lblFormMsg.setText("");
				}
			}
		});
		txbEndereco.setEnabled(false);
		txbEndereco.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		
		txbCidade = new JTextField();
		txbCidade.setEnabled(false);
		txbCidade.setColumns(10);
		
		txbEstado = new JTextField();
		txbEstado.setEnabled(false);
		txbEstado.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		MaskFormatter cepMask = null;
		try {
			cepMask = new MaskFormatter("##.###.###");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txbCEP = new JFormattedTextField(cepMask);
		txbCEP.setEnabled(false);
		txbCEP.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		
		txbPais = new JTextField();
		txbPais.setEnabled(false);
		txbPais.setColumns(10);
		
		JLabel lblPas = new JLabel("Pa\u00EDs");
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o");
		
		txbObs = new JTextField();
		txbObs.setEnabled(false);
		txbObs.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuAcao = false;
				limpaCampos();
				habilitaCampos(false);
				habilitaMenu("habilita");
				habilitaBotoes(false);
			}
		});
		btnCancelar.setEnabled(false);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verificaCamposVazios().equals("")){
					ClienteDao rd = new ClienteDao();
					Clientes p = new Clientes();
					p.setNome(txbNome.getText());
					p.setCpf(txbCpf.getText().replaceAll("[-/.]", ""));
					p.setRg(txbRG.getText().replaceAll("[.-]", ""));
					p.setDtNasc(txbDataNasc.getText().replaceAll("[.-/]", ""));
					p.setHabilitacao(txbHabilitacao.getText());
					p.setEndereco(txbEndereco.getText());
					p.setCidade(txbCidade.getText());
					p.setEstado(txbEstado.getText());
					p.setCep(txbCEP.getText().replaceAll("[.-/]", ""));
					p.setPais(txbPais.getText());
					p.setObs(txbObs.getText());
					
					
					switch(salvarAcao){
					case 1:
						p.setId(geraID());
						JOptionPane.showMessageDialog(null, rd.cadastraClientes(p));
						break;
					case 2:
						p.setId(idTempo);
						JOptionPane.showMessageDialog(null, rd.alteraClientes(p));
						break;
					}
					
					menuAcao = false;
					limpaCampos();
					habilitaCampos(false);
					habilitaMenu("habilita");
					habilitaBotoes(false);
				}else{
					JOptionPane.showMessageDialog(null, verificaCamposVazios());
				}
			}
		});
		btnSalvar.setEnabled(false);
		
		panel_3 = new JPanel();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txbNome, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataNascimento)
								.addComponent(txbDataNasc, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
						.addComponent(txbEndereco, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txbRG, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRg))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txbCpf, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(chckbxCpf)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxCnpj)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHabilitao)
								.addComponent(txbHabilitacao, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
						.addComponent(txbObs, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCidade)
								.addComponent(txbCidade, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEstado)
								.addComponent(txbEstado, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txbCEP, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCep))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPas)
								.addComponent(txbPais, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalvar)
							.addGap(18)
							.addComponent(btnCancelar))
						.addComponent(lblObservao)
						.addComponent(lblEndereo))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblDataNascimento))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbDataNasc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRg)
						.addComponent(chckbxCpf)
						.addComponent(chckbxCnpj)
						.addComponent(lblHabilitao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbRG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbHabilitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEndereo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txbEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(lblEstado)
						.addComponent(lblCep)
						.addComponent(lblPas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txbCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbPais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblObservao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txbObs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		lblFormMsg = new JLabel("");
		lblFormMsg.setForeground(Color.RED);
		panel_3.add(lblFormMsg);
		panel_2.setLayout(gl_panel_2);
		
		lblStatus = new JLabel("");
		panel_1.add(lblStatus);
		
		lblAdicionar = new JLabel("");
		lblAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblAdicionar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/novoOver.png")));
				lblStatus.setText("Adiciona um novo Cliente");
				lblAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAdicionar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/novo.png")));
				lblStatus.setText("");
				lblAdicionar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(menuAcao){
					return;
				}else{
					salvarAcao = 1;
					menuAcao = true;
					lblFormMsg.setText("Informe todos os campos");
					habilitaMenu("Novo");
					limpaCampos();
					habilitaCampos(true);
					habilitaBotoes(true);
					txbPais.setText("Brasil");
				}
			}
		});
		lblAdicionar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/novo.png")));
		
		lblEditar = new JLabel("");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblEditar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/editarOver.png")));
				lblStatus.setText("Edita Cliente selecionado!");
				lblEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEditar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/editar.png")));
				lblStatus.setText("");
				lblEditar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(menuAcao){
					return;
				}
				
				if(idTempo.equals("")){
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um CLIENTE para editar.");
				}else{
					menuAcao = true;
					salvarAcao = 2;
					habilitaMenu("Editar");
					habilitaCampos(true);
					habilitaBotoes(true);
				}
			}
		});
		lblEditar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/editar.png")));
		
		lblRemover = new JLabel("");
		lblRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblRemover.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/deletarOver.png")));
				lblStatus.setText("Remover Cliente Selecionado");
				lblRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRemover.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/deletar.png")));
				lblStatus.setText("");
				lblRemover.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(menuAcao){
					return;
				}
				
				if(idTempo.equals("")){
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um CLIENTE para excluir.");
				}else{
					menuAcao = true;
					habilitaMenu("Excluir");
					Clientes cl = new Clientes();
					ClienteDao cld = new ClienteDao();
					cl.setId(idTempo);
					if(JOptionPane.showConfirmDialog(null, "Confirma a remo��o ? " + idTempo) == JOptionPane.YES_OPTION){
						JOptionPane.showMessageDialog(null, cld.excluiClientes(cl));
						limpaCampos();
						habilitaMenu("habilita");
						habilitaCampos(false);
						habilitaBotoes(false);
						menuAcao = false;
					}else{
						habilitaMenu("habilita");
						habilitaCampos(false);
						habilitaBotoes(false);
						menuAcao = false;
					}
				}
			}
		});
		lblRemover.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/deletar.png")));
		
		lblBusca = new JLabel("");
		lblBusca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblBusca.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/procurarOver.png")));
				lblStatus.setText("Procurar Cliente");
				lblBusca.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBusca.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/procurar.png")));
				lblStatus.setText("");
				lblBusca.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(menuAcao){
					return;
				}
				FrmBuscaCliente frmD = new FrmBuscaCliente();
				frmD.setVisible(true);
				txbCEP.setText(String.valueOf(frmD.getPessoaSelecionada().getCep()));
				txbCidade.setText(String.valueOf(frmD.getPessoaSelecionada().getCidade()));
					if(String.valueOf(frmD.getPessoaSelecionada().getCpf().replaceAll("[-/ ]", "")).length() > 11){
						chckbxCnpj.setSelected(true);
						chckbxCpf.setSelected(false);
						try {
							txbCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						chckbxCpf.setSelected(true);
						chckbxCnpj.setSelected(false);
						try {
							txbCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				txbCpf.setText(String.valueOf(frmD.getPessoaSelecionada().getCpf()));
				txbDataNasc.setText(String.valueOf(frmD.getPessoaSelecionada().getDtNasc()));
				txbEndereco.setText(String.valueOf(frmD.getPessoaSelecionada().getEndereco()));
				txbEstado.setText(String.valueOf(frmD.getPessoaSelecionada().getEstado()));
				txbHabilitacao.setText(String.valueOf(frmD.getPessoaSelecionada().getHabilitacao()));
				txbNome.setText(String.valueOf(frmD.getPessoaSelecionada().getNome()));
				txbObs.setText(String.valueOf(frmD.getPessoaSelecionada().getObs()));
				txbPais.setText(String.valueOf(frmD.getPessoaSelecionada().getPais()));
				txbRG.setText(String.valueOf(frmD.getPessoaSelecionada().getRg()));
				
				idTempo = String.valueOf(frmD.getPessoaSelecionada().getId());
				
				habilitaMenu("habilita");
			}
			
		});
		lblBusca.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/procurar.png")));
		
		lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblFechar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/closeOver.png")));
				lblStatus.setText("Fechar Janela");
				lblFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFechar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/close.png")));
				lblStatus.setText("");
				lblFechar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		lblFechar.setIcon(new ImageIcon(FrmCliente.class.getResource("/resources/close.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdicionar)
						.addComponent(lblEditar)
						.addComponent(lblRemover)
						.addComponent(lblFechar)
						.addComponent(lblBusca))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdicionar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEditar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRemover)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(lblBusca)
					.addGap(18)
					.addComponent(lblFechar))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
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
	
	public void habilitaCampos(boolean valor){
		txbCEP.setEnabled(valor);
		txbCidade.setEnabled(valor);
		txbCpf.setEnabled(valor);
		txbDataNasc.setEnabled(valor);
		txbEndereco.setEnabled(valor);
		txbEstado.setEnabled(valor);
		txbHabilitacao.setEnabled(valor);
		txbNome.setEnabled(valor);
		txbObs.setEnabled(valor);
		txbPais.setEnabled(valor);
		txbRG.setEnabled(valor);
		chckbxCnpj.setEnabled(valor);
		chckbxCpf.setEnabled(valor);
	}
	
	public void limpaCampos(){
		txbCEP.setText("");
		txbCidade.setText("");
		txbCpf.setText("");
		txbDataNasc.setText("");
		txbEndereco.setText("");
		txbEstado.setText("");
		txbHabilitacao.setText("");
		txbNome.setText("");
		txbObs.setText("");
		txbPais.setText("");
		txbRG.setText("");
		chckbxCnpj.setSelected(false);
		chckbxCpf.setSelected(false);
		idTempo = "";
	}
	
	public void habilitaBotoes(boolean valor){
		btnCancelar.setEnabled(valor);
		btnSalvar.setEnabled(valor);
	}
	
	public void menuAcao(boolean valor){
		menuAcao = valor;
	}
	
	public void habilitaMenu(String valor){
		switch(valor){
			case "Novo":
				lblEditar.setEnabled(false);
				lblRemover.setEnabled(false);
				lblBusca.setEnabled(false);
				break;
			case "Editar":
				lblAdicionar.setEnabled(false);
				lblRemover.setEnabled(false);
				lblBusca.setEnabled(false);
				break;
			case "Deletar":
				lblAdicionar.setEnabled(false);
				lblEditar.setEnabled(false);
				lblBusca.setEnabled(false);
				break;
			case "Procurar":
				lblAdicionar.setEnabled(false);
				lblEditar.setEnabled(false);
				lblRemover.setEnabled(false);
				break;
			case "habilita":
				lblAdicionar.setEnabled(true);
				lblEditar.setEnabled(true);
				lblRemover.setEnabled(true);
				lblBusca.setEnabled(true);
				break;
			case "fecha":
				lblAdicionar.setEnabled(false);
				lblEditar.setEnabled(false);
				lblRemover.setEnabled(false);
				lblBusca.setEnabled(false);
		}
	}
	
	public String verificaCamposVazios(){
		String msg = "";
		if(txbCEP.getText().replaceAll("[. ]", "").equals("")){ msg = "Voc� esqueceu de informar o CEP!"; }
		if(txbCidade.getText().equals("")){ msg = "Voc� esqueceu de informar a CIDADE!"; }
		if(txbCpf.getText().replaceAll("[-/ ]", "").equals("")){ msg = "Voc� esqueceu de informar o CPF!"; }
		if(txbDataNasc.getText().replaceAll("[/ ]", "").equals("")){ msg = "Voc� esqueceu de informar a DATA DE NASCIMENTO!"; }
		if(txbEndereco.getText().equals("")){ msg = "Voc� esqueceu de informar o ENDERE�O!"; }
		if(txbEstado.getText().equals("")){ msg = "Voc� esqueceu de informar o ESTADO!"; }
		if(txbHabilitacao.getText().equals("")){ msg = "Voc� esqueceu de informar a HABILITA��O!"; }
		if(txbNome.getText().equals("")){ msg = "Voc� esqueceu de informar o NOME!"; }
		if(txbPais.getText().equals("")){ msg = "Voc� esqueceu de informar o PA�S!"; }
		if(txbRG.getText().replaceAll("[-. ]", "").equals("")){ msg = "Voc� esqueceu de informar o RG!"; }
		return msg;
	}
}
