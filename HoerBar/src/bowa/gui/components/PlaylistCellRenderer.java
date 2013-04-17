/**
 * 
 */
package bowa.gui.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

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
		Component comp =  super.getListCellRendererComponent(list, obj, arg2, arg3, arg4);
		Playlist pl = (Playlist)list.getModel();
		
		if(obj == pl.getCurrentTitle()){
			comp.setBackground(Color.ORANGE);
		}
		
		return comp;
	}

}
