package application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.Menu;
import java.util.List;

import org.w3c.dom.events.EventException;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class UIManager {

	private Stage mainStage;
	private Scene mainScene;
	private AnchorPane uiRoot;
	private MenuSubscene helpSubScene;
	private MenuSubscene optionSubScene;
	private MenuSubscene playSubScene; // aka tutorial
	private MenuSubscene curShowSubScene; // curShowSS
	private MenuSubscene dummySubScene; // dummySS
	private MediaManager media;
	
	// use only calibrate size
	ScreenSizeCalibrator sc = new ScreenSizeCalibrator();

	// resource ClassLoader
	private final String BACKGROUND_PATH = ClassLoader.getSystemResource("images/b.jpg").toString();
	private final String CURSOR_PATH = ClassLoader.getSystemResource("images\\cursor_pointerFlat_shadow.png")
			.toString();

	// constructor
	public UIManager() {
		uiRoot = new AnchorPane();
		mainScene = new Scene(uiRoot);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createBackground();
		createAllButtons();
		createLogo();
		customCursor();
		createSubScene();
		createMediaAndPlay();
	}

	// get main stage
	public Stage getMainStage() {
		return mainStage;
	}
	
	protected void createMediaAndPlay() {
		media = new MediaManager();
		media.play();
	}

	// create buttons :
	protected void createAllButtons() {
		createPlayButton();
		createHelpButton();
		createOptionButton();
		createExitButton();
	}

	// play Button
	protected void createPlayButton() {
		ButtonGenerator butt = new ButtonGenerator("PLAY");
		butt.setLayoutX(sc.setPinSize(175));
		butt.setLayoutY(sc.setPinSize(450));
		uiRoot.getChildren().add(butt);

		butt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {

					if (curShowSubScene.equals(playSubScene)) {
						playSubScene.transitionOut();
						curShowSubScene = dummySubScene;
					} else {
						curShowSubScene.transitionOut();
						playSubScene.transitionIn();
						curShowSubScene = playSubScene;
					}
				}
			}
		});
	}

	// help button
	protected void createHelpButton() {
		ButtonGenerator butt = new ButtonGenerator("HELP");
		butt.setLayoutX(sc.setPinSize(175));
		butt.setLayoutY(sc.setPinSize(550));
		uiRoot.getChildren().add(butt);

		butt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					if (curShowSubScene.equals(helpSubScene)) {
						helpSubScene.transitionOut();
						curShowSubScene = dummySubScene;
					} else {
						curShowSubScene.transitionOut();
						helpSubScene.transitionIn();
						curShowSubScene = helpSubScene;
					}
				}
				arg0.consume();
			}
		});

	}

	// option button
	protected void createOptionButton() {
		ButtonGenerator opButt = new ButtonGenerator("OPTION");
		opButt.setLayoutX(sc.setPinSize(175));
		opButt.setLayoutY(sc.setPinSize(650));
		uiRoot.getChildren().add(opButt);

		opButt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				if (arg0.getButton().equals(MouseButton.PRIMARY)) {

					if (curShowSubScene.equals(optionSubScene)) {
						optionSubScene.transitionOut();
						curShowSubScene = dummySubScene;
					} else {
						curShowSubScene.transitionOut();
						optionSubScene.transitionIn();
						curShowSubScene = optionSubScene;
					}
				}

				arg0.consume();
			}
		});
	}

	// exit button
	protected void createExitButton() {
		ButtonGenerator butt = new ButtonGenerator("EXIT");
		butt.setLayoutX(sc.setPinSize(175));
		butt.setLayoutY(sc.setPinSize(750));
		uiRoot.getChildren().add(butt);

		butt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					// maybe create pop up confirm windows
					Platform.exit();
				}
			}
		});

	}

	// set Background
	protected void createBackground() {
		uiRoot.setStyle("-fx-background-image: url(" + BACKGROUND_PATH + "); " + "-fx-background-size: cover;");
	}

	// create Logo
	protected void createLogo() {
		LabelGenerator logo = new LabelGenerator("Animal Rescue");
		logo.setAsHeader();
		logo.setLayoutX(sc.setPinSize(200));
		logo.setLayoutY(sc.setPinSize(140));
		uiRoot.getChildren().add(logo);
	}

	// create subscenes
	protected void createSubScene() {
		curShowSubScene = new MenuSubscene();
		dummySubScene = new MenuSubscene();
		createHelpSubScene();
		createOptionSubScene();
		createPlaySubScene();
	}

	// aka tutorial
	// this subscene will a tutorial and { "I'm READY" } button which will start the
	// game
	protected void createPlaySubScene() {
		playSubScene = new MenuSubscene();

		LabelGenerator head_Tutorial = new LabelGenerator("Tutorial");
		head_Tutorial.setFont(new Font("Joystix Monospace", 36));
		playSubScene.getPane().getChildren().add(head_Tutorial);
		head_Tutorial.setAlignment(Pos.CENTER);
		head_Tutorial.setLayoutX(sc.setPinSize(350));
		head_Tutorial.setLayoutY(sc.setPinSize(30));
		
		LabelGenerator text_Tutorial = new LabelGenerator("\tthis is a tutorial demo "+
														  " so I don't \n know what to write "+
														  "yet, so nvm just a plain\n text to test.");
		
		text_Tutorial.setLayoutX(sc.setPinSize(20));
		text_Tutorial.setLayoutY(sc.setPinSize(100));
		playSubScene.getPane().getChildren().add(text_Tutorial);
		
		playSubScene.getSubSceneBtn().setText("OK");
		playSubScene.getSubSceneBtn().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(arg0.getButton().equals(MouseButton.PRIMARY)) {
					GameManager gameRoot = new GameManager();
					mainStage.setScene(gameRoot.getGameManager());
					mainStage.setFullScreen(true);
				}
			}
		});
		
		uiRoot.getChildren().add(playSubScene);
	}
	
	protected void createHelpSubScene() {
		helpSubScene = new MenuSubscene();
		uiRoot.getChildren().add(helpSubScene);
		helpSubScene.getSubSceneBtn().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				helpSubScene.transitionOut();
				curShowSubScene = dummySubScene;
			}
		});
		LabelGenerator head_help = new LabelGenerator("Help");
		head_help.setFont(new Font("Joystix Monospace", 36));
		helpSubScene.getPane().getChildren().add(head_help);
		head_help.setAlignment(Pos.CENTER);
		head_help.setLayoutX(sc.setPinSize(400));
		head_help.setLayoutY(sc.setPinSize(30));
	}
	
	protected void createOptionSubScene() {
		optionSubScene = new MenuSubscene();
		uiRoot.getChildren().add(optionSubScene);
		optionSubScene.getSubSceneBtn().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				optionSubScene.transitionOut();
				curShowSubScene = dummySubScene;
			}
		});
		LabelGenerator head_option = new LabelGenerator("Option");
		head_option.setFont(new Font("Joystix Monospace", 36));
		optionSubScene.getPane().getChildren().add(head_option);
		head_option.setAlignment(Pos.CENTER);
		head_option.setLayoutX(sc.setPinSize(380));
		head_option.setLayoutY(sc.setPinSize(30));
	}

	// set custom cursor. Just for FUN!
	protected void customCursor() {
		Image customCur = new Image(CURSOR_PATH);
		mainScene.setCursor(new ImageCursor(customCur));
	}

}
