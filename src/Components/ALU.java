package Components;

public class ALU {
    public static String operation;
    public static int operand1;
    public static int operand2;
    public static int output;
    public static boolean Z_Flag;

    public static void evaluate(String Op, int Operand1, int Operand2) {
        switch (Op) {
            case "0000":  //ANDI
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 & operand2;
                Z_Flag = output == 0;
                break;
            case "0001":  //OR
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 | operand2;
                Z_Flag = output == 0;
                break;
            case "0010":  //ADD - ADDI - LW - SW
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 + operand2;
                Z_Flag = output == 0;
                break;
            case "0011":  //SUB - BEQ
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 - operand2;
                Z_Flag = output == 0;
                break;
            case "0100":  //SLT
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = (operand1 < operand2) ? 1:0;
                Z_Flag = output == 0;
                break;
            case "0101":  //MULT
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 * operand2;
                Z_Flag = output == 0;
                break;
            case "0110":  //SRL
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 / (operand2*2);
                Z_Flag = output == 0;
                break;
            case "0111":  //SLL
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = operand1 * (operand2*2);
                Z_Flag = output == 0;
                break;
            case "1000":  //BLT
                operation = Op;
                operand1 = Operand1;
                operand2 = Operand2;
                output = (operand1 < operand2) ? 0:1;
                Z_Flag = output == 0;
                break;
        }
    }
}

