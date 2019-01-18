package StationDebug;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;

import com.zou.tools.CmdMaker;
import com.zou.tools.ConfigManager;
import com.zou.tools.CrcMaker;
import com.zy.devices.SubStationManger;
import com.zy.net.UdpSender;
import com.zy.quartz.scheduler.TimeTaskScheduler;
import com.zy.views.AddStationView;
import com.zy.views.ExcuteView;
import com.zy.views.MainView;
import com.zy.views.SingleBoardCastCtrView;
import com.zy.views.StationListView;
import com.zy.views.groupconfig.GroupConfigView;
import com.zy.views.sensor.BreakerCtrView;
import com.zy.views.sensor.SenserFactory;

public class App {
	public static MainView mainView;
	public static AddStationView addStationView;
	public static GroupConfigView groupConfigView;
	public static StationListView stationListView;
	public static SingleBoardCastCtrView singleBoardCastCtrView;
	public static BreakerCtrView breakerCtrView;
	public static ExcuteView excuteView;
	public static SubStationManger stationManger = new SubStationManger();
	public static UdpSender udpSender = new UdpSender();
	public static ConfigManager configManager = new ConfigManager();
	public static final Font font = new Font("宋体", Font.BOLD, 24);
	public static final Font font18 = new Font("宋体", Font.BOLD, 16);
	public static CmdMaker cmdMaker = new CmdMaker();
	public static CrcMaker crcMaker = new CrcMaker();
	public static TimeTaskScheduler taskScheduler;
	public static SenserFactory senserFactory = new SenserFactory();
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Thread((new Runnable() {
					@Override
					public void run() {
						try {
							UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
							SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.BusinessBlackSteelSkin");
							SubstanceLookAndFeel
									.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceNoneWatermark");
							SubstanceLookAndFeel
									.setCurrentGradientPainter("org.jvnet.substance.painter.StandardGradientPainter");
							JFrame.setDefaultLookAndFeelDecorated(true);
							JDialog.setDefaultLookAndFeelDecorated(true);
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e) {
							e.printStackTrace();
						}
						taskScheduler = new TimeTaskScheduler();
						mainView = new MainView();
						addStationView = new AddStationView();
						stationListView = new StationListView();
						groupConfigView = new GroupConfigView();
						singleBoardCastCtrView = new SingleBoardCastCtrView();
						breakerCtrView = new BreakerCtrView();
						excuteView = new ExcuteView();
					}
				})).start();
			}
		});
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stationManger.AddSubStationFromConfig();
	}
}
