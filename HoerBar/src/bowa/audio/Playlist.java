/**
 * 
 */
package bowa.audio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.bff.javampd.MPD;
import org.bff.javampd.exception.MPDConnectionException;
import org.bff.javampd.exception.MPDPlayerException;
import org.bff.javampd.exception.MPDPlaylistException;
import org.bff.javampd.objects.MPDSong;

/**
 * @author Phillip 
 *
 */
public class Playlist implements ListModel<MPDSong>{
	
	protected ArrayList<ListDataListener> _listener = new ArrayList<ListDataListener>();
	
	
	protected MPD _mpd = null;
	
	protected List<MPDSong> _sList = null;


	public Playlist(MPD mpd) {
		_mpd = mpd;
		update();
	}
	
	public void update(){
		try {
			_sList = _mpd.getMPDPlaylist().getSongList();
		} catch (MPDPlaylistException e) {
			e.printStackTrace();
		} catch (MPDConnectionException e) {
			e.printStackTrace();
		}
		notifyListeners(0, _sList.size());
	}
	
	public MPDSong getCurrentSong(){
		try {
			return _mpd.getMPDPlayer().getCurrentSong();
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public void setNext(MPDSong song){
		try {
			_mpd.getMPDPlayer().playId(song);
			update();
		} catch (MPDPlayerException e) {
			e.printStackTrace();
		} catch (MPDConnectionException e) {
			e.printStackTrace();
		}
	}
	
	public void addTitle(MPDSong song){
		try {
			_mpd.getMPDPlaylist().addSong(song);
			update();
		} catch (MPDPlaylistException e) {
			e.printStackTrace();
		} catch (MPDConnectionException e) {
			e.printStackTrace();
		}
	}
	
	public void removeTitle(MPDSong song){
		try {
			_mpd.getMPDPlaylist().removeSong(song);
			update();
		} catch (MPDPlaylistException e) {
			e.printStackTrace();
		} catch (MPDConnectionException e) {
			e.printStackTrace();
		}
	}
	
	public void swapTitle(MPDSong song1, MPDSong song2){
		try {
			_mpd.getMPDPlaylist().swap(song1, song2);
			update();
		} catch (MPDPlaylistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MPDConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void notifyListeners(int index0, int index1){
		
		Iterator<ListDataListener> iter = _listener.iterator();
		
		while(iter.hasNext()){
			iter.next().contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index0, index1));
		}
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		_listener.add(l);
		
	}
	
	@Override
	public void removeListDataListener(ListDataListener l) {
		_listener.remove(l);
		
	}

	
	@Override
	public MPDSong getElementAt(int index) {
		return _sList.get(index);
	}

	@Override
	public int getSize() {
		return _sList.size();
	}
	
}
