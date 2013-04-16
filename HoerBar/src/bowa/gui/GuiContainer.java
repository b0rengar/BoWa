/**
 * 
 */
package bowa.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import bowa.audio.AudioPlayer;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * @author Phillip
 *
 */
public class GuiContainer extends JFrame {
	
	protected JFrame _this = null;
	
	protected AudioPlayer _player = new AudioPlayer();
	
	
	
	public GuiContainer() {
		_this = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//testcode
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser();
				jf.setFileFilter(new FileNameExtensionFilter("Audio", "mp3"));
				jf.setMultiSelectionEnabled(true);
				if(jf.showOpenDialog(_this) == JFileChooser.APPROVE_OPTION){					
					for(File f : jf.getSelectedFiles()){
						_player.addFile(f);
					}
					
					
				}
			}
		});
		
		
		getContentPane().add(btnNewButton, BorderLayout.WEST);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_player.Play();
				
			}
		});
		getContentPane().add(btnPlay, BorderLayout.NORTH);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_player.Pause();
				
			}
		});
		getContentPane().add(btnPause, BorderLayout.SOUTH);
		
		JButton btnSkip = new JButton("Next");
		btnSkip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_player.Next();
				
			}
		});
		getContentPane().add(btnSkip, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList(_player.getPlaylist());
		scrollPane.setViewportView(list);
		
		//end testcode
		
		
	}

}
