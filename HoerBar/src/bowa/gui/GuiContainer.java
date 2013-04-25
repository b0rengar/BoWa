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
import javax.swing.SwingConstants;

import org.bff.javampd.MPD;

import bowa.audio.AudioPlayer;
import bowa.audio.MusicLibrary;
import bowa.gui.tabs.AudioTab;

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
		setAlwaysOnTop(true);
		setUndecorated(true);
		_this = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(_this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		

		_lib = new MusicLibrary(mpd);
		_player = new AudioPlayer(mpd);

		
		AudioTab audioTab = new AudioTab(_player, _lib );
		getContentPane().add(audioTab, BorderLayout.CENTER);

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHoerbar = new JLabel("HoerBAR");
		lblHoerbar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoerbar.setForeground(Color.ORANGE);
		lblHoerbar.setFont(new Font("SansSerif", Font.BOLD, 45));
		panel_1.add(lblHoerbar);
		
		
		JButton btnA = new JButton("L");
		btnA.setPreferredSize(new Dimension(50, 50));
		btnA.setMaximumSize(new Dimension(50, 50));
		btnA.setForeground(new Color(0, 0, 0));
		btnA.setFont(new Font("SansSerif", Font.BOLD, 20));
		panel.add(btnA);
		
		JButton btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeButtonClicked();
			}
		});
		btnExit.setForeground(Color.RED);
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnExit.setMaximumSize(new Dimension(50, 50));
		btnExit.setPreferredSize(new Dimension(50, 50));
		panel.add(btnExit);
	
	}
	
	protected void closeButtonClicked() {
		System.exit(0);
	}

}
