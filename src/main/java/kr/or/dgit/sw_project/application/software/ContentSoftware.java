package kr.or.dgit.sw_project.application.software;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.CategoryService;
import kr.or.dgit.sw_project.service.JoinFromSoftwareService;
import kr.or.dgit.sw_project.service.SoftwareService;

@SuppressWarnings("serial")
public class ContentSoftware extends JPanel implements MouseListener {
	private JLabel lblImage;
	private TextFieldPanel tfpSWCode;
	private TextFieldPanel tfpSWName;
	private TextFieldPanel tfpSwPrice;
	private ComboPanel tfpGroupName;
	private JComboBox tfpGroupNameBox;
	private byte[] pic;
	private JPanel panel;

	public ContentSoftware() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 160, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		tfpSWCode = new TextFieldPanel();
		tfpSWCode.setTitle("제품번호");
		tfpSWCode.getTf().setFocusable(false);
		tfpSWCode.getTf().setEditable(false);
		GridBagConstraints gbc_tfpSWCode = new GridBagConstraints(); 
		gbc_tfpSWCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSWCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSWCode.gridx = 0;
		gbc_tfpSWCode.gridy = 1;
		add(tfpSWCode, gbc_tfpSWCode);

		tfpSWName = new TextFieldPanel();
		tfpSWName.setTitle("제품명");
		GridBagConstraints gbc_tfpSWName = new GridBagConstraints();
		gbc_tfpSWName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSWName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSWName.gridx = 0;
		gbc_tfpSWName.gridy = 2;
		add(tfpSWName, gbc_tfpSWName);

		tfpSwPrice = new TextFieldPanel();
		tfpSwPrice.setTitle("판매가격");
		GridBagConstraints gbc_tfpSwPrice = new GridBagConstraints();
		gbc_tfpSwPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwPrice.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSwPrice.gridx = 0;
		gbc_tfpSwPrice.gridy = 3;
		add(tfpSwPrice, gbc_tfpSwPrice);

		tfpGroupName = new ComboPanel();
		tfpGroupName.setTitle("분류");
		GridBagConstraints gbc_tfpGroupName = new GridBagConstraints();
		gbc_tfpGroupName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpGroupName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpGroupName.gridx = 0;
		gbc_tfpGroupName.gridy = 4;
		add(tfpGroupName, gbc_tfpGroupName);

		lblImage = new JLabel("");
		lblImage.setPreferredSize(new Dimension(110, 110));
		lblImage.addMouseListener(this);

