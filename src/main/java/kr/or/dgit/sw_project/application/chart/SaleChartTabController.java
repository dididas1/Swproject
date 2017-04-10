package kr.or.dgit.sw_project.application.chart;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.service.JoinFromSaleService;

//고객사별 주문 수량
public class SaleChartTabController implements Initializable{
	@FXML
	private BarChart<String, Integer> barChart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<JoinFromSale> list = JoinFromSaleService.getInstance().selectJoinFromSaleByAll();
		String[] arrayClientNames = new String[list.size()];
		int[] arraySaleAmount = new int[arrayClientNames.length];
		
		for(int i=0; i<list.size(); i++){
			arrayClientNames[i] = list.get(i).getClient().getClntName();
			System.out.println("Add Client Name :" + arrayClientNames[i]);

			//해당 client의 판매 수량 누적
			arraySaleAmount[i] += list.get(i).getSale().getSaleAmount();;
		}
		
		//시리즈 생성
		XYChart.Series series = new XYChart.Series();
		for(int j=0; j<arrayClientNames.length; j++){
			series.getData().add(new XYChart.Data(arrayClientNames[j], arraySaleAmount[j]));
		}
		
		barChart.getData().add(series);
	}
}
