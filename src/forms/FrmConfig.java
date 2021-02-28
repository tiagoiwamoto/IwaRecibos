package forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import utils.CopiaArquivos;
import utils.DbConnect;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Toolkit;

public class FrmConfig extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txbNome;
	private JFormattedTextField txbCpf;
	private JTextField txbCidade;
	private String caminhoImg;
	private JLabel lblImagem;
	private JList lstSkins;
	private String fotoNome;
	private JCheckBox chckbxCpf;
	private JCheckBox chckbxCnpj;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			FrmConfig dialog = new FrmConfig();
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
	@SuppressWarnings("unchecked")
	public FrmConfig() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConfig.class.getResource("/resources/appIcon.png")));
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				getConfig();
				ImageIcon icon = new ImageIcon(caminhoImg);
				lblImagem.setIcon(icon);
				fotoNome = "";
			}
		});

		setResizable(false);
		setTitle("IwaRecibos - Configura\u00E7\u00F5es");
		setBounds(100, 100, 517, 432);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNome = new JLabel("Emitente");
		
		txbNome = new JTextField();
		txbNome.setColumns(10);
		
		chckbxCpf = new JCheckBox("CPF");
		chckbxCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxCnpj.isSelected()){
					
					chckbxCnpj.setSelected(false);
					try {
						txbCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("###-###-###-##")));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				chckbxCpf.setSelected(true);
			}
		});
		
		chckbxCnpj = new JCheckBox("CNPJ");
		chckbxCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxCpf.isSelected()){
					chckbxCpf.setSelected(false);
					
					try {
						txbCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				chckbxCnpj.setSelected(true);
			}
		});
		
		txbCpf = new JFormattedTextField();
		txbCpf.setColumns(10);
		
		JLabel lblSuaCidade = new JLabel("Sua Cidade");
		
		txbCidade = new JTextField();
		txbCidade.setColumns(10);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblImagem.setIcon(null);
				caminhoImg = "";
				fotoNome = "";
				lblImagem.setText("Selecione uma imagem");
			}
		});
		
		JButton btnAdicionarLogomarca = new JButton("Adicionar");
		btnAdicionarLogomarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter fl = new FileNameExtensionFilter("Arquivos de Imagem", "jpg", "png");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(fl);	
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		          caminhoImg = selectedFile.getAbsolutePath().toString();
		          fotoNome = selectedFile.getName().toString();
		          ImageIcon icon= new ImageIcon(caminhoImg);
		          lblImagem.setIcon(icon);
		          lblImagem.setText("");
		          //System.out.println(selectedFile.getName());
		        }
			}
		});
		
		JButton btnSalvar = new JButton("Salvar configura\u00E7\u00F5es");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSalvar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(fotoNome.equals("")){
					fotoNome = "blank.png";
				}else{
					CopiaArquivos.copiaArquivo(caminhoImg, "img/" + fotoNome);	
				}
				List<String> lista = new ArrayList<String>();
				lista.add(0, txbNome.getText());
				lista.add(1, txbCpf.getText());
				lista.add(2, txbCidade.getText());
				lista.add(3, "img/" + fotoNome);
				lista.add(4, lstSkins.getSelectedValue().toString());
				setConfig(lista);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblLayoutDoRecibo = new JLabel("Layout do Recibo");
		
		JList list = new JList();
		
		JPanel panel = new JPanel();
		
		JLabel lblLogomarca = new JLabel("Logomarca");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogomarca)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addComponent(chckbxCpf)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxCnpj))
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addComponent(btnAdicionarLogomarca)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemover)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnSalvar))
								.addComponent(lblSuaCidade, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txbCidade, Alignment.LEADING)
									.addComponent(txbCpf, Alignment.LEADING)
									.addComponent(txbNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
								.addComponent(lblNome, Alignment.LEADING))
							.addGap(6)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblLayoutDoRecibo)
							.addContainerGap(94, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblLayoutDoRecibo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txbNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxCpf)
								.addComponent(chckbxCnpj))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txbCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSuaCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txbCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLogomarca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdicionarLogomarca)
								.addComponent(btnRemover)
								.addComponent(btnSalvar)))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		lstSkins = new JList();
		lstSkins.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstSkins.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					//JOptionPane.showMessageDialog(null, lstSkins.getSelectedValue().toString());
					FrmImgView frmI = new FrmImgView();
					frmI.setImg(lstSkins.getSelectedValue() + ".png");
					frmI.setVisible(true);
					
				}
			}
		});
		lstSkins.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		carregaSkins();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lstSkins, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lstSkins, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		lblImagem = new JLabel("");
		ImageIcon icon = new ImageIcon(caminhoImg);
		lblImagem.setIcon(icon);
		scrollPane.setViewportView(lblImagem);
		lblImagem.setForeground(Color.DARK_GRAY);
		lblImagem.setBackground(Color.DARK_GRAY);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void getConfig(){
		Connection conn = null;
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT * FROM Config");
			txbNome.setText(meuRs.getString("emitente"));
			
			if(String.valueOf(meuRs.getString("cpf")).replaceAll("[-/ ]", "").length() > 11){
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
			
			txbCpf.setText(String.valueOf(meuRs.getString("cpf")));
			txbCidade.setText(meuRs.getString("cidade"));
			caminhoImg = meuRs.getString("img");
			lstSkins.setSelectedValue(meuRs.getString("skin"), false);
			
			if(meuRs.getString("img").equals("")){
				lblImagem.setText("Selecione uma imagem");
			}
			
		}catch(Exception e){
			
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String setConfig(List<String> lista){
		Connection conn = null;
		try{
			conn = DbConnect.getConnection();
			PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "UPDATE Config SET emitente = ?, cpf = ?, cidade = ?, img = ?, skin = ?");
			meuSt.setString(1, lista.get(0));
			meuSt.setString(2, lista.get(1).replaceAll("[-./]", ""));
			meuSt.setString(3, lista.get(2));
			meuSt.setString(4, lista.get(3));
			meuSt.setString(5, lista.get(4));
			if(meuSt.executeUpdate() >= 1){
				JOptionPane.showMessageDialog(null, "Configurações atualizadas!");
			}else{
				JOptionPane.showMessageDialog(null, "Erro ao atualizar configurações :(");
			}
		}catch(Exception e){
			
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public void carregaSkins(){
		DefaultListModel model1 = new DefaultListModel();
	    File o = new File("modelos/");

	    File[] yourFileList = o.listFiles();
	    for(File f : yourFileList) {
	    	
	    	if(f.getName().endsWith(".png")){
	    		continue;
	    	}else{
	    		model1.addElement(f.getName().replaceAll(".html", ""));	
	    	}
	    	
	    	
	    	
	        
	    }
	    lstSkins.setModel(model1);
	    lstSkins.setSelectedIndex(0);
	}
}
