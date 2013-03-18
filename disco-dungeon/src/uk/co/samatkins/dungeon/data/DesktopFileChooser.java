package uk.co.samatkins.dungeon.data;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DesktopFileChooser extends FileChooser {

	private JFileChooser fileChooser;
	
	public DesktopFileChooser() {
		this.fileChooser = new JFileChooser();
		
		// Customise the file chooser
		this.fileChooser.setDialogTitle("Choose a song to play");
		this.fileChooser.setApproveButtonText("Play");
		this.fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 files", "mp3"));
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
