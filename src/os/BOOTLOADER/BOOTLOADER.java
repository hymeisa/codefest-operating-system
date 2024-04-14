package src.os.BOOTLOADER;

import src.os.CPU.*;
import src.os.*;

public class BOOTLOADER {

    public static OS os = new OS();

    public static void main(String[] args) throws InterruptedException {
        os.startup();
    }
}
