import java.util.*;

//import MEMORY_MANAGEMENT._RAM._mem_locations;

public class MEMORY_MANAGEMENT {

    static _RAM RAM;

    public MEMORY_MANAGEMENT() {
        RAM = new _RAM();
    }
    
    public static final int BYTE = 1;
    public static final int KILOBYTE = BYTE * 1000;
    public static final int MEGABYTE = KILOBYTE * 1000;
    public static final int GIGABYTE = MEGABYTE * 1000;


    public class _mem_locations{
        int _start;
        int _end;

        public _mem_locations(int start, int end) {
            this._start = start;
            this._end = end;
        }

        private int _get_start() {
            return this._start;
        }
        private int _get_end() {
            return this._end;
        }
    }

    public class _RAM {

        private int _total_ram = GIGABYTE;
        private ArrayList<_mem_locations> _ram_data = new ArrayList<_mem_locations>();

        public _RAM() {}
        
        public int _get_total_ram() {
            return this._total_ram;
        }
        private void _change_total_ram(int _new_total_ram) {
            this._total_ram = _new_total_ram;
        }

        private boolean _allocate_memory(int start, int end) {
            if(start < 0 || start > this._get_total_ram()) {
                return false;
            }

            this._ram_data.add(new _mem_locations(start, end));

            return true;
        }

        private boolean check_memory(_mem_locations mem_locations, int index) {
            if(mem_locations._get_start() < 0 || mem_locations._get_start() > this._total_ram) {
                return false;
            } else if(mem_locations._get_end() < 0 || mem_locations._get_end() > this._total_ram) {
                return false;
            }

            if(mem_locations._get_start() > ((_mem_locations)(this._ram_data.get(index)))._get_start() && mem_locations._get_start() < ((_mem_locations)(this._ram_data.get(index)))._get_end()) {
                return false;
            } else if(mem_locations._get_end() > ((_mem_locations)(this._ram_data.get(index)))._get_start() && mem_locations._get_end() < ((_mem_locations)(this._ram_data.get(index)))._get_end()) {
                return false;
            }
    
            return true;
        }

    };

    public boolean allocate_memory(int _size_to_allocate) {

        for(int x = 0; x < RAM._ram_data.size(); x++) {
            if(RAM.check_memory(new _mem_locations((int)RAM._ram_data.get(x)._get_start() + 1, (int)RAM._ram_data.get(x)._get_start() + _size_to_allocate + 1), x) == true) {
                RAM._allocate_memory(RAM._ram_data.get(x)._get_start() + 1, RAM._ram_data.get(x)._get_start() + _size_to_allocate + x + 1);
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        
        System.out.println("Total Ram: " + RAM._get_total_ram());

    }

}