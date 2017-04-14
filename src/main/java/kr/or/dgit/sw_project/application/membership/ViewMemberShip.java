package kr.or.dgit.sw_project.application.membership;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.service.MemberShipService;

public class ViewMemberShip extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnInsert;
	private JButton btnCancle;
	private ContentMemberShip pContent;
	
	public ViewMemberShip() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0}; //각 행의 가중치
		getContentPane().setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		label.setEnabled(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("\uC778\uD130\uD30C\uD06C\uACE0\uB515 B", label.getFont().getStyle(), label.getFont().getSize() + 5));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		getContentPane().add(label, gbc_label);
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/sign.png");
		label.setIcon(icon);	
		
		pContent = new ContentMemberShip();
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 10, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		getContentPane().add(pContent, gbc_pContent);

		JPanel pButton = new JPanel();
		GridBagConstraints gbc_pButton = new GridBagConstraints();
		gbc_pButton.insets = new Insets(0, 0, 0, 0);
		gbc_pButton.fill = GridBagConstraints.NONE;
		gbc_pButton.gridx = 0;
		gbc_pButton.gridy = 2;
		getContentPane().add(pButton, gbc_pButton);

		GridBagLayout gbl_pButton = new GridBagLayout();
		gbl_pButton.columnWidths = new int[] {100, 100};
		gbl_pButton.rowHeights = new int[]{55, 0};
		gbl_pButton.columnWeights = new double[]{0.0, 0.0};
		gbl_pButton.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pButton.setLayout(gbl_pButton);

		btnInsert = new JButton("가입");
		btnInsert.addActionListener(this);
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.insets = new Insets(0, 0, 0, 0);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 0;
		pButton.add(btnInsert, gbc_btnInsert);

		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		GridBagConstraints gbc_btnCancle = new GridBagConstraints();
		gbc_btnCancle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancle.insets = new Insets(0, 0, 0, 0);
		gbc_btnCancle.gridx = 1;
		gbc_btnCancle.gridy = 0;
		pButton.add(btnCancle, gbc_btnCancle);
		
		pContent.setViewMemberShip(ViewMemberShip.this);
		btnInsert.setEnabled(false);
		setVisible(true);
	}
	
	/*************************** actionPerformed ***************************/  

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInsert) {
			btnInsertActionPerformed(e);
		}
		
		if (e.getSource() == btnCancle) {
			btnCancleActionPerformed(e);
		}
	}

	private void btnInsertActionPerformed(ActionEvent e) { //입력 수정 테이블 인덱스 클릭시 수정으로 변함
		System.out.println("Click!");
		if(pContent.isEmptyCheck()){
			JOptionPane.showMessageDialog(null, "공란이 있습니다");
		}else if(!pContent.isPasswordEquals()){
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
		}else{
			if(JOptionPane.showConfirmDialog(null, "가입하시겠습니까?")==JOptionPane.YES_OPTION){
				MemberShipService.getInstance().insertMembersItem(pContent.getObject());
				dispose();
			}
		}
	}

	private void btnCancleActionPerformed(ActionEvent e) { //취소버튼
		dispose();
	}
	/***********************************************************************/
	
	public JButton getBtnInsert() {
		return btnInsert;
	}
}