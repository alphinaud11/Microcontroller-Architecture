package Components;

public class MainControl {

    public static boolean RegDst;
    public static boolean Branch;
    public static boolean MemRead;
    public static boolean MemToReg;
    public static String ALUOp;
    public static boolean MemWrite;
    public static boolean ALUSrc;
    public static boolean RegWrite;
    public static boolean Shift;
    public static boolean MemUse;
    public static boolean Jump;

    public static void generateSignals(String opcode) {
        switch (opcode) {
            case "0000":  //ANDI
                RegDst = false;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0000";
                MemWrite = false;
                ALUSrc = true;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "0001":  //OR
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0001";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "0010":  //ADD
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0010";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "0011":  //SUB
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0011";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "0100":  //SLT
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0100";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "0101":  //MULT
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0101";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "0110":  //SRL
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0110";
                MemWrite = false;
                ALUSrc = true;
                RegWrite = true;
                Shift = true;
                MemUse = false;
                Jump = false;
                break;
            case "0111":  //SLL
                RegDst = true;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0111";
                MemWrite = false;
                ALUSrc = true;
                RegWrite = true;
                Shift = true;
                MemUse = false;
                Jump = false;
                break;
            case "1000":  //LW
                RegDst = false;
                Branch = false;
                MemRead = true;
                MemToReg = true;
                ALUOp = "0010";
                MemWrite = false;
                ALUSrc = true;
                RegWrite = true;
                Shift = false;
                MemUse = true;
                Jump = false;
                break;
            case "1001":  //SW
                RegDst = false;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0010";
                MemWrite = true;
                ALUSrc = true;
                RegWrite = false;
                Shift = false;
                MemUse = true;
                Jump = false;
                break;
            case "1010":  //BEQ
                RegDst = false;
                Branch = true;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0011";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = false;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "1011":  //BLT
                RegDst = false;
                Branch = true;
                MemRead = false;
                MemToReg = false;
                ALUOp = "1000";
                MemWrite = false;
                ALUSrc = false;
                RegWrite = false;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
            case "1100":  //JR
                RegDst = false;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "1111"; //Does nothing
                MemWrite = false;
                ALUSrc = false;
                RegWrite = false;
                Shift = false;
                MemUse = false;
                Jump = true;
                break;
            case "1101":  //ADDI
                RegDst = false;
                Branch = false;
                MemRead = false;
                MemToReg = false;
                ALUOp = "0010";
                MemWrite = false;
                ALUSrc = true;
                RegWrite = true;
                Shift = false;
                MemUse = false;
                Jump = false;
                break;
        }
    }
}

