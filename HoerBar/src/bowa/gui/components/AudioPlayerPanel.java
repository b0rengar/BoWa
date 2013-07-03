/**
 * 
 */
package bowa.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.bff.javampd.objects.MPDSong;

import bowa.audio.AudioPlayer;


/**
 * @author Phillip
 *
 */
@SuppressWarnings("serial")
public class AudioPlayerPanel extends JPanel implements ActionListener{
	
	protected JLabel _title = new JLabel(" ");
	protected JProgressBar _progress = new JProgressBar();
	protected JLabel _time = new JLabel("00:00:00 von 00:00:00");
	protected JSlider _volume = new JSlider();
	protected JButton _play = new JButton();
	protected Timer _timer = new Timer(1000, this);
	
	protected AudioPlayer _player = null;
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel _lblVolume = new JLabel("0 %");
	
	

	public AudioPlayerPanel(AudioPlayer player) {
		_player = player;
		_timer.start();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		_title.setHorizontalAlignment(SwingConstants.LEFT);
		_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(_title);
		_title.setForeground(new Color(64, 64, 64));
		_title.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		_progress.setMaximumSize(new Dimension(32767, 50));
		_progress.setPreferredSize(new Dimension(150, 30));
		panel.add(_progress);
		_time.setHorizontalAlignment(SwingConstants.LEFT);
		_time.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		panel.add(_time);
		_time.setFont(new Font("SansSerif", Font.ITALIC, 12));
		_time.setForeground(Color.DARK_GRAY);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		JButton back = new JButton("\u00AB");
		back.setMinimumSize(new Dimension(75, 75));
		back.setPreferredSize(new Dimension(75, 75));
		back.setMaximumSize(new Dimension(75, 75));
		back.setForeground(new Color(255, 140, 0));
		back.setFont(new Font("SansSerif", Font.BOLD, 18));
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonClicked();				
			}
		});
		
		panel_1.add(back);
		_play.setMinimumSize(new Dimension(75, 75));
		_play.setFont(new Font("SansSerif", Font.BOLD, 18));
		_play.setForeground(new Color(255, 140, 0));
		
		_play.setMaximumSize(new Dimension(75, 75));
		_play.setPreferredSize(new Dimension(75, 75));
		panel_1.add(_play);
		if(_player.isPlaying()){
			_play.setText("\u25AE\u25AE");
		}
		else{
			_play.setText("\u25B6");
		}
		_play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playButtonClicked();				
			}
		});
		
		
		
		JButton skip = new JButton("\u00BB");
		skip.setMinimumSize(new Dimension(75, 75));
		skip.setMaximumSize(new Dimension(75, 75));
		skip.setPreferredSize(new Dimension(75, 75));
		panel_1.add(skip);
		skip.setFont(new Font("SansSerif", Font.BOLD, 18));
		skip.setForeground(new Color(255, 140, 0));
		skip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				skipButtonClicked();
			}
		});
		panel_2.setMaximumSize(new Dimension(32767, 50));
		
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_4.setMaximumSize(new Dimension(40, 32767));
		panel_2.add(panel_4);
		panel_4.setPreferredSize(new Dimension(40, 50));
		panel_4.setLayout(new BorderLayout(0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_4.add(lblNewLabel);
		panel_2.add(_volume);
		_volume.setValue(_player.getVolume());
		_volume.setMajorTickSpacing(10);
		
		panel_3.setMaximumSize(new Dimension(40, 32767));
		panel_3.setPreferredSize(new Dimension(40, 50));
		
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		_lblVolume.setHorizontalAlignment(SwingConstants.CENTER);
		_lblVolume.setForeground(Color.DARK_GRAY);
		_lblVolume.setFont(new Font("SansSerif", Font.ITALIC, 12));
		_lblVolume.setText(_volume.getValue() + " %");
		
		panel_3.add(_lblVolume);
		_volume.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				volumeChanged();
			}
		});
		
		
	}
	
	
	protected void playButtonClicked(){
		if(_player.isPlaying()){
			_player.Pause();
			_play.setText("\u25B6");
			
		}
		else{
			_player.Play();
			_play.setText("\u25AE\u25AE");
		}
	}
	
	protected void skipButtonClicked(){
		_player.Next();
		update();
	}
	
	protected void backButtonClicked(){
		_player.Prev();
		update();
	}
	
	protected void volumeChanged(){
		_player.setVolume(_volume.getValue());
		_lblVolume.setText(_volume.getValue() + " %");
	}
	
	protected void update(){
		_time.setText(secondsToTime(_player.getProgress()) + " von " + secondsToTime(_player.getDuration()));
		_progress.setMaximum(_player.getDuration());
		_progress.setValue(_player.getProgress());
		_volume.setValue(_player.getVolume());
		_lblVolume.setText(_volume.getValue() + " %");
		
		_player.getPlaylist().update();
		
		if(_player.isPlaying()){
			_play.setText("\u25AE\u25AE");
		}
		else{
			_play.setText("\u25B6");
		}
		try{
			MPDSong song = _player.getCurrentTitle();
			String filename = Paths.get(song.getFile()).getFileName().toString();
			_title.setText(song.getArtist() + " - " + song.getTitle() + " (" + filename + ")");
		}catch(Exception e){}
		
	}
	
	
	protected String secondsToTime(int seconds){
		int h = (seconds/60)/60;
		int m = (seconds/60)%60;
		int s = seconds%60;
		
		return ((h < 10)?"0":"") + h + ":" + ((m < 10)?"0":"") + m + ":" + ((s < 10)?"0":"") + s;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		update();		
	}
	
	
	
}
