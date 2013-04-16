/**
 * 
 */
package bowa.audio;

import java.io.File;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.KeyValue;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * @author Phillip 
 *
 */
public class Playlist implements ListModel<File>{
	
	protected ArrayList<ListDataListener> _listener = new ArrayList<ListDataListener>();
	
	protected ArrayList<File> _list = new ArrayList<File>();
	
	protected int _nextTitle = 0;
	
	public void addTitle(File title){
		_list.add(title);
		notifyListeners(getSize()-1, getSize()-1);
	}
	
	public File getNextTitle(){
		File f = null;
		try{
			f = _list.get(_nextTitle++);
		}
		catch(Exception e){}
		
		return f;
	}
	
	public void removeTitle(int index){
		_list.remove(index);
		notifyListeners(0, getSize()-1);
	}
	
	public void removeTitle(File title){
		_list.remove(title);
		notifyListeners(0, getSize()-1);
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
	public File getElementAt(int index) {
		return _list.get(index);
	}

	@Override
	public int getSize() {
		return _list.size();
	}
	
}
