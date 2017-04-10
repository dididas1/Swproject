package kr.or.dgit.sw_project.application.chart;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.service.JoinFromSaleService;

//고객사별 주문 수량
public class SaleChart extends Application{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Show Chart");
		initRootLayout();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SaleChart.class.getResource("SaleChartTab.fxml"));
			rootLayout = (BorderPane) loader.load();

			scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Scene getScene(){
		return scene;
	}

	public void showChart() {
		launch();
	}
}