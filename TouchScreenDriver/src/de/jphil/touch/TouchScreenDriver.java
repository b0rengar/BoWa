/**
 * Project MeterPi
 * TouchScreenDriver.java
 */
package de.jphil.touch;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jphil.serial.SerialListener;
import de.jphil.serial.SerialReader;

/**
 * TouchScreenDriver - de.jphil.touch
 *
 * Description.
 *
 * @author Phillip Kopprasch
 * @created 03.04.2013
 */
public class TouchScreenDriver implements SerialListener{
	
	protected Semaphore _sema = new Semaphore(1,true);
	
	protected  StringBuilder _buffer = new StringBuilder();
	
	protected Robot _mouse = null;

	protected Pattern _pattern = Pattern.compile("(\\d)+x(\\d)+\\s");
	
	protected Point[] _callibration = {new Point(265,743), new Point(328,658)};
	
	protected Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	protected double _xFactor = _screenSize.width / (double)(_callibration[0].y - _callibration[0].x);

	protected double _yFactor = _screenSize.height / (double)(_callibration[1].y - _callibration[1].x);
	
	protected boolean _touched = false;
	
	public static void main(String[] args){
		
		try {
			new TouchScreenDriver(new SerialReader("/dev/ttyAMA0"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private TouchScreenDriver(SerialReader reader){
		try {
			
			_mouse = new Robot();
			reader.addListener(this);
			
			
			
			
		
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void dataReceived(char data) {
		_buffer.append(data);
		if(data == ' '){
			checkBuffer();
		}	
	}
		
	protected void checkBuffer(){
		int touch = _buffer.indexOf("Touch");
		int end = _buffer.indexOf("End");
		Matcher m = _pattern.matcher(_buffer.toString());
		
		
		
		if(touch != -1){
			//System.out.println("Touch");
			_buffer.delete(0, touch+ "Touch".length() + 1);
			_touched = true;
		}
		
		if (m.find()) {
			_buffer.delete(0, m.start()+ m.group().length() + 1);
			//System.out.println("--> " + m.group());
			
			String[] coords = m.group().trim().split("x");
					
			
			
			//transform the coordinates
			
			int x = (int)((Integer.parseInt(coords[0]) - _callibration[0].x) * _xFactor) ;
			if(x < 0) x = 0; if(x >= _screenSize.width) x = _screenSize.width-1;
			
			int y = (int)((Integer.parseInt(coords[1]) - _callibration[1].x) * _yFactor) ;
			if(y < 0) y = 0; if(y >= _screenSize.height) y = _screenSize.height-1;
			
			_mouse.mouseMove(x, y);
			if(_touched){
				_mouse.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				_touched = false;
			}
		}
		
		
		if(end != -1){
			//System.out.println("End");
			_buffer.delete(0, end + "End".length() + 1);
			_mouse.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			_touched = false;
		}
	}


}
