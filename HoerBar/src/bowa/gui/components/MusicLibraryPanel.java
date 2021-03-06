/**
 * 
 */
package bowa.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import bowa.audio.AudioPlayer;
import bowa.audio.MusicLibrary;
import bowa.audio.SongContainer;
import javax.swing.ScrollPaneConstants;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class MusicLibraryPanel extends JPanel{
	
	
	protected JTree _tree = null;
	
	protected MusicLibrary _lib = null;
	
	protected AudioPlayer _player = null;

	public MusicLibraryPanel(MusicLibrary lib, AudioPlayer player) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		_player = player;
		_lib = lib;
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50, 32767));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		
		JButton btnUpdateLibrary = new JButton("\u21BA");
		btnUpdateLibrary.setMinimumSize(new Dimension(75, 75));
		btnUpdateLibrary.setPreferredSize(new Dimension(75, 75));
		btnUpdateLibrary.setMaximumSize(new Dimension(75, 75));
		btnUpdateLibrary.setForeground(new Color(255, 140, 0));
		btnUpdateLibrary.setFont(new Font("SansSerif", Font.BOLD, 17));
		btnUpdateLibrary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_lib.update();
			}
		});
		panel.add(btnUpdateLibrary);
		
		
		JButton btnAddToPlaylist = new JButton("+");
		btnAddToPlaylist.setMinimumSize(new Dimension(75, 75));
		btnAddToPlaylist.setMaximumSize(new Dimension(75, 75));
		btnAddToPlaylist.setPreferredSize(new Dimension(75, 75));
		btnAddToPlaylist.setForeground(new Color(255, 140, 0));
		btnAddToPlaylist.setFont(new Font("SansSerif", Font.PLAIN, 30));
		btnAddToPlaylist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonAddToPlaylistClicked();
			}
		});
		panel.add(btnAddToPlaylist);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(50, 0));
		
		_tree = new JTree(lib);
		scrollPane.setViewportView(_tree);
		_tree.setCellRenderer(new LibraryCellRenderer());
	}

	public void buttonAddToPlaylistClicked() {
		TreePath selectionPath =  _tree.getSelectionPath();
		if(selectionPath == null) return;
		
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
		
		if(selectedNode.isLeaf() && selectedNode.getUserObject().getClass() == SongContainer.class){
			_player.getPlaylist().addTitle(((SongContainer)selectedNode.getUserObject()).getSong());
		}
		
	}


}
