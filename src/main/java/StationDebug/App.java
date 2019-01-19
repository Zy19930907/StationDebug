package StationDebug;

import java.awt.Font;

import javax.swing.*;

import com.zou.tools.CmdMaker;
import com.zou.tools.ConfigManager;
import com.zou.tools.CrcMaker;
import com.zy.devices.SubStationManger;
import com.zy.net.UdpSender;
import com.zy.quartz.scheduler.TimeTaskScheduler;
import com.zy.views.AddStationView;
import com.zy.views.ExcuteView;
import com.zy.views.MainView;
import com.zy.views.singleboardcast.SingleBoardCastCtrView;
import com.zy.views.StationListView;
import com.zy.views.groupconfig.GroupConfigView;
import com.zy.views.sensor.BreakerCtrView;
import com.zy.views.sensor.SenserFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

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
    public static final Font font = new Font("微软雅黑", Font.BOLD, 24);
    public static final Font font16 = new Font("微软雅黑", Font.BOLD, 16);
    public static CmdMaker cmdMaker = new CmdMaker();
    public static CrcMaker crcMaker = new CrcMaker();
    public static TimeTaskScheduler taskScheduler;
    public static SenserFactory senserFactory = new SenserFactory();

    public static void main(String[] args) {

        try {
            System.setProperty("sun.java2d.noddraw", "true");
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            //TODO exception
        }
        taskScheduler = new TimeTaskScheduler();
        mainView = new MainView();
        addStationView = new AddStationView();
        stationListView = new StationListView();
        groupConfigView = new GroupConfigView();
        singleBoardCastCtrView = new SingleBoardCastCtrView();
        breakerCtrView = new BreakerCtrView();
        excuteView = new ExcuteView();
        stationManger.AddSubStationFromConfig();
    }
}
