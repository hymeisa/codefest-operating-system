package src.os;

import src.os.APPLICATION_LAUNCHER.*;
import src.os.CPU.*;
import src.os.RAM.*;
import java.lang.Thread;

import src.os.gui.DesktopGUI;
import src.os.gui.*;

public class OS {
    public static CPU cpu = new CPU();
    public static MEMORY_MANAGEMENT memory_management = new MEMORY_MANAGEMENT();
    public static DesktopGUI desktop = null;
    public static APPLICATION_LAUNCHER application_launcher = new APPLICATION_LAUNCHER();

    public boolean startup() throws InterruptedException {
        cpu._start_thread((Runnable)new DesktopGUI());
        runtime();
        return true;
    }

    public void runtime() throws InterruptedException {
        while(true) {
            Thread.sleep(250);
        }
    }

    public class TaskManagerGUI {
    }
    
}
