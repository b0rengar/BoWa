/**
 * 
 */
package bowa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.bff.javampd.MPD;

import bowa.audio.AudioPlayer;
import bowa.audio.MusicLibrary;
import bowa.gui.components.MusicLibraryPanel;
import bowa.gui.tabs.PlayerTab;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class GuiContainer extends JFrame {
	
	protected JFrame _this = null;
	
	protected AudioPlayer _player = null;

	protected MusicLibrary _lib = null;
	
	
	
	public GuiContainer(MPD mpd) {
		setTitle("Sound PI");
		setAlwaysOnTop(true);
		setUndecorated(true);
		_this = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(_this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		

		_lib = new MusicLibrary(mpd);
		_player = new AudioPlayer(mpd);

		
		JTabbedPane tabs = new JTabbedPane();
		getContentPane().add(tabs, BorderLayout.CENTER);
		tabs.addTab("<html><body><font size=25 color=#ff8c00>Player</font></body></html>", new PlayerTab(_player, _lib));
		tabs.addTab("<html><body><font size=25 color=#ff8c00>Bibliothek</font></body></html>", new MusicLibraryPanel(_lib, _player));

		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHoerbar = new JLabel("Sound PI");
		lblHoerbar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoerbar.setForeground(new Color(255, 20, 147));
		lblHoerbar.setFont(new Font("SansSerif", Font.BOLD, 45));
		panel_1.add(lblHoerbar);
		
		
		JButton btnA = new JButton("L");
		btnA.setPreferredSize(new Dimension(75, 75));
		btnA.setMaximumSize(new Dimension(75, 75));
		btnA.setForeground(new Color(0, 0, 0));
		btnA.setFont(new Font("SansSerif", Font.BOLD, 20));
		panel.add(btnA);
		
		JButton btnExit = new JButton("X");
		btnExit.setMaximumSize(new Dimension(75, 75));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeButtonClicked();
			}
		});
		btnExit.setForeground(Color.RED);
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnExit.setPreferredSize(new Dimension(75, 75));
		panel.add(btnExit);
	
	}
	
	protected void closeButtonClicked() {
		System.exit(0);
	}

}
