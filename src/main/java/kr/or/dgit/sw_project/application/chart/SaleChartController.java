package kr.or.dgit.sw_project.application.chart;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.JoinFromSaleService;

//고객사별 주문 수량
public class SaleChartController implements Initializable{
	@FXML
	private BarChart<String, Integer> barChart;
	private List<Client> clientNameList;
	private String[] arrayClientNames;
	private List<JoinFromSale> list;
	private int[] arraySaleAmountofThisYear;
	private int[] arraySaleAmountofOneAgoYear;
	private int[] arraySaleAmountofTwoAgoYear;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setData();
	}
	
	public void setData(){
		clientNameList = ClientService.getInstance().selectClientByAll();
		arrayClientNames = new String[clientNameList.size()];

		for(int i=0; i<clientNameList.size(); i++){
			arrayClientNames[i] = clientNameList.get(i).getClntName();
			System.out.println("Add Client Name :" + arrayClientNames[i]);
		}

		//올해 연도 구하기
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int year = cal.get(cal.YEAR);

		Map<String, Object> thisYear = new HashMap<>();
		thisYear.put("date",year+"");

		Map<String, Object> oneAgoYear= new HashMap<>();
		oneAgoYear.put("date",(year-1)+"");

		Map<String, Object> twoAgoYear= new HashMap<>();
		twoAgoYear.put("date",(year-2)+"");

		arraySaleAmountofThisYear = createArraySaleAmount(twoAgoYear);
		arraySaleAmountofOneAgoYear = createArraySaleAmount(oneAgoYear);
		arraySaleAmountofTwoAgoYear = createArraySaleAmount(thisYear);

		//시리즈 생성
		XYChart.Series series1 = CreateSeries(arrayClientNames, arraySaleAmountofTwoAgoYear);
		series1.setName("2 Years Ago");

		XYChart.Series series2 = CreateSeries(arrayClientNames, arraySaleAmountofOneAgoYear);
		series2.setName("1 Years Ago");

		XYChart.Series series3 = CreateSeries(arrayClientNames, arraySaleAmountofThisYear);
		series3.setName("This Year");

		barChart.getData().addAll(series1,series2,series3);
	}

	private int[] createArraySaleAmount(Map<String, Object> thisYear) {
		list = JoinFromSaleService.getInstance().selectJoinFromSaleByYear(thisYear);
		int[] arraySaleAmount = new int[clientNameList.size()];

		for (int i=0; i< list.size(); i++){
			//인자로 받은 list의 i번째 인자의 수량과 클라이언트
			int amount = list.get(i).getSale().getSaleAmount();
			String clientName = list.get(i).getClient().getClntName();

			//client Name이 몇 번째 배열에 있는지 확인
			int idx = -1;
			for(int j=0; j<clientNameList.size(); j++){
				System.out.println("    Check: X축의 Client 이름 : " + arrayClientNames[j] + " = 인자List의 클라이언트 이름 : " + clientName);
				if(arrayClientNames[j].equals(clientName))
					idx = j;
			}
			System.out.println("인덱스 확인 : " + clientName + " : " + idx);

			//해당 client의 판매 수량 누적
			if(idx != -1){
				arraySaleAmount[idx] += amount;
				System.out.println("고객사: "+clientName +"  판매량: "+arraySaleAmount[idx]+"\n\n");
			}
		}
		return arraySaleAmount;
	}

	private XYChart.Series CreateSeries(String[] arrayClientNames, int[] arraySaleAmount) { //시리즈 생성
		XYChart.Series series = new XYChart.Series();
		for (int i = 0; i < arraySaleAmount.length; i++) {
			series.getData().add(new XYChart.Data<>(arrayClientNames[i], arraySaleAmount[i]));
			System.out.println("@@@@@@@@@@ 고객사: " + arrayClientNames[i] +" 판매량 : " + arraySaleAmount[i]);
		}
		return series;
	}
}
