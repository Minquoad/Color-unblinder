
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.IOException;
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
import model.LabeledColor;
import model.LabeledColorInventory;
import model.standard.Standard;
import tool.Configuration;
import tool.InternationalizationTool;
import tool.Unit;

public class Launcher extends Application {

	public static final long FRECENCE = 1000l / 3l;

	private double xOffset = 0;
	private double yOffset = 0;

	private ScheduledExecutorService scheduler;
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
			stage.show();

			mainContainer.setOnMousePressed(event -> {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			});
			mainContainer.setOnMouseDragged(event -> {
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

							} else if ("unit".equals(menuItem.getId())) {

								((Menu) menuItem).setText(InternationalizationTool.getText("Unit", "resources/Permanent"));

								for (MenuItem unit : ((Menu) menuItem).getItems()) {
									((RadioMenuItem) unit).setSelected(Configuration.getUnit().toString().equals(unit.getId()));
									((RadioMenuItem) unit).addEventHandler(ActionEvent.ACTION, event -> {
										Configuration.setUnit(Unit.valueOf(unit.getId()));
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

		for (Node children : ((Pane) mainContainer.getCenter()).getChildren()) {
			Pane pane = ((Pane) children);

			if ("confirmation".equals(pane.getId())) {
				pane.setStyle("-fx-background-color: rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ");");

			} else if ("rightness".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(approximate ? "≈" : "=");

			} else if ("name".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(color.getName());

			} else if ("r".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						InternationalizationTool.getText("R", "resources/Permanent")
								+ ":"
								+ Configuration.getUnit().format(color.getRed()));

			} else if ("g".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						InternationalizationTool.getText("G", "resources/Permanent")
								+ ":"
								+ Configuration.getUnit().format(color.getGreen()));

			} else if ("b".equals(pane.getId())) {
				((Text) pane.getChildren().get(0)).setText(
						InternationalizationTool.getText("B", "resources/Permanent")
								+ ":"
								+ Configuration.getUnit().format(color.getBlue()));
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
