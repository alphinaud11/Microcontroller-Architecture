package Stages;

import Components.MainControl;
import Components.RegisterFile;
import java.util.LinkedList;
import java.util.Queue;

public class InstructionDecode_Stage {

    public static Queue<Object> decodeQ = new LinkedList<>();
    public static String signExtend;
    public static int offsetReg;

    public static void InstDecode(String instruction, int pcPlus2, int programSize) {
        ContUnit(instruction.substring(0,4));
        RegisterFile.decodeInstruction(instruction);
        if (MainControl.Branch)
            SignExtend(instruction.substring(12,16));
        else {
            if (MainControl.Shift)
                SignExtend(instruction.substring(8,12));
            else
                SignExtend(instruction.substring(8,16));
        }
        offsetReg = RegisterFile.registers[0];
        System.out.println(convertInstruction(instruction) + " in Decode stage:" + "\n");
        System.out.println("ReadData1 -> " + convertBin(RegisterFile.readData1));
        System.out.println("ReadData2 -> " + convertBin(RegisterFile.readData2));
        System.out.println("offsetReg -> " + convertBin(offsetReg));
        System.out.println("Sign Extend -> " + signExtend);
        System.out.println("Next PC -> " + convertBin(InstructionFetch_Stage.pcPlus2));
        System.out.println("Control signals ->");
        System.out.println("RegDst: " + MainControl.RegDst);
        System.out.println("Branch: " + MainControl.Branch);
        System.out.println("MemRead: " + MainControl.MemRead);
        System.out.println("MemToReg: " + MainControl.MemToReg);
        System.out.println("ALUOp: " + MainControl.ALUOp);
        System.out.println("MemWrite: " + MainControl.MemWrite);
        System.out.println("ALUSrc: " + MainControl.ALUSrc);
        System.out.println("RegWrite: " + MainControl.RegWrite);
        System.out.println("Shift: " + MainControl.Shift);
        System.out.println("MemUse: " + MainControl.MemUse);
        System.out.println("Jump: " + MainControl.Jump + "\n");
        Execute_Stage.executionQ.add(MainControl.ALUOp);
        Execute_Stage.executionQ.add(MainControl.ALUSrc);
        Execute_Stage.executionQ.add(MainControl.MemUse);
        Execute_Stage.executionQ.add(signExtend);
        Execute_Stage.executionQ.add(pcPlus2);
        Execute_Stage.executionQ.add(instruction);
        Execute_Stage.executionQ.add(MainControl.MemRead);
        Execute_Stage.executionQ.add(MainControl.MemWrite);
        Execute_Stage.executionQ.add(MainControl.Branch);
        Execute_Stage.executionQ.add(MainControl.MemToReg);
        Execute_Stage.executionQ.add(MainControl.RegWrite);
        Execute_Stage.executionQ.add(MainControl.Jump);
        Execute_Stage.executionQ.add(programSize);
        Execute_Stage.executionQ.add(MainControl.RegDst);
    }

    public static String convertInstruction(String instruction) {
        String instructionConverted = "";
        int operand1 = Integer.parseInt(instruction.substring(4,8),2);
        int operand2 = Integer.parseInt(instruction.substring(8,12),2);
        int operand3 = Integer.parseInt(instruction.substring(12,16),2);
        int last4bits = getTwosComplement(instruction.substring(12,16));
        int last8bits = getTwosComplement(instruction.substring(8,16));
        switch (instruction.substring(0,4)) {
            case "0000":  //ANDI
                instructionConverted = "(Andi $" + operand1 + ", " + last8bits + ")";
                break;
            case "0001":  //OR
                instructionConverted = "(Or $" + operand1 + ", $" + operand2 + ", $" + operand3 + ")";
                break;
            case "0010":  //ADD
                instructionConverted = "(Add $" + operand1 + ", $" + operand2 + ", $" + operand3 + ")";
                break;
            case "0011":  //SUB
                instructionConverted = "(Sub $" + operand1 + ", $" + operand2 + ", $" + operand3 + ")";
                break;
            case "0100":  //SLT
                instructionConverted = "(Slt $" + operand1 + ", $" + operand2 + ", $" + operand3 + ")";
                break;
            case "0101":  //MULT
                instructionConverted = "(Mult $" + operand1 + ", $" + operand2 + ", $" + operand3 + ")";
                break;
            case "0110":  //SRL
                instructionConverted = "(Srl $" + operand1 + ", " + operand2 + ", $" + operand3 + ")";
                break;
            case "0111":  //SLL
                instructionConverted = "(Sll $" + operand1 + ", " + operand2 + ", $" + operand3 + ")";
                break;
            case "1000":  //LW
                instructionConverted = "(Lw $" + operand1 + ", " + last8bits + ")";
                break;
            case "1001":  //SW
                instructionConverted = "(Sw $" + operand1 + ", " + last8bits + ")";
                break;
            case "1010":  //BEQ
                instructionConverted = "(Beq $" + operand1 + ", $" + operand2 + ", " + last4bits + ")";
                break;
            case "1011":  //BLT
                instructionConverted = "(Blt $" + operand1 + ", $" + operand2 + ", " + last4bits + ")";
                break;
            case "1100":  //JR
                instructionConverted = "(Jr $" + operand1 + ")";
                break;
            case "1101":  //ADDI
                instructionConverted = "(Addi $" + operand1 + ", " + last8bits + ")";
                break;
        }
        return instructionConverted;
    }

    public static void SignExtend(String bits) {
        char leftBit = bits.charAt(0);
        StringBuilder bitsBuilder = new StringBuilder(bits);
        while (bitsBuilder.length() < 16) {
            bitsBuilder.insert(0, leftBit);
        }
        signExtend = bitsBuilder.toString();
    }

    public static void ContUnit(String opcode) {
        MainControl.generateSignals(opcode);
    }

    public static String convertBin(int value) {
        StringBuilder binValue;
        if (value >= 0)
            binValue = new StringBuilder(Integer.toBinaryString(value));
        else
            binValue = new StringBuilder(findTwoscomplement(new StringBuffer(Integer.toBinaryString(value*-1))));
        while (binValue.length() < 16) {
            if (value >= 0)
                binValue.insert(0, '0');
            else
                binValue.insert(0, '1');
        }
        return binValue.toString();
    }

    public static String findTwoscomplement(StringBuffer str)
    {
        int n = str.length();
        int i;
        for (i = n-1 ; i >= 0 ; i--)
            if (str.charAt(i) == '1')
                break;
        if (i == -1)
            return "1" + str;
        for (int k = i-1 ; k >= 0; k--)
        {
            if (str.charAt(k) == '1')
                str.replace(k, k+1, "0");
            else
                str.replace(k, k+1, "1");
        }
        return str.toString();
    }

    public static int getTwosComplement(String binaryInt) {  //gets value of 2's complement
        if (binaryInt.charAt(0) == '1') {
            String invertedInt = invertDigits(binaryInt);
            int decimalValue = Integer.parseInt(invertedInt, 2);
            decimalValue = (decimalValue + 1) * -1;
            return decimalValue;
        } else {
            return Integer.parseInt(binaryInt, 2);
        }
    }

    public static String invertDigits(String binaryInt) {
        String result = binaryInt;
        result = result.replace("0", " ");
        result = result.replace("1", "0");
        result = result.replace(" ", "1");
        return result;
    }
}

