package forms;

import reciboDao.ClienteDao;
import reciboDao.ReciboDao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.text.ParseException;

public class App extends JFrame {

	private JPanel contentPane;
	private JLabel lblStatus;
	private JLabel lblTotalcadastros;
	private JLabel lblTotalrecibos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setTitle("IwaRecibos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/resources/appIcon.png")));
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblTotalcadastros.setText(String.valueOf(ClienteDao.totalClientes()));
				lblTotalrecibos.setText(String.valueOf(ReciboDao.totalRecibo()));
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 242);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblGerenciaRecibo = new JLabel("");
		lblGerenciaRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblGerenciaRecibo.setIcon(new ImageIcon(App.class.getResource("/resources/reciboManagerNormalOver.png")));
				lblGerenciaRecibo.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Gerenciador de Recibos");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGerenciaRecibo.setIcon(new ImageIcon(App.class.getResource("/resources/reciboManagerNormal.png")));
				lblGerenciaRecibo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmRecibo rb = new FrmRecibo();
				rb.setVisible(true);
			}
		});
		lblGerenciaRecibo.setIcon(new ImageIcon(App.class.getResource("/resources/reciboManagerNormal.png")));
		
		JLabel lblGerenciaCadastros = new JLabel("");
		lblGerenciaCadastros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGerenciaCadastros.setIcon(new ImageIcon(App.class.getResource("/resources/userManagerOver.png")));
				lblGerenciaCadastros.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Gerenciador de Cadastros");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGerenciaCadastros.setIcon(new ImageIcon(App.class.getResource("/resources/userManagerNormal.png")));
				lblGerenciaCadastros.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmCliente rb = new FrmCliente();
				rb.setVisible(true);
			}
		});
		lblGerenciaCadastros.setIcon(new ImageIcon(App.class.getResource("/resources/userManagerNormal.png")));
		
		JLabel lblRelatorios = new JLabel("");
		lblRelatorios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRelatorios.setIcon(new ImageIcon(App.class.getResource("/resources/relatoriosOver.png")));
				lblRelatorios.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Relatórios Gerados");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRelatorios.setIcon(new ImageIcon(App.class.getResource("/resources/relatoriosNormal.png")));
				lblRelatorios.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmRelatorios frmR;
				try {
					frmR = new FrmRelatorios();
					frmR.setVisible(true);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		lblRelatorios.setIcon(new ImageIcon(App.class.getResource("/resources/relatoriosNormal.png")));
		
		JLabel lblSettings = new JLabel("");
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSettings.setIcon(new ImageIcon(App.class.getResource("/resources/settingsOver.png")));
				lblSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Configurações");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSettings.setIcon(new ImageIcon(App.class.getResource("/resources/settingsNormal.png")));
				lblSettings.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmConfig frmC = new FrmConfig();
				frmC.setVisible(true);
			}
		});
		lblSettings.setIcon(new ImageIcon(App.class.getResource("/resources/settingsNormal.png")));
		
		JLabel lblSair = new JLabel("");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSair.setIcon(new ImageIcon(App.class.getResource("/resources/sairOver.png")));
				lblSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Fechar Programa");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSair.setIcon(new ImageIcon(App.class.getResource("/resources/sairNormal.png")));
				lblSair.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblSair.setIcon(new ImageIcon(App.class.getResource("/resources/sairNormal.png")));
		
		JLabel lblTotalDeRecibos = new JLabel("Total de Recibos Gerados:");
		
		JLabel lblTotalDeCadastros = new JLabel("Total de Cadastros:");
		
		lblTotalrecibos = new JLabel("000");
		
		
		lblTotalcadastros = new JLabel("000");
		
		
		JLabel lblHelp = new JLabel("");
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHelp.setIcon(new ImageIcon(App.class.getResource("/resources/helpSmallOver.png")));
				lblHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Ajuda do Sistema");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblHelp.setIcon(new ImageIcon(App.class.getResource("/resources/helpSmall.png")));
				lblHelp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("https://iwarecibos.sourceforge.io/"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		lblHelp.setIcon(new ImageIcon(App.class.getResource("/resources/helpSmall.png")));
		
		JLabel lblInfo = new JLabel("");
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblInfo.setIcon(new ImageIcon(App.class.getResource("/resources/infoSmallOver.png")));
				lblInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblStatus.setText("Sobre o Programa");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblInfo.setIcon(new ImageIcon(App.class.getResource("/resources/infoSmall.png")));
				lblInfo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblStatus.setText("");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ImageIcon icon = new ImageIcon(App.class.getResource("/resources/infoBig.png"));
				JOptionPane.showMessageDialog(null, "IwaRecibos 1.0"
											+ "\nPrograma Gratuito(Para uso PESSOAL)"
											+ "\nPagina de suporte: https://iwarecibos.sourceforge.io"
											+ "\n"
											+ "\nDesenvolvimento personalizado"
											+ "\nDesktop, Web, Mobile"
											+ "\nObrigado!", "Sobre o IwaRecibos", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		lblInfo.setIcon(new ImageIcon(App.class.getResource("/resources/infoSmall.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGerenciaRecibo)
					.addGap(18)
					.addComponent(lblGerenciaCadastros)
					.addGap(18)
					.addComponent(lblRelatorios)
					.addGap(18)
					.addComponent(lblSettings)
					.addGap(18)
					.addComponent(lblSair)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(lblTotalDeCadastros)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTotalcadastros))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTotalDeRecibos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTotalrecibos)))
					.addPreferredGap(ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
					.addComponent(lblInfo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHelp)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblGerenciaRecibo)
						.addComponent(lblGerenciaCadastros)
						.addComponent(lblRelatorios)
						.addComponent(lblSettings)
						.addComponent(lblSair))
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHelp)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalDeRecibos)
								.addComponent(lblTotalrecibos))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalDeCadastros)
								.addComponent(lblTotalcadastros)))
						.addComponent(lblInfo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		
		lblStatus = new JLabel("");
		panel.add(lblStatus);
		contentPane.setLayout(gl_contentPane);
	}
}
