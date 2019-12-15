
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LabeledColor;
import model.LabeledColorInventory;
import model.standard.Standard;
import tool.Configuration;
import tool.InternationalizationTool;

public class Launcher extends Application {

	public static final long FRECENCE = 1000l / 3l;

	private double xOffset = 0;
	private double yOffset = 0;

	private ScheduledExecutorService scheduler;
	private Robot robot;

	private Stage stage;
	private BorderPane mainXontainer;

	private Configuration configuration;

	@Override
	public void start(Stage primaryStage) {
		try {
			robot = new Robot();
			configuration = new Configuration();

			FXMLLoader loader;

			loader = new FXMLLoader();
			loader.setLocation(Launcher.class.getResource("view/Permanent.fxml"));
			mainXontainer = (BorderPane) loader.load();

			for (Node children : ((GridPane) mainXontainer.getTop()).getChildren()) {
				for (Menu menu : ((MenuBar) children).getMenus()) {

					if ("language".equals(menu.getId())) {

						ToggleGroup toggleGroup = new ToggleGroup();
						for (Locale locale : InternationalizationTool.getSupportedLocales()) {
							RadioMenuItem item = new RadioMenuItem();
							item.setToggleGroup(toggleGroup);
							item.setId(locale.getLanguage());
							item.setText(InternationalizationTool.getText(locale.getLanguage(), "resources/Permanent", configuration.getLocale()));
							item.setSelected(locale.equals(configuration.getLocale()));
							menu.getItems().add(item);
						}

					} else if ("about".equals(menu.getId())) {

					} else if ("close".equals(menu.getId())) {
						menu.getGraphic().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stage.close());

					} else if ("minimize".equals(menu.getId())) {
						menu.getGraphic().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stage.setIconified(true));

					}
				}
			}

			this.triggerLanguageChanged();

			stage = primaryStage;
			stage.setTitle("Color-unblinder");
			stage.setAlwaysOnTop(true);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(mainXontainer));
			stage.setResizable(false);
			stage.show();

			mainXontainer.setOnMousePressed(event -> {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			});
			mainXontainer.setOnMouseDragged(event -> {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			});

			scheduler = Executors.newSingleThreadScheduledExecutor();
			scheduler.scheduleAtFixedRate(
					() -> this.updateDisplay(),
					0l,
					1000l / 3l,
					TimeUnit.MILLISECONDS);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void triggerLanguageChanged() {

		for (Node children : ((GridPane) mainXontainer.getTop()).getChildren()) {
			for (Menu menu : ((MenuBar) children).getMenus()) {

				if ("standard".equals(menu.getId())) {

					menu.getItems().clear();

					menu.setText(InternationalizationTool.getText("Standard", "resources/Permanent", configuration.getLocale()));

					ToggleGroup toggleGroup = new ToggleGroup();
					for (Standard standard : Standard.getAll(configuration.getLocale())) {
						RadioMenuItem item = new RadioMenuItem();
						item.setToggleGroup(toggleGroup);
						item.setId(standard.getId());
						item.setText(standard.getName());
						item.setSelected(standard.equals(configuration.getStandard()));
						menu.getItems().add(item);
					}

				}
			}
		}

		this.updateDisplay();
	}

	@Override
	public void stop() throws Exception {
		scheduler.shutdown();
		super.stop();
	}

	public void updateDisplay() {

		Point point = MouseInfo.getPointerInfo().getLocation();
		Color pointedColor = robot.getPixelColor(point.x, point.y);

		int red = pointedColor.getRed();
		int green = pointedColor.getGreen();
		int blue = pointedColor.getBlue();

		boolean approximate = false;

		LabeledColorInventory inventory = configuration.getStandard().getInventory();

		LabeledColor color = inventory.getLabeledColor(red, green, blue);

		if (color == null) {
			approximate = true;
			color = inventory.getClosestLabeledColor(red, green, blue);
		}

		for (Node children : ((GridPane) mainXontainer.getCenter()).getChildren()) {
			if ("confirmationPan".equals(children.getId())) {
				children.setStyle("-fx-background-color: rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ");");

			} else if ("textualInformation".equals(children.getId())) {
				for (Node subChildren : ((GridPane) children).getChildren()) {
					if ("name".equals(subChildren.getId())) {

						String text = null;
						if (approximate) {
							text = "≈";
						} else {
							text = "=";
						}
						text += " " + color.getName();
						((Text) subChildren).setText(text);

					} else if ("description".equals(subChildren.getId())) {
						String text = InternationalizationTool.getText("R", "resources/Permanent", configuration.getLocale())
								+ ":"
								+ subColorToPercent(color.getRed())
								+ "% | "
								+ InternationalizationTool.getText("G", "resources/Permanent", configuration.getLocale())
								+ ":"
								+ subColorToPercent(color.getGreen())
								+ "% | "
								+ InternationalizationTool.getText("B", "resources/Permanent", configuration.getLocale())
								+ ":"
								+ subColorToPercent(color.getBlue())
								+ "%";

						((Text) subChildren).setText(text);

					}
				}
			}
		}

	}

	public static String subColorToPercent(int original) {
		String str = Integer.toString((int) Math.round(100f * ((float) original) / 255f));
		while (str.length() < 3)
			str = " " + str;
		return str;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
