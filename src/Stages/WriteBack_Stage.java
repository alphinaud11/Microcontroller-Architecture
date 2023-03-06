package Stages;

import Components.RegisterFile;

import java.util.LinkedList;
import java.util.Queue;

public class WriteBack_Stage {

    public static Queue<Object> writeBackQ = new LinkedList<>();

    public static void WriteBack(int ALUResult, int ReadData, boolean MemToReg, String instruction,
                                 boolean RegWrite, boolean RegDst) {
        String writeRegister = "";
        if (RegDst)
            writeRegister = instruction.substring(12,16);
        else
            writeRegister = instruction.substring(4,8);
        if (RegWrite) {
            if (MemToReg)
                RegisterFile.setRegister(Integer.parseInt(writeRegister,2),ReadData);
            else
                RegisterFile.setRegister(Integer.parseInt(writeRegister,2),ALUResult);
        }
        System.out.println(convertInstruction(instruction) + " in WB stage: " + "\n");
        System.out.println("write data -> " + convertBin(RegisterFile.writeData) + "\n");
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

