package Components;

import Stages.InstructionFetch_Stage;

public class RegisterFile {

    public static int[] registers = new int[16];
    public static String readRegister1 = "Null";
    public static String readRegister2 = "Null";
    public static String writeRegister;
    public static int readData1;
    public static int readData2;
    public static int writeData;


    public static void decodeInstruction(String instruction) {
        String opcode = instruction.substring(0,4);
        switch (opcode) {
            case "0000":  //ANDI
                readRegister1 = instruction.substring(4,8);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                break;
            case "0001":  //OR
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "0010":  //ADD
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "0011":  //SUB
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "0100":  //SLT
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "0101":  //MULT
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "0110":  //SRL
                readRegister1 = instruction.substring(4,8);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                break;
            case "0111":  //SLL
                readRegister1 = instruction.substring(4,8);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                break;
            case "1001":  //SW
                readRegister1 = instruction.substring(4,8);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                break;
            case "1010":  //BEQ
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "1011":  //BLT
                readRegister1 = instruction.substring(4,8);
                readRegister2 = instruction.substring(8,12);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                readData2 = registers[Integer.parseInt(readRegister2,2)];
                break;
            case "1100":  //JR
                readRegister1 = instruction.substring(4,8);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                break;
            case "1101":  //ADDI
                readRegister1 = instruction.substring(4,8);
                readData1 = registers[Integer.parseInt(readRegister1,2)];
                break;
        }
    }

    public static void setRegister(int index, int value) {
        writeData = value;
        registers[index] = writeData;
    }
}

