package application;

/*
	Deleted some function not use
*/

import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class LabelGenerator extends Label{

	// use only calibrate size
	ScreenSizeCalibrator sc = new ScreenSizeCalibrator();
	
	// Size controller
	private final double HEAD_FONT_SIZE = sc.setPinSize(172);
	private final double NORM_FONT_SIZE = sc.setPinSize(48);
	
	// Text style, including COLOR, FONT and SIZE. Probably background later.
	
	private final String HEAD_STYLE = "-fx-text-fill: #ffff00;"
			+ "-fx-font-family: 'Animal Alphabet'; "
			+ "-fx-font-size: " + HEAD_FONT_SIZE + "; ";
	
	
	//constructor
	public LabelGenerator(String text) {
		super(text);
		setWrapText(true);
		
		// this Joystix Monospace will be the default font :O 
		setFont(new Font("Joystix Monospace",20));
	}
	
	// use this method to set to head(logo) font (head's font is Blox)
	public void setAsHeader() {
		setHeadStyle();
	}
	
	
	
	// set font :
	private void setHeadStyle() {
		setStyle(HEAD_STYLE);
	}
	
	
}
