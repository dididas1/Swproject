package kr.or.dgit.sw_project.application.chart;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.service.JoinFromSaleService;

public class ChartRootLayout extends Application {

	private Stage primaryStage;
	private TabPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Show Chart");
		initRootLayout();
		showSaleChartTab();
	}

	/**
	 * 상위 레이아웃을 초기화한다.
	 */
	public void initRootLayout() {
		try {
			// fxml 파일에서 상위 레이아웃을 가져온다.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ChartRootLayout.class.getResource("ChartRootLayout.fxml"));
			rootLayout = (TabPane) loader.load();

			// 상위 레이아웃을 포함하는 scene을 보여준다.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showSaleChartTab() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ChartRootLayout.class.getResource("SaleChartTab.fxml"));
			loader.load();
			SaleChartTabController saleChartTabController = loader.getController();

			List<JoinFromSale> list = JoinFromSaleService.getInstance().selectJoinFromSaleByAll();
			saleChartTabController.setSaleData(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void showChart() {
		launch();
	}
}