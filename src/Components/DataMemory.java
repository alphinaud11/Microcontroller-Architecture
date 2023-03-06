package Components;

public class DataMemory {
    public static int[] memory = new int[1024];

    public static int read(int address) {
        return memory[address];
    }

    public static void write(int address, int data) {
        memory[address] = data;
    }
}
