package Components;

import java.util.ArrayList;

public class HarvardCPU {

    public static String fetchInstruction(int pc) { //Instruction Memory
        return InstructionMemory.fetchInstruction(pc);
    }

    public static void loadProgram(ArrayList<String> program) { //Instruction Memory
        InstructionMemory.loadProgram(program);
    }

    public static int readData(int address) { //Data Memory
        return Cache.access(address);
    }

    public static void writeData(int address, int data) { //Data Memory
        DataMemory.write(address,data);
        Cache.update(address,data);
    }
}
