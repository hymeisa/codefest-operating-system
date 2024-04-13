package RAM;

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
        int _index;

        public _mem_locations(int start, int end, int index) {
            this._start = start;
            this._end = end;
            this._index = index;
        }
        public _mem_locations(int start, int end) {
            this(start, end, -1);
        }

        private int _get_start() {
            return this._start;
        }
        private int _get_end() {
            return this._end;
        }
        private int _get_index() {
            return this._index;
        }
    }

    public class _RAM {

        private int _total_ram = GIGABYTE;
        public ArrayList<_mem_locations> _ram_data = new ArrayList<_mem_locations>();
        int _index = 0;

        public _RAM() {}
        
        public int _get_total_ram() {
            return this._total_ram;
        }
        private void _change_total_ram(int _new_total_ram) {
            this._total_ram = _new_total_ram;
        }

        private int _allocate_memory(int start, int end) {
            if(start < 0 || start > this._get_total_ram()) {
                System.out.println("HE-1RE: " + this._ram_data.size());
                return -1;
            }

            this._ram_data.add(new _mem_locations(start, end, this._index));
            this._index++;

            System.out.println("\tstart,end: " + start + "," + end);
            //System.out.println("\t\tHERE: " + this._ram_data.get(this._ram_data.size() - 1)._get_start() + ", " + this._ram_data.get(this._ram_data.size() - 1)._get_end());

            return this._index;
        }

        private boolean _deallocate_memory(int index) {            
            for(int x = 0; x < this._ram_data.size(); x++) {
                if(this._ram_data.get(x)._get_index() == index) {
                    System.out.println("\tindex removed: " + index + ", " + this._ram_data.get(x)._get_start() + ", " + this._ram_data.get(x)._get_end());
                    this._ram_data.remove(x);
                    return true;
                }
            }

            //System.out.println("\t\tHERE: " + this._ram_data.get(this._ram_data.size() - 1)._get_start() + ", " + this._ram_data.get(this._ram_data.size() - 1)._get_end());

            return false;
        }

        private boolean check_memory(_mem_locations mem_locations, int index) {
            //System.out.println("\t\t\tCHECK MEMORY " + mem_locations._get_start() + " " + mem_locations._get_end() + " " + index);
            if(mem_locations._get_start() < 0 || mem_locations._get_start() > this._total_ram) {
                return false;
            } else if(mem_locations._get_end() < 0 || mem_locations._get_end() > this._total_ram) {
                return false;
            }
            //System.out.println("INDEX: " + index);
            //System.out.println("#####" + mem_locations._get_start() + " > " + ((_mem_locations)(this._ram_data.get(index)))._get_start() + "&&" + mem_locations._get_start() + "<" + ((_mem_locations)(this._ram_data.get(index)))._get_end());
            if(mem_locations._get_start() > ((_mem_locations)(this._ram_data.get(index)))._get_start() && mem_locations._get_start() < ((_mem_locations)(this._ram_data.get(index)))._get_end()) {
                return false;
            } else if(mem_locations._get_end() > ((_mem_locations)(this._ram_data.get(index)))._get_start() && mem_locations._get_end() < ((_mem_locations)(this._ram_data.get(index)))._get_end()) {
                return false;
            }
    
            return true;
        }

    };
/*
    public int allocate_memory(int _size_to_allocate) {

        if(RAM._ram_data.size() == 0) {
            return RAM._allocate_memory(0, _size_to_allocate - 1);
        }

        for(int x = 0; x < RAM._ram_data.size() + 1; x++) {
            if(RAM._ram_data.size() == x) {
                return RAM._allocate_memory(RAM._ram_data.get(x - 1)._get_end() + 1, RAM._ram_data.get(x - 1)._get_end() + _size_to_allocate + x);
            }
            //System.out.println("size(): " + RAM._ram_data.size());
            if(RAM.check_memory(new _mem_locations((int)RAM._ram_data.get(x)._get_end() + 1, (int)RAM._ram_data.get(x)._get_end() + _size_to_allocate), x) == true) {
                System.out.println((RAM._ram_data.get(x)._get_end() + 1) + " | " + (RAM._ram_data.get(x)._get_end() + _size_to_allocate));
                if(x + 1 != RAM._ram_data.size()) {
                    if(RAM.check_memory(new _mem_locations((int)RAM._ram_data.get(x+1)._get_end() + 1, (int)RAM._ram_data.get(x+1)._get_end() + _size_to_allocate), x+1) == true) {
                        //System.out.println(":LSDIGHPSDGHJSPDLGJ: " + x);
                        return RAM._allocate_memory(RAM._ram_data.get(x+1)._get_end() + 1, RAM._ram_data.get(x+1)._get_end() + _size_to_allocate);        
                    }
                }
            }
        }
        System.out.println("FAILED");
        return -1;
    }
*/

private boolean isOverlap(int newStart, int newEnd) {
    for (_mem_locations block : RAM._ram_data) {
        if (newStart < block._get_end() && newEnd > block._get_start()) {
            return true;  // There's an overlap
        }
    }
    return false;
}

public int allocate_memory(int _size_to_allocate) {
    if (_size_to_allocate > RAM._get_total_ram()) return -1;  // Check if the size is more than available RAM

    if (RAM._ram_data.isEmpty()) {
        return RAM._allocate_memory(0, _size_to_allocate - 1);
    }

    int lastEnd = 0;
    for (_mem_locations block : RAM._ram_data) {
        if (block._get_start() > lastEnd) {
            if (!isOverlap(lastEnd, lastEnd + _size_to_allocate - 1)) {
                return RAM._allocate_memory(lastEnd, lastEnd + _size_to_allocate - 1);
            }
        }
        lastEnd = block._get_end() + 1;
    }

    // Check after the last block
    if (lastEnd + _size_to_allocate - 1 < RAM._get_total_ram()) {
        return RAM._allocate_memory(lastEnd, lastEnd + _size_to_allocate - 1);
    }

    System.out.println("FAILED");
    return -1;
}
    public boolean deallocate_memory(int index) {
        return RAM._deallocate_memory(index);
    }
/*
    public static void main(String[] args) {
        MEMORY_MANAGEMENT tester = new MEMORY_MANAGEMENT();
        RAM._change_total_ram(BYTE * 80);
        System.out.println("Total Ram: " + RAM._get_total_ram());
        System.out.println("Allocation Index1: " + tester.allocate_memory(20));
        System.out.println("Allocation Index2: " + tester.allocate_memory(20));
        System.out.println("Allocation Index3: " + tester.allocate_memory(20));
        System.out.println("Allocation Index4: " + tester.allocate_memory(30));
        System.out.println("Deallocated: " + tester.deallocate_memory(2));
        System.out.println("Deallocated: " + tester.deallocate_memory(3));
        System.out.println("Allocation Index5: " + tester.allocate_memory(10));
        System.out.println("Allocation Index6: " + tester.allocate_memory(10));
        System.out.println("Allocation Index7: " + tester.allocate_memory(10));
        System.out.println("Deallocated: " + tester.deallocate_memory(1));
        System.out.println("Allocation Index7: " + tester.allocate_memory(10));
        for(int x = 0; x < RAM._ram_data.size(); x++) {
            System.out.println("RAM: " + RAM._ram_data.get(x)._get_start() + "," + RAM._ram_data.get(x)._get_end() + "," + RAM._ram_data.get(x)._get_index());
        }


    }*/

}