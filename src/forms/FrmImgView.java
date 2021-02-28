package forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class FrmImgView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblimg;
	private String imgFinal;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FrmImgView dialog = new FrmImgView();
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
	public FrmImgView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmImgView.class.getResource("/resources/appIcon.png")));
		setTitle("Visualizador de Skins");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				getImg();
			}
		});
		setModal(true);
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		lblimg = new JLabel("");
		lblimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					dispose();
				}
			}
		});
		scrollPane.setViewportView(lblimg);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void setImg(String img){
		imgFinal = img.replaceAll(".html", ".png");
		
	}
	
	public void getImg(){
		ImageIcon icon= new ImageIcon("modelos/" + imgFinal);
		lblimg.setIcon(icon);
	}
}
