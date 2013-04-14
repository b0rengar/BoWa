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
import java.util.concurrent.Semaphore;

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
	
	protected Semaphore _sema = new Semaphore(1);
	
	
	public SerialReader(String sourceFile) throws FileNotFoundException{
		
		_reader = new FileReader(new File(sourceFile));
		Thread t = new Thread(this);
		t.start();
		
	}
	
	public void addListener(SerialListener listener){
		try {
			_sema.acquire();
			_listeners.add(listener);
			_sema.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeListener(SerialListener listener){
		try {
			_sema.acquire();
			_listeners.remove(listener);
			_sema.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void notifyListeners(char data){
		try {
			_sema.acquire();
			Iterator<SerialListener> iter = _listeners.iterator();
			
			while(iter.hasNext()){
				iter.next().dataReceived(data);
			}
			_sema.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
