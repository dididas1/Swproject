package kr.or.dgit.sw_project.application.chart;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.service.ClientService;

//고객사별 주문 수량
public class SaleChartTabController {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	private ObservableList<String> ClientNameList = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		// 클라이언트 이름을 배열로 가져온다.
		List<Client> list = ClientService.getInstance().selectClientByAll();
		String[] arrayClientNames = new String[list.size()];
		for(int i=0; i<list.size(); i++){
			arrayClientNames[i] = list.get(i).getClntName();
			System.out.println("Add Client Name :" + arrayClientNames[i]);
		}
		// 리스트로 변환하고 나서 ObservableList에 추가한다.
		ClientNameList.addAll(Arrays.asList(arrayClientNames));

		// 수평축에 클라이언트 이름을 카테고리로 할당한다.
		xAxis.setCategories(ClientNameList);
	}

	public void setSaleData(List<JoinFromSale> list) {
		// 클라이언트별 판매수량 개수를 누적한다.
		int[] arraySaleAmount = new int[ClientNameList.size()];

		for (int i=0; i< list.size(); i++){
			
			//인자로 받은 list의 i번째 인자의 수량과 클라이언트
			int amount = list.get(i).getSale().getSaleAmount();
			String clientName = list.get(i).getClient().getClntName();
			
			//client Name이 몇 번째 배열에 있는지 확인
			int idx = -1;
			for(int j=0; j<ClientNameList.size(); j++){
				System.out.println("    Check: X축의 Client 이름 : " + ClientNameList.get(j) + " = 인자List의 클라이언트 이름 : " + clientName);
				if(ClientNameList.get(j).equals(clientName))
					idx = j;
			}
			System.out.println("인덱스 확인 : " + clientName + " : " + idx);

			//해당 client의 판매 수량 누적
			if(idx != -1){
				arraySaleAmount[idx] += amount;
				System.out.println("고객사: "+clientName +"  판매량: "+arraySaleAmount[idx]+"\n\n");
			}
		}
		
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		// 클라이언트별로 XYChart.Data 객체를 만든다. series에 추가한다.
		for (int i = 0; i < arraySaleAmount.length; i++) {
			series.getData().add(new XYChart.Data<>(ClientNameList.get(i), arraySaleAmount[i]));
			System.out.println("@@@@@@@@@@ 고객사: " + ClientNameList.get(i) +" 판매량 : " + arraySaleAmount[i]);
		}
		barChart.getData().add(series);
	}
}
