/**
 * 
 */
package bowa.audio;

import org.bff.javampd.MPD;
import org.bff.javampd.MPDPlayer;
import org.bff.javampd.MPDPlayer.PlayerStatus;
import org.bff.javampd.events.PlayerChangeEvent;
import org.bff.javampd.events.PlayerChangeListener;
import org.bff.javampd.exception.MPDConnectionException;
import org.bff.javampd.exception.MPDPlayerException;
import org.bff.javampd.objects.MPDSong;

/**
 * @author Phillip
 *
 */
public class AudioPlayer implements PlayerChangeListener{

		
	protected MPDPlayer _player = null;
	
	protected MPD _mpd = null;
	
	protected Playlist _playlist = null;

	
	public AudioPlayer(MPD mpd){

		try {
			_mpd = mpd;
			_player = _mpd.getMPDPlayer();
			_player.addPlayerChangeListener(this);
			_player.setRepeat(true);	
			_playlist = new Playlist(_mpd);
		} catch (MPDConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MPDPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Playlist getPlaylist(){
		return _playlist;
	}
	
	public int getDuration(){
		try {
			return _player.getCurrentSong().getLength();
		} catch (Exception e) {}
		return 0;
	}
	
	public int getProgress(){
		try {
			return (int)_player.getElapsedTime();
		} catch (Exception e) {}
		return 0;
	}
	
	public int getVolume(){
		try {
			return _player.getVolume();
		} catch (Exception e) {}
		return 0;
	}
	
	public void setVolume(int volume){
		try {
			_player.setVolume(volume);
		} catch (Exception e){}
	}
	
	public boolean isPlaying(){
		try {
			return (_player.getStatus() == PlayerStatus.STATUS_PLAYING);
		} catch (Exception e){}
		return false;
	}
	
	public MPDSong getCurrentTitle(){
		try {
			return _player.getCurrentSong();
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	
	public void Play(){
		try{
			_player.play();
		}catch(Exception e){e.printStackTrace();}
		
	}
	
	public void Pause(){
		try {
			_player.pause();
		} catch (Exception e){e.printStackTrace();}
	}
	
	public void Next(){
		try {
			_player.playNext();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void Prev(){
		try {
			_player.playPrev();
		} catch (Exception e) {e.printStackTrace();}
		
	}
	

	@Override
	public void playerChanged(PlayerChangeEvent event) {
		_playlist.update();
		
	}

}
