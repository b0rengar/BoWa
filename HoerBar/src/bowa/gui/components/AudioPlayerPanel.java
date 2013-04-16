/**
 * 
 */
package bowa.gui.components;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bowa.audio.AudioPlayer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Phillip
 *
 */
public class AudioPlayerPanel extends JPanel implements ActionListener{
	
	protected JLabel _title = new JLabel(" ");
	protected JProgressBar _progress = new JProgressBar();
	protected JLabel _time = new JLabel("0:00 von 0:00");
	protected JSlider _volume = new JSlider();
	protected JButton _play = new JButton();
	protected Timer _timer = new Timer(1000, this);
	
	protected AudioPlayer _player = null;
	private final JPanel panel_2 = new JPanel();
	private final JLabel _lblVolume = new JLabel("100 %");
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	

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
		
		_play.setMaximumSize(new Dimension(50, 50));
		_play.setPreferredSize(new Dimension(50, 50));
		panel_1.add(_play);
		if(_player.isPlaying()){
			_play.setIcon(new ImageIcon(AudioPlayerPanel.class.getResource("/com/sun/webpane/sg/prism/resources/mediaPause.png")));
		}
		else{
			_play.setIcon(new ImageIcon(AudioPlayerPanel.class.getResource("/com/sun/webpane/sg/prism/resources/mediaPlay.png")));
		}
		_play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playButtonClicked();				
			}
		});
		
		
		
		JButton skip = new JButton(">>");
		skip.setMaximumSize(new Dimension(50, 50));
		skip.setPreferredSize(new Dimension(50, 50));
		panel_1.add(skip);
		skip.setFont(new Font("SansSerif", Font.BOLD, 18));
		skip.setForeground(Color.WHITE);
		skip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				skipButtonClicked();
			}
		});
		panel_2.setMaximumSize(new Dimension(32767, 50));
		
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_2.add(panel_4);
		panel_4.setPreferredSize(new Dimension(10, 50));
		panel_4.setMaximumSize(new Dimension(10, 32767));
		panel_2.add(_volume);
		_volume.setValue(100);
		_volume.setMajorTickSpacing(10);
		_lblVolume.setFont(new Font("SansSerif", Font.ITALIC, 12));
		_lblVolume.setForeground(Color.DARK_GRAY);
		
		panel_2.add(_lblVolume);
		panel_3.setMaximumSize(new Dimension(10, 32767));
		panel_3.setPreferredSize(new Dimension(10, 50));
		
		panel_2.add(panel_3);
		_volume.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				volumeChanged();
				_lblVolume.setText(_volume.getValue() + " %");
				
			}
		});
		
		
	}
	
	
	protected void playButtonClicked(){
		if(_player.isPlaying()){
			_player.Pause();
			_play.setIcon(new ImageIcon(AudioPlayerPanel.class.getResource("/com/sun/webpane/sg/prism/resources/mediaPlay.png")));
			
		}
		else{
			_player.Play();
			_play.setIcon(new ImageIcon(AudioPlayerPanel.class.getResource("/com/sun/webpane/sg/prism/resources/mediaPause.png")));
		}
	}
	
	protected void skipButtonClicked(){
		_player.Next();
		updateProgress();
	}
	
	protected void volumeChanged(){
		_player.setVolume(_volume.getValue());
	}
	
	protected void updateProgress(){
		_time.setText(secondsToTime(_player.getProgress()) + " von " + secondsToTime(_player.getDuration()));
		_progress.setMaximum(_player.getDuration());
		_progress.setValue(_player.getProgress());
		try{
			_title.setText(_player.getCurrentTitle().getName().replaceAll(".mp3", ""));
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
		updateProgress();		
	}
	
	
	
}
