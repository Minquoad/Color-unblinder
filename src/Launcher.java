
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.IOException;
import java.util.Locale;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.LabeledColor;
import model.LabeledColorInventory;
import model.standard.Standard;
import tool.Configuration;
import tool.InternationalizationTool;
import tool.RgbUnit;

public class Launcher extends Application {

	public static final long FRECENCE = 1000l / 3l;

	private double xOffset = 0;
	private double yOffset = 0;

	private Timeline scheduler;
	private Robot robot;

	private Stage stage;
	private BorderPane mainContainer;

	@Override
	public void start(Stage primaryStage) {
		try {
			robot = new Robot();

			FXMLLoader loader;

			loader = new FXMLLoader();
			loader.setLocation(Launcher.class.getResource("view/Permanent.fxml"));
			mainContainer = (BorderPane) loader.load();

			for (Node children : ((Pane) mainContainer.getTop()).getChildren()) {

				if ("configurationMenus".equals(children.getId())) {
					for (Menu menu : ((MenuBar) children).getMenus()) {

						if ("option".equals(menu.getId())) {
							for (MenuItem menuItem : menu.getItems()) {

								if ("language".equals(menuItem.getId())) {

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
										((Menu) menuItem).getItems().add(item);
									}

								}
							}
						}
					}
				} else if ("windowButtonsPan".equals(children.getId())) {

					for (Node buttonNode : ((Pane) children).getChildren()) {
						Button button = (Button) buttonNode;

						if ("about".equals(button.getId())) {
							button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

								try {
									FXMLLoader aboutLoader = new FXMLLoader();
									aboutLoader.setLocation(Launcher.class.getResource("view/About.fxml"));
									Pane about = (Pane) aboutLoader.load();
									for (Node aboutChildren : about.getChildren())
										if ("version".equals(aboutChildren.getId()))
											((Text) aboutChildren).setText(Configuration.VERSION);

									Stage dialog = new Stage();
									dialog.initOwner(stage);
									dialog.setScene(new Scene(about));
									dialog.getIcons().add(getIcon());
									dialog.setResizable(false);
									dialog.showAndWait();

								} catch (IOException e) {
									e.printStackTrace();
								}
							});

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
			stage.setScene(new Scene(mainContainer));
			stage.setResizable(false);
			stage.getIcons().add(getIcon());

			mainContainer.setOnMousePressed(event -> {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			});
			mainContainer.setOnMouseDragged(event -> {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			});

			stage.show();

			scheduler = new Timeline(
					new KeyFrame(
							Duration.millis(1000l / 3l),
							event -> this.updateDisplay()));
			scheduler.setCycleCount(Timeline.INDEFINITE);
			scheduler.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void triggerLanguageChanged() {

		for (Node children : ((Pane) mainContainer.getTop()).getChildren()) {

			if ("configurationMenus".equals(children.getId())) {
				for (Menu menu : ((MenuBar) children).getMenus()) {

					if ("option".equals(menu.getId())) {

						((Text) menu.getGraphic()).setText(InternationalizationTool.getText("Option", "resources/Permanent"));

						for (MenuItem menuItem : menu.getItems()) {

							if ("standard".equals(menuItem.getId())) {

								((Menu) menuItem).setText(InternationalizationTool.getText("Standard", "resources/Permanent"));

								((Menu) menuItem).getItems().clear();

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
									((Menu) menuItem).getItems().add(item);
								}

							} else if ("rgbUnit".equals(menuItem.getId())) {

								((Menu) menuItem).setText(InternationalizationTool.getText("rgbUnit", "resources/Permanent"));

								for (MenuItem rgbUnit : ((Menu) menuItem).getItems()) {
									((RadioMenuItem) rgbUnit).setSelected(Configuration.getRgbUnit().toString().equals(rgbUnit.getId()));
									((RadioMenuItem) rgbUnit).addEventHandler(ActionEvent.ACTION, event -> {
										Configuration.setRgbUnit(RgbUnit.valueOf(rgbUnit.getId()));
										updateDisplay();
									});
								}
							}
						}
					}
				}
			}
		}

		for (Node children : ((Pane) mainContainer.getTop()).getChildren()) {

			if ("configurationMenus".equals(children.getId())) {
				for (Menu menu : ((MenuBar) children).getMenus()) {

					if ("standard".equals(menu.getId())) {

						((Text) menu.getGraphic()).setText(InternationalizationTool.getText("Standard", "resources/Permanent"));

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
		scheduler.stop();
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

		String name = color == null ? InternationalizationTool.getText("StandardNotDefined", "resources/Permanent") : color.getName();
		String accuracySymbol = color == null ? "x" : (approximate ? "≈" : "=");
		if (stage != null)
			stage.setTitle(accuracySymbol + " " + name);

		for (Node children : ((Pane) mainContainer.getCenter()).getChildren()) {
			Pane pane = ((Pane) children);

			if ("confirmation".equals(pane.getId())) {
				pane.setStyle("-fx-background-color: rgb(" + red + ", " + green + ", " + blue + ");");

			} else if ("rightness".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(accuracySymbol);

			} else if ("name".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(name);

			} else if ("r".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						InternationalizationTool.getText("R", "resources/Permanent")
								+ ":"
								+ Configuration.getRgbUnit().format(red));
				pane.setVisible(Configuration.getRgbUnit() != RgbUnit.HEXADECIMAL);

			} else if ("g".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						InternationalizationTool.getText("G", "resources/Permanent")
								+ ":"
								+ Configuration.getRgbUnit().format(green));
				pane.setVisible(Configuration.getRgbUnit() != RgbUnit.HEXADECIMAL);

			} else if ("b".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						InternationalizationTool.getText("B", "resources/Permanent")
								+ ":"
								+ Configuration.getRgbUnit().format(blue));
				pane.setVisible(Configuration.getRgbUnit() != RgbUnit.HEXADECIMAL);

			} else if ("h".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						"#"
								+ Configuration.getRgbUnit().format(red)
								+ Configuration.getRgbUnit().format(green)
								+ Configuration.getRgbUnit().format(blue));
				pane.setVisible(Configuration.getRgbUnit() == RgbUnit.HEXADECIMAL);
			}
		}

	}

	public static Image getIcon() {
		return new Image(Launcher.class.getClassLoader().getResourceAsStream("resources/icon.png"));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
