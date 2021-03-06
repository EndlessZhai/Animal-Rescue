package application;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class PauseSubscene extends SubScene {

	// use only calibrate size
	ScreenSizeCalibrator sc = new ScreenSizeCalibrator();
	
	// load Background
	// need to find new panel image (this one seem to be too small)
	private final String BACKGROUND_PATH = ClassLoader.getSystemResource("images/pause_scene.png").toString();
	
	// constructor
	public PauseSubscene() {
		// TODO Auto-generated constructor stub
		super(new AnchorPane(), 0, 0);

		// Because i can't set this in constructor
		setWidth(sc.setTongSize(730));
		setHeight(sc.setTongSize(449));
		
		AnchorPane root = (AnchorPane) this.getRoot();
		// set background
		root.setStyle("-fx-background-color: transparent; " 
						+ "-fx-background-image: url(" + BACKGROUND_PATH + "); "
						+ "-fx-background-size: cover; ");
		
		// set initial position
		setLayoutX((ScreenSizeCalibrator.WIDTH - getWidth()) /2);
		setLayoutY(-getHeight());
	}
	
	// set transition in out
	public void transitionIn() {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setToX(0);
		t.setToY(getHeight() + (ScreenSizeCalibrator.HEIGHT-getHeight())/3);
		t.setDuration(new Duration(300));
		t.play();
	}
	public void transitionOut() {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setToX(0);
		t.setToY(-getHeight() - (ScreenSizeCalibrator.HEIGHT-getHeight())/3);
		t.setDuration(new Duration(300));
		t.play();
	}
	
	
}