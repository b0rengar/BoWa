/**
 * Project TouchScreen
 * SerialReader.java
 */
package de.jphil.serial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * SerialReader - de.jphil.serial
 *
 * Description.
 *
 * @author Phillip Kopprasch
 * @created 08.04.2013
 */
public class SerialReader implements Runnable{
	
	
	protected InputStreamReader _reader = null;
	
	protected ArrayList<SerialListener> _listeners = new ArrayList<SerialListener>();
		
	
	public SerialReader(String sourceFile) throws FileNotFoundException{
		
		_reader = new FileReader(new File(sourceFile));
		Thread t = new Thread(this);
		t.start();
		
	}
	
	public void addListener(SerialListener listener){
		_listeners.add(listener);
	}
	
	public void removeListener(SerialListener listener){
		_listeners.remove(listener);
	}
	
	public void notifyListeners(char data){

		Iterator<SerialListener> iter = _listeners.iterator();
			
		while(iter.hasNext()){
			iter.next().dataReceived(data);
		}

	}


	@Override
	public void run() {
		while(true){
			try {
				notifyListeners((char)_reader.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		
	}
	
	

}
