package src.os.APPLICATION_LAUNCHER;

import src.os.CPU.*;
import java.lang.Thread;
import src.os.*;
import src.os.BOOTLOADER.*;

public class APPLICATION_LAUNCHER {

    public boolean startProgram(Runnable startClass) {
        return OS.cpu._start_thread((Runnable)startClass);
    }
}
