package kr.or.dgit.sw_project.application.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.service.JoinFromSaleService;
import kr.or.dgit.sw_project.service.JoinFromSoftwareService;

public class GenerateExcel {
	private WritableCellFormat titleFormat;
	private WritableCellFormat textFormat;
	private WritableCellFormat columnFormat;
	private File file;
	private WritableWorkbook workbook;
	private String path;

	public GenerateExcel(String path) {
		this.path=path;
	}

	public void CreateExcel() {
		createFile();
		setFormat();
		CreateExcelByOrderDate();
		CreateExcelByClient();
		System.out.println("생성완료");
		try {
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	private void createFile() {
		file = new File(path);
		try {
			workbook = Workbook.createWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		} // 엑셀파일 객체 생성
	}

	private void setFormat(){
		titleFormat = new WritableCellFormat();  
		columnFormat = new WritableCellFormat();
		textFormat = new WritableCellFormat();

		try {
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFormat.setFont(new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, 
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT));
			titleFormat.setAlignment(Alignment.CENTRE);

			columnFormat = new WritableCellFormat();  
			columnFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			columnFormat.setBackground(Colour.LIGHT_ORANGE);
			columnFormat.setAlignment(Alignment.CENTRE);
			columnFormat.setFont(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, 
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT));

			textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			textFormat.setBackground(Colour.IVORY);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public void CreateExcelByOrderDate() {
		List<JoinFromSale> dataListForOrderDate = JoinFromSaleService.getInstance().selectJoinFromSaleByAllOrderByOrderDate();
		String[] column = {"주문 날짜","분류","품목명","주문번호","주문수량","판매금액"};
		try{
			workbook.createSheet("전체 판매현황", 0); 
			WritableSheet sheetForOrderDate = workbook.getSheet(0);

			// Column Width 설정
			sheetForOrderDate.setColumnView(0, 14);
			sheetForOrderDate.setColumnView(2, 23);
			sheetForOrderDate.setColumnView(3, 10);
			sheetForOrderDate.setColumnView(4, 10);
			sheetForOrderDate.setColumnView(5, 14);
			//Title Setting
			sheetForOrderDate.mergeCells(0, 0, 5, 0);

			Label titleLabel = new Label(0,0,"전체 소프트웨어 판매 현황");
			sheetForOrderDate.addCell(titleLabel);
			titleLabel.setCellFormat(titleFormat);

			//Column Setting
			for(int i=0; i<column.length; i++){
				Label label = new Label(i,1,column[i]);
				label.setCellFormat(columnFormat);
				sheetForOrderDate.addCell(label);
			}

			// 데이터 삽입
			for(int i=2; i < dataListForOrderDate.size()+2; i++){
				JoinFromSale joinFromSale = dataListForOrderDate.get(i-2);
				Label[] labelArr = {
						new Label(0, i, joinFromSale.getSale().getOrderDate()),
						new Label(1, i, joinFromSale.getCategory().getGroupName()),
						new Label(2, i, joinFromSale.getSoftware().getSwName()),
						new Label(3, i, joinFromSale.getSale().getSaleCode()),
						new Label(4, i, String.valueOf(joinFromSale.getSale().getSaleAmount())),
						new Label(5, i, String.format("%,d",joinFromSale.getSaleDetail().getTotalSalePrice()))
				};
				for(int j=0; j <labelArr.length; j++){
					sheetForOrderDate.addCell(labelArr[j]);
					labelArr[j].setCellFormat(textFormat);	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void CreateExcelByClient() {
		List<JoinFromSoftware> dataListForClient = JoinFromSoftwareService.getInstance().selectJoinFromSoftwareByAllOrderByClntName();
		String[] column = {"공급회사명","주문일자","고객상호","품명","수량","단가","금액","세금","총 납품 금액"};
		try{
			workbook.createSheet("공급회사별 판매현황", 1);   
			WritableSheet sheetForClient = workbook.getSheet(1); // 시트 생성

			// Column Width 설정
			sheetForClient.setColumnView(0, 23);
			sheetForClient.setColumnView(1, 14);
			sheetForClient.setColumnView(2, 23);
			sheetForClient.setColumnView(3, 23);
			sheetForClient.setColumnView(4, 10);
			sheetForClient.setColumnView(5, 14);
			sheetForClient.setColumnView(6, 14);
			sheetForClient.setColumnView(7, 14);
			sheetForClient.setColumnView(8, 14);
			//Title Setting
			sheetForClient.mergeCells(0, 0, 8, 0);

			Label titleLabel = new Label(0,0,"공급회사별 거래 내역");
			sheetForClient.addCell(titleLabel);
			titleLabel.setCellFormat(titleFormat);

			//Column Setting
			for(int i=0; i<column.length; i++){
				Label label = new Label(i,1,column[i]);
				label.setCellFormat(columnFormat);
				sheetForClient.addCell(label);
			}

			// 데이터 삽입
			for(int i=0; i < dataListForClient.size(); i++){
				JoinFromSoftware joinFromSoftware = dataListForClient.get(i);
				Label[] labelArr = {
						new Label(0, i+2, joinFromSoftware.getSupplyCompany().getCompName()),
						new Label(1, i+2, joinFromSoftware.getSale().getOrderDate()),
						new Label(2, i+2, joinFromSoftware.getClient().getClntName()),
						new Label(3, i+2, joinFromSoftware.getSoftware().getSwName()),
						new Label(4, i+2, String.valueOf(joinFromSoftware.getSale().getSaleAmount())),
						new Label(5, i+2, String.format("%,d",joinFromSoftware.getSoftware().getSalePrice())),
						new Label(6, i+2, String.format("%,d",joinFromSoftware.getSaleDetail().getTotalSalePrice())),
						new Label(7, i+2, String.format("%,d",joinFromSoftware.getSaleDetail().getTax())),
						new Label(8, i+2, String.format("%,d",joinFromSoftware.getSaleDetail().getTaxSaleprice()))
				};
				System.out.println(labelArr);
				for(int j=0; j <labelArr.length; j++){
					sheetForClient.addCell(labelArr[j]);
					labelArr[j].setCellFormat(textFormat);	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}