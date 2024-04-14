package src.os.CPU;

import java.lang.Thread;

public class CPU {
    private int _num_cores = 4;
    public CPU() {
        this._num_cores -= 1;
    }

    public boolean _start_thread(Runnable test) {
        if(_num_cores > 0) {
            Thread tester = new Thread(test);
            tester.start();
            return true;
        }

        return false;
    }

    public int _get_num_cores() {
        return this._num_cores;
    }
}
