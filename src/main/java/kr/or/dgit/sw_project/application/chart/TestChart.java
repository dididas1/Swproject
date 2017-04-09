package kr.or.dgit.sw_project.application.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class TestChart extends Application {
	private String austria = "Austria";
	private String brazil = "Brazil";
	private String france = "France";
	private String italy = "Italy";
	private String usa = "USA";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override 
	public void start(Stage stage) {
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> barChart = 
				new BarChart<>(xAxis,yAxis);
		barChart.setTitle("Country Summary");
		xAxis.setLabel("Country");       
		yAxis.setLabel("Value");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("2003");       
		series1.getData().add(new XYChart.Data(austria, 25601.34));
		series1.getData().add(new XYChart.Data(brazil, 20148.82));
		series1.getData().add(new XYChart.Data(france, 10000));
		series1.getData().add(new XYChart.Data(italy, 35407.15));
		series1.getData().add(new XYChart.Data(usa, 12000));      

		Scene scene  = new Scene(barChart,800,600);
		barChart.getData().add(series1);
		stage.setScene(scene);
		stage.show();
	}

	public void showChart() {
		launch();
	}
}