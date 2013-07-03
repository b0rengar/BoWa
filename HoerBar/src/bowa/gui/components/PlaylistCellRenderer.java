/**
 * 
 */
package bowa.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.nio.file.Paths;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.bff.javampd.objects.MPDSong;

import bowa.audio.Playlist;

/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class PlaylistCellRenderer extends DefaultListCellRenderer {
	
	
	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object obj, int arg2, boolean arg3, boolean arg4) {
		super.getListCellRendererComponent(list, obj, arg2, arg3, arg4);
		Playlist pl = (Playlist)list.getModel();
		
		MPDSong song = ((MPDSong)obj);
		MPDSong current = pl.getCurrentSong();
		
		if(song != null && song.getPosition() == current.getPosition()){
			this.setBackground(Color.ORANGE);
		}
		String filename = Paths.get(song.getFile()).getFileName().toString();
		
		String pos = ((song.getPosition()+1) <10?"  ":((song.getPosition()+1) <10?" ":"")) + (song.getPosition()+1) + ".";
		
		this.setText(pos + " [" + secondsToTime(song.getLength()) + "] " + song.getArtist() + " - " + song.getTitle() + " (" + filename + ")");
		this.setFont(new Font("SansSerif", Font.BOLD, 20));
		return this;
	}
	
	protected String secondsToTime(int seconds){
		int h = (seconds/60)/60;
		int m = (seconds/60)%60;
		int s = seconds%60;
		
		return ((h < 10)?"0":"") + h + ":" + ((m < 10)?"0":"") + m + ":" + ((s < 10)?"0":"") + s;
	}

}
