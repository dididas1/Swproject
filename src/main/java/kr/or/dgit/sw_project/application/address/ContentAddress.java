package kr.or.dgit.sw_project.application.address;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import erp_myframework.PasswordPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.service.AddrService;

public class ContentAddress extends JPanel {


	private ComboPanel<String> tfpSiDo;
	private TextFieldPanel tfpDoro;
	private PasswordPanel passwordPanel;
	public ContentAddress() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);


		tfpSiDo = new ComboPanel<>();

		Vector<String> cbSiDo= new Vector<>();
		String[] sidoList = {"강원도","경기도","경상남도","경상북도","광주광역시","대구광역시","대전광역시","부산광역시",
				"서울특별시","세종특별자치시","울산광역시","인천광역시","전라남도","전라북도","제주특별자치도","충청남도","충청북도"};

		cbSiDo.add("선택해주세요");
		for(int i=0;i<sidoList.length;i++){
			cbSiDo.add(sidoList[i]);
		}
		tfpSiDo.setComboData(cbSiDo);
		tfpSiDo.setTitle("도/특별/광역시");
		GridBagConstraints gbc_tfpSiDo = new GridBagConstraints();
		gbc_tfpSiDo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSiDo.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSiDo.gridx = 0;
		gbc_tfpSiDo.gridy = 1;
		add(tfpSiDo, gbc_tfpSiDo);

		tfpDoro = new TextFieldPanel();
		tfpDoro.setTitle("도로명");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(tfpDoro, gbc_panel);
	}


	public ComboPanel<String> getTfpSiDo() {
		return tfpSiDo;
	}

	public TextFieldPanel getTfpDoro() {
		return tfpDoro;
	}
	
	
	public void clear() {
		tfpSiDo.setSelectedItem(0);
		tfpDoro.setTfValue("");
	}

	// 콤보박스에서 아이템선택하면 String 값으로 가져와서 검색후 시군구 콤보박스에 add아이템  안씀 주석...
	/*public void setCobobox(String Comboitems){
		tfpSiGunGu.getTf().removeAllItems();
		Vector<String> cbSiGunGu= new Vector<>();
		List<Address> list = AddrService.getInstance().selectSigungu(new Address(Comboitems));
		cbSiGunGu.add("선택해주세요");
		for(int i=0;i<list.size();i++){
			if(list.get(i).getSigungu()==list.get(i).getSigungu()){
				cbSiGunGu.add(list.get(i).toCobodata());
			}
			
		}
		tfpSiGunGu.setComboData(cbSiGunGu);
	}
*/
}
