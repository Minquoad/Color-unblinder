
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

	@Override
	public void start(Stage primaryStage) {
		try {
			robot = new Robot();

			FXMLLoader loader;

			loader = new FXMLLoader();
			loader.setLocation(Launcher.class.getResource("view/Permanent.fxml"));
			mainXontainer = (BorderPane) loader.load();

			for (Node children : ((Pane) mainXontainer.getTop()).getChildren()) {

				if ("configurationMenus".equals(children.getId())) {
					for (Menu menu : ((MenuBar) children).getMenus()) {
						if ("language".equals(menu.getId())) {

							ToggleGroup toggleGroup = new ToggleGroup();
							for (Locale locale : InternationalizationTool.getSupportedLocales()) {
								RadioMenuItem item = new RadioMenuItem();
								item.setToggleGroup(toggleGroup);
								item.setId(locale.getLanguage());
								item.setText(InternationalizationTool.getText(locale.getLanguage(), "resources/Permanent"));
								item.setSelected(locale.equals(Configuration.getLocale()));
								item.addEventHandler(ActionEvent.ACTION, event -> {
									Configuration.setLocale(locale);
									triggerLanguageChanged();
								});
								menu.getItems().add(item);
							}

						}
					}
				} else if ("windowButtonsPan".equals(children.getId())) {

					for (Node buttonNode : ((Pane) children).getChildren()) {
						Button button = (Button) buttonNode;
						if ("about".equals(button.getId())) {

						} else if ("close".equals(button.getId())) {
							button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stage.close());

						} else if ("iconify".equals(button.getId())) {
							button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stage.setIconified(true));

						}
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

		for (Node children : ((Pane) mainXontainer.getTop()).getChildren()) {

			if ("configurationMenus".equals(children.getId())) {
				for (Menu menu : ((MenuBar) children).getMenus()) {

					if ("standard".equals(menu.getId())) {

						((Label) menu.getGraphic()).setText(InternationalizationTool.getText("Standard", "resources/Permanent"));

						menu.getItems().clear();

						ToggleGroup toggleGroup = new ToggleGroup();
						for (Standard standard : Standard.getAll(Configuration.getLocale())) {
							RadioMenuItem item = new RadioMenuItem();
							item.setToggleGroup(toggleGroup);
							item.setId(standard.getId());
							item.setText(standard.getName());
							item.setSelected(standard.equals(Configuration.getStandard()));
							item.addEventHandler(ActionEvent.ACTION, event -> {
								Configuration.setStandard(standard);
								updateDisplay();
							});
							menu.getItems().add(item);
						}

					}
				}
			}
		}

		this.updateDisplay();
	}

	@Override
	public void stop() throws Exception {
		scheduler.shutdown();
		Configuration.save();
		super.stop();
	}

	public void updateDisplay() {

		Point point = MouseInfo.getPointerInfo().getLocation();
		Color pointedColor = robot.getPixelColor(point.x, point.y);

		int red = pointedColor.getRed();
		int green = pointedColor.getGreen();
		int blue = pointedColor.getBlue();

		boolean approximate = false;

		LabeledColorInventory inventory = Configuration.getStandard().getInventory();

		LabeledColor color = inventory.getLabeledColor(red, green, blue);

		if (color == null) {
			approximate = true;
			color = inventory.getClosestLabeledColor(red, green, blue);
		}

		for (Node children : ((Pane) mainXontainer.getCenter()).getChildren()) {
			
			if ("confirmation".equals(children.getId())) {
				((Pane) children).setStyle("-fx-background-color: rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ");");

			} else if ("informations".equals(children.getId())) {
				for (Node subChildren : ((Pane) children).getChildren()) {
					
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
						String text = InternationalizationTool.getText("R", "resources/Permanent")
								+ ":"
								+ subColorToPercent(color.getRed())
								+ "%    "
								+ InternationalizationTool.getText("G", "resources/Permanent")
								+ ":"
								+ subColorToPercent(color.getGreen())
								+ "%    "
								+ InternationalizationTool.getText("B", "resources/Permanent")
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
