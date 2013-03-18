package uk.co.samatkins.dungeon.data;

import java.io.File;

import com.badlogic.gdx.Gdx;

public abstract class FileChooser {
	
	private static FileChooser instance;
	
	public static FileChooser getInstance() {
		if (FileChooser.instance == null) {
			switch (Gdx.app.getType()) {
			case Android:
				break;
			case Desktop:
				FileChooser.instance = new DesktopFileChooser();
				break;
			case Applet:
				break;
			case WebGL:
				break;
			case iOS:
				break;
			default:
				break;
			}
			
		}
		
		return FileChooser.instance;
	}
	
	public abstract File getFile();
}
