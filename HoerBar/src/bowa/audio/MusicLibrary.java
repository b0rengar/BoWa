/**
 * 
 */
package bowa.audio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.bff.javampd.MPD;
import org.bff.javampd.objects.MPDSong;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class MusicLibrary extends DefaultTreeModel{

	protected MPD _mpd = null;

	
	public MusicLibrary(MPD mpd){
		super(new DefaultMutableTreeNode("Music"));
		_mpd = mpd;
		update();
			
	}



	public void update(){
		try {
			_mpd.getMPDAdmin().updateDatabase();
		} catch (Exception e) {}
		
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode)getRoot();
		Iterator<Path> pathIter = null;
		Path subPath = null;
		MPDSong song = null;
				
		try {
			Iterator<MPDSong> list = _mpd.getMPDDatabase().listAllSongs().iterator();
			
			parent.removeAllChildren();
			
			while(list.hasNext()){
				song = list.next();
				pathIter = Paths.get(song.getFile()).iterator();
				parent = (DefaultMutableTreeNode)getRoot();
				
				while(pathIter.hasNext()){
					subPath = pathIter.next();
					if(pathIter.hasNext()){
						parent = appendNode(parent, new DefaultMutableTreeNode(subPath));
					}else{
						appendNode(parent, new DefaultMutableTreeNode(new SongContainer(song)));
					}
				}
				
			}
			super.reload();
		} catch (Exception e) {}
		
	}

	
	protected DefaultMutableTreeNode appendNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child){
		
		@SuppressWarnings("rawtypes")
		Enumeration e = parent.children();
		DefaultMutableTreeNode current = null;
		
		while(e.hasMoreElements()){
			current = (DefaultMutableTreeNode)e.nextElement();
			if(current.getUserObject().equals(child.getUserObject())){
				return current;
			}
		}
		parent.add(child);
		return child;
	}



}
