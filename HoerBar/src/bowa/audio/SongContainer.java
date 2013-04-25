/**
 * 
 */
package bowa.audio;

import java.nio.file.Paths;

import org.bff.javampd.objects.MPDSong;

/**
 * @author Phillip
 *
 */
public class SongContainer {

	protected MPDSong _song = null;
	
	public SongContainer(MPDSong song){
		_song = song;
	}
	
	public MPDSong getSong(){
		return _song;
	}
	
	@Override
	public String toString(){
		
		if(_song != null) return Paths.get(_song.getFile()).getFileName().toString();
		return "null";
	}
}