		File file = new File(System.getProperty("user.dir")+"/build/resources/main/softwareimage/select.png");
		setImg(file);

		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.fill = GridBagConstraints.BOTH;
		gbc_lblImage.gridx = 1;
		gbc_lblImage.gridy = 1;
		gbc_lblImage.insets = new Insets(10, 50, 10, 5);
		gbc_lblImage.gridheight = 4;
		add(lblImage, gbc_lblImage);
	}

	public boolean isPatternCheck(){// 판매가란에 숫자만(9자리 미만)입력 하였는지 체크
		boolean isPtenCh = false;
		if(Pattern.matches("^[0-9]{0,9}|([0-9]{0,3},)?([0-9]{0,3},)?[0-9]{0,3}$", tfpSwPrice.getTfValue())==false){
			isPtenCh = true;
		}
		return isPtenCh;
	}



	public int isWsCheck(){// 공백 체크
		int isWsCheck = 0;
		if(tfpSWName.getTfValue().equals("") ||
				tfpSwPrice.getTfValue().equals("")){
			isWsCheck = 1;
		}else if(tfpGroupName.getSelectItem()=="선택해주세요"){
			isWsCheck = 2;
		}
		return isWsCheck;
	}




	public void getSwCode(){//소프트웨어 전체 목록, 제품코드를 SW000 으로 자동 내림차순
		List<JoinFromSoftware> list = JoinFromSoftwareService.getInstance().selectJoinFromSoftwareByAll();
		String value = String.format("SW%03d", list.size()+1);
		tfpSWCode.setTfValue(value);
	}

	public void setComboBox(){ // 분류 목록에 있는 분류명을 가져와 콤보박스에 삽입
		tfpGroupName.getTf().removeAllItems();
		List<Category> list = CategoryService.getInstance().selectCategoryByAll();
		String[][] getComboObj = new String[list.size()][];
		String[] comboObj = new String[list.size()];
		Vector<String> setComboObj = new Vector<>();
		setComboObj.removeAllElements();
		setComboObj.add("선택해주세요");
		for(int i=0 ; i<list.size() ; i++){
			getComboObj[i] = list.get(i).toArray();
			comboObj[i] = getComboObj[i][1];
			setComboObj.add(comboObj[i]);
		}
		tfpGroupName.setComboData(setComboObj);
	}




	public Software getSoftwareCode(){ //제품코드 텍스트에 있는 값을 가져와 리턴
		String swCode = tfpSWCode.getTfValue();
		return new Software(swCode);
	}

	public void initSetting(){ //초기화
		tfpSWName.requestFocus();
		tfpSWName.setTfValue("");
		tfpSwPrice.setTfValue("");
		tfpGroupName.setSelectedItem("선택해주세요");
		File file = new File(System.getProperty("user.dir")+"/build/resources/main/softwareimage/select.png");
		setImg(file);
	}

	public void setObject(Object[] swObj){ //클릭된 테이블의 인덱스에 있는 컬럼들을 가져와 각각의 입력목록에 삽입
		tfpSWCode.setTfValue(String.valueOf(swObj[0]));
		tfpGroupName.setSelectedItem(swObj[1]);
		tfpSWName.setTfValue(String.valueOf(swObj[2]));
		tfpSwPrice.setTfValue(String.valueOf(swObj[3]));

		//DB에서 swCode와 일치하는 img를 가져옴
		byte[] pic = JoinFromSoftwareService.getInstance().
				selectJoinFromSoftwareByCode((String.valueOf(swObj[0]))).
				getSoftware().getSwImg();

		ImageIcon icon = new ImageIcon(pic);
		lblImage.setIcon(icon);
	}

	public void insertObject(){// 하나의 소프트웨어를 등록
		Map<String, Object> insertSoftware = new HashMap<String, Object>();

		//lblImage의 Icon을 받아와서 byte배열 생성
		Icon icon = lblImage.getIcon();
		byte[] bytes = null;
		BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
			try {
				ImageIO.write(img, "png", ios);
				// Set a flag to indicate that the write was successful
			} finally {
				ios.close();
			}
			bytes = baos.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		insertSoftware.put("swCode", tfpSWCode.getTfValue());
		insertSoftware.put("groupName", tfpGroupName.getSelectItem());
		insertSoftware.put("swName", tfpSWName.getTfValue());
		insertSoftware.put("salePrice", tfpSwPrice.getTfValue());
		insertSoftware.put("swInven", 0);
		insertSoftware.put("swImg", bytes);
		insertSoftware.put("swIsSale", false);
		SoftwareService.getInstance().insertSoftwareItem(insertSoftware);
	}

	public void updateObject() {// 하나의 소프트웨어를 수정
		Map<String, Object> updateSoftware = new HashMap<String, Object>();

		//lblImage의 Icon을 받아와서 byte배열 생성
		Icon icon = lblImage.getIcon();
		byte[] bytes = null;
		BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
			try {
				ImageIO.write(img, "png", ios);
				// Set a flag to indicate that the write was successful
			} finally {
				ios.close();
			}
			bytes = baos.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		updateSoftware.put("swCode", tfpSWCode.getTfValue());
		updateSoftware.put("groupName", tfpGroupName.getSelectItem());
		updateSoftware.put("swName", tfpSWName.getTfValue());
		updateSoftware.put("swImg", bytes);
		updateSoftware.put("salePrice", tfpSwPrice.getTfValue());
		SoftwareService.getInstance().updateSoftwareItem(updateSoftware);
	}

	public void swCodeReset(Object[] swCodes) {// swCode의 내림차순패턴이 하나 비었을때 앞당김
		Map<String, Object> swCodeReset = new HashMap<String, Object>();
		for(int i=1 ; i<=swCodes.length ; i++){
			if(!(String.format("SW%03d", i).equals(swCodes[i-1]))){
				swCodeReset.put("resetCode", String.format("SW%03d", i+1));
				swCodeReset.put("swCode", String.format("SW%03d", i+1));
				SoftwareService.getInstance().swCodeReset(swCodeReset);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == lblImage) {
			mousePressedLblImage(e);
		}
	}

	public void mouseReleased(MouseEvent e) {}
	protected void mousePressedLblImage(MouseEvent e) {	//Image를 클릭했을 때 파일추저 실행
		JFileChooser jFileChooser = new JFileChooser("c:/");
		int value = jFileChooser.showOpenDialog(null);{
			File file = jFileChooser.getSelectedFile();
			setImg(file);
		}
	}

	private void setImg(File file) { //file로 이미지를 세팅
		try {
			InputStream inputStream = new FileInputStream(file);
			pic = new byte[inputStream.available()];
			inputStream.read(pic);
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(pic);
		lblImage.setIcon(icon);		
	}


}
