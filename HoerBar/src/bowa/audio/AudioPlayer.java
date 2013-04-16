/**
 * 
 */
package bowa.audio;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Phillip
 *
 */
public class AudioPlayer implements Runnable{
	
	protected MediaPlayer _player = null;
	
	protected Playlist _playlist = new Playlist();
	
	public AudioPlayer(){
		new JFXPanel();
	}
	
	public Playlist getPlaylist(){
		return _playlist;
	}
	
	
	public void addFile(File file){
		_playlist.addTitle(file);
	}

	
	public void Play(){
		if(_player == null){
			Next();
		}else{
			_player.play();
		}
		
	}
	
	public void Pause(){
		_player.pause();
	}
	
	public void Next(){
		if(_player != null){
			_player.stop();
		}
		
		File file = _playlist.getNextTitle();
		
		if(file != null){
			try{
				_player = new MediaPlayer(new Media("file:///"+file.getPath().replace('\\', '/').replaceAll(" ", "%20")));
				_player.setOnEndOfMedia(this);
				_player.play();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	

	@Override
	public void run() {
		Next();		
	}

}
