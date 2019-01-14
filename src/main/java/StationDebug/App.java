package StationDebug;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;

import com.zy.views.AddStationView;
import com.zy.views.MainView;


public class App 
{
	public static MainView mainView;
	public static AddStationView addStationView;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
			SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.BusinessSkin");
			SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceNoneWatermark");
			SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.StandardGradientPainter");
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Thread((new Runnable() {
					@Override
					public void run() {
						mainView = new MainView();
						addStationView = new AddStationView();
					}
				})).start();
			}
		});
	}
}
