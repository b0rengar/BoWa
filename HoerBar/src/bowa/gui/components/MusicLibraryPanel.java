/**
 * 
 */
package bowa.gui.components;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import bowa.audio.AudioPlayer;
import bowa.audio.MusicLibrary;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.nio.file.Path;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class MusicLibraryPanel extends JPanel implements ActionListener{
	
	
	protected JTree _tree = null;
	
	protected AudioPlayer _player = null;

	public MusicLibraryPanel(MusicLibrary lib, AudioPlayer player) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		_player = player;
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		_tree = new JTree(lib);
		scrollPane.setViewportView(_tree);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50, 32767));
		add(panel);
		
		JButton button = new JButton("");
		button.addActionListener(this);
		button.setMaximumSize(new Dimension(50, 50));
		button.setPreferredSize(new Dimension(50, 50));
		button.setIcon(new ImageIcon(MusicLibraryPanel.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		panel.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TreePath selectionPath =  _tree.getSelectionPath();
		if(selectionPath == null) return;
		
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
		
		if(selectedNode.isLeaf()){
			Object[] pathArray = selectedNode.getUserObjectPath();
			Path path = null;
			for(Object subPath : pathArray){
				if(path == null){
					path = (Path)subPath;
				}else{
					path = path.resolve((Path)subPath);
				}
			}
			System.out.println(path);
			_player.addFile(path.toFile());
			
		}
		
	}


}
