/**
 * 
 */
package bowa.gui.tabs;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;

import bowa.audio.AudioPlayer;
import bowa.audio.MusicLibrary;
import bowa.gui.components.AudioPlayerPanel;
import bowa.gui.components.MusicLibraryPanel;
import bowa.gui.components.PlaylistPanel;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class AudioTab extends JPanel {
	
	public AudioTab(AudioPlayer player, MusicLibrary lib) {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
		add(splitPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		AudioPlayerPanel panel_1 = new AudioPlayerPanel(player);
		panel.add(panel_1, BorderLayout.NORTH);
		
		PlaylistPanel panel_2 = new PlaylistPanel(player);
		panel.add(panel_2, BorderLayout.CENTER);
		
		MusicLibraryPanel libPanel = new MusicLibraryPanel(lib, player);
		libPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		splitPane.setLeftComponent(libPanel);
	}

}
