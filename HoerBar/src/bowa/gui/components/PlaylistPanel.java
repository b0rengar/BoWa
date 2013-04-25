/**
 * 
 */
package bowa.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.bff.javampd.objects.MPDSong;

import bowa.audio.AudioPlayer;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class PlaylistPanel extends JPanel {
	
	protected AudioPlayer _player = null;

	protected JList<MPDSong> _list;

	public PlaylistPanel(AudioPlayer player) {
		
		_player = player;
		
		setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		add(separator, BorderLayout.NORTH);
		
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JButton btnUp = new JButton("\u2191");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upButtonClicked();
			}
		});
		
		JButton button_1 = new JButton("\u25B6");
		panel_2.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playButtonClicked();
			}
		});
		button_1.setPreferredSize(new Dimension(50, 50));
		button_1.setMaximumSize(new Dimension(50, 50));
		button_1.setForeground(new Color(255, 140, 0));
		button_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnUp.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_2.add(btnUp);
		btnUp.setPreferredSize(new Dimension(50, 50));
		btnUp.setMaximumSize(new Dimension(50, 50));
		btnUp.setForeground(new Color(255, 140, 0));
		btnUp.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnDown = new JButton("\u2193");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downButtonClicked();
			}
		});
		btnDown.setPreferredSize(new Dimension(50, 50));
		btnDown.setMaximumSize(new Dimension(50, 50));
		btnDown.setForeground(new Color(255, 140, 0));
		btnDown.setFont(new Font("SansSerif", Font.BOLD, 18));
		panel_2.add(btnDown);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeButtonClicked();
			}
		});
		btnX.setPreferredSize(new Dimension(50, 50));
		btnX.setMaximumSize(new Dimension(50, 50));
		btnX.setForeground(new Color(255, 0, 0));
		btnX.setFont(new Font("SansSerif", Font.BOLD, 18));
		panel_2.add(btnX);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(50, 32767));
		panel_3.setPreferredSize(new Dimension(50, 10));
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		_list = new JList<MPDSong>(_player.getPlaylist());
		_list.setCellRenderer(new PlaylistCellRenderer());
		scrollPane.setViewportView(_list);
	}
	
	
	protected void upButtonClicked(){
		if(_list.getSelectedIndex() < 1) return;
		_player.getPlaylist().swapTitle(_list.getSelectedValue(), _player.getPlaylist().getElementAt(_list.getSelectedIndex()-1));
		_list.setSelectedIndex(_list.getSelectedIndex()-1);
		
	}

	protected void downButtonClicked(){
		_player.getPlaylist().swapTitle(_list.getSelectedValue(), _player.getPlaylist().getElementAt(_list.getSelectedIndex()+1));
		_list.setSelectedIndex(_list.getSelectedIndex()+1);
	}
	
	protected void removeButtonClicked(){
		_player.getPlaylist().removeTitle(_list.getSelectedValue());
	}
	
	protected void playButtonClicked(){
		if(_list.getSelectedIndex() == -1) return;
		_player.getPlaylist().setNext(_list.getSelectedValue());
	}
	
	
	
}
