package Components;
import java.util.ArrayList;

public class InstructionMemory {

    public static String[] memory = new String[1024];  //Byte addressing

    public static void loadProgram(ArrayList<String> program) {
        int i = 0;
        for (String s : program) {
            memory[i++] = s.substring(0,8);   //1st byte
            memory[i++] = s.substring(8,16);  //2nd byte
        }
    }

    public static String fetchInstruction(int pc) {
        return memory[pc] + memory[pc+1];
    }

    public static int end() {
        int endIndex = memory.length;
        for (int i=0; i<memory.length; i++) {
            if (memory[i].equals("Null")) {
                endIndex = i;
                break;
            }
        }
        return endIndex;
    }

}

