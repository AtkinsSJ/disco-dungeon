package uk.co.samatkins.dungeon.data;

import java.io.File;

import javax.swing.JFileChooser;

public class DesktopFileChooser extends FileChooser {

	private JFileChooser fileChooser;
	
	public DesktopFileChooser() {
		this.fileChooser = new JFileChooser();
	}
	
	@Override
	public File getFile() {
		int result = this.fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			return this.fileChooser.getSelectedFile();
		} else {
			return null;
		}
	}

}
