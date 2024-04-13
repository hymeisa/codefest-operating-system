public class MEMORY_MANAGEMENT {

    
    public static final int BYTE = 1;
    public static final int KILOBYTE = BYTE * 1000;
    public static final int MEGABYTE = KILOBYTE * 1000;
    public static final int GIGABYTE = MEGABYTE * 1000;


    private class _memory_data {
        private int _total_ram = GIGABYTE;
        
        public int _get_total_ram() {
            return this._total_ram;
        }
        public void _change_total_ram(int _new_total_ram) {
            this._total_ram = _new_total_ram;
        }
    };


    public static void main(String[] args) {
        
    }

}