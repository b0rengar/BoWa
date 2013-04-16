/**
 * 
 */
package bowa.gui.components;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Dimension;

/**
 * @author Phillip
 *
 */
public class PlaylistPanel extends JPanel {
	public PlaylistPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JCheckBox chckbxRepeated = new JCheckBox("repeat all");
		panel.add(chckbxRepeated);
		chckbxRepeated.setForeground(Color.DARK_GRAY);
		chckbxRepeated.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JButton btnUp = new JButton("\u2191");
		btnUp.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_2.add(btnUp);
		btnUp.setPreferredSize(new Dimension(50, 50));
		btnUp.setMaximumSize(new Dimension(50, 50));
		btnUp.setForeground(Color.WHITE);
		btnUp.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnDown = new JButton("\u2193");
		btnDown.setPreferredSize(new Dimension(50, 50));
		btnDown.setMaximumSize(new Dimension(50, 50));
		btnDown.setForeground(Color.WHITE);
		btnDown.setFont(new Font("SansSerif", Font.BOLD, 18));
		panel_2.add(btnDown);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(PlaylistPanel.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		button.setPreferredSize(new Dimension(50, 50));
		button.setMaximumSize(new Dimension(50, 50));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 18));
		panel_2.add(button);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(50, 32767));
		panel_3.setPreferredSize(new Dimension(50, 10));
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
	}
}
