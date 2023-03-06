import Components.*;
import Stages.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Computer {

    public static int programSize = 0;
    public static int clockCycle = 1;

    public static void run(ArrayList<String> program)
    {
        Cache.fillBlocks(Cache.blocks);
        programSize = program.size();
        HarvardCPU.loadProgram(program);
        for(int i = 0; i < (programSize * 2) - 1; i +=2)
        {
            InstructionFetch_Stage.fetchQ.add(i);
            InstructionFetch_Stage.fetchQ.add(programSize);
        }
        runHelper();
    }

    public static void runHelper()
    {
        System.out.println(">>Program started.\n");
        while(!InstructionFetch_Stage.fetchQ.isEmpty() || !InstructionDecode_Stage.decodeQ.isEmpty() || !Execute_Stage.executionQ.isEmpty()
        || !MemoryAccess_Stage.memAccQ.isEmpty() || !WriteBack_Stage.writeBackQ.isEmpty())
        {
            System.out.println("<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
            System.out.println("After clock-cycle #" + clockCycle++ + "\n");
            boolean fetchFlag = !InstructionFetch_Stage.fetchQ.isEmpty();
            boolean decodeFlag = !InstructionDecode_Stage.decodeQ.isEmpty();
            boolean executeFlag = !Execute_Stage.executionQ.isEmpty();
            boolean memAccessFlag = !MemoryAccess_Stage.memAccQ.isEmpty();
            boolean writeBackFlag = !WriteBack_Stage.writeBackQ.isEmpty();
            int ALUResult = 0;
            boolean ZeroFlag = false;
            int BranchAddressResult = 0;
            boolean MemWrite = false;
            String instruction = "";
            // Variables to print data memory changes
            if (memAccessFlag) {
                ALUResult = (Integer) MemoryAccess_Stage.memAccQ.remove();
                ZeroFlag = (Boolean) MemoryAccess_Stage.memAccQ.remove();
                BranchAddressResult = (Integer) MemoryAccess_Stage.memAccQ.remove();
                MemWrite = (Boolean) MemoryAccess_Stage.memAccQ.remove();
                instruction = (String) MemoryAccess_Stage.memAccQ.remove();
            }
            if(fetchFlag)
            {
                InstructionFetch_Stage.InstFetch(InstructionFetch_Stage.fetchQ.remove(),
                        InstructionFetch_Stage.fetchQ.remove()
                );
            }
            if(decodeFlag)
            {
                InstructionDecode_Stage.InstDecode((String) InstructionDecode_Stage.decodeQ.remove(),
                        (Integer) InstructionDecode_Stage.decodeQ.remove(),
                        (Integer) InstructionDecode_Stage.decodeQ.remove()
                );
            }
            if(executeFlag)
            {
                Execute_Stage.Execute(
                        (String) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (String) Execute_Stage.executionQ.remove(),
                        (Integer) Execute_Stage.executionQ.remove(),
                        (String) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove(),
                        (Integer) Execute_Stage.executionQ.remove(),
                        (Boolean) Execute_Stage.executionQ.remove()
                );
            }
            if(memAccessFlag)
            {
                MemoryAccess_Stage.MemAccess(ALUResult,
                        ZeroFlag,
                        BranchAddressResult,
                        MemWrite,
                        instruction,
                        (Boolean) MemoryAccess_Stage.memAccQ.remove(),
                        (Boolean) MemoryAccess_Stage.memAccQ.remove(),
                        (Boolean) MemoryAccess_Stage.memAccQ.remove(),
                        (Boolean) MemoryAccess_Stage.memAccQ.remove(),
                        (Boolean) MemoryAccess_Stage.memAccQ.remove(),
                        (Integer) MemoryAccess_Stage.memAccQ.remove(),
                        (Boolean) MemoryAccess_Stage.memAccQ.remove()
                );
            }
            if(writeBackFlag)
            {
                WriteBack_Stage.WriteBack((Integer) WriteBack_Stage.writeBackQ.remove(),
                        (Integer) WriteBack_Stage.writeBackQ.remove(),
                        (Boolean) WriteBack_Stage.writeBackQ.remove(),
                        (String) WriteBack_Stage.writeBackQ.remove(),
                        (Boolean) WriteBack_Stage.writeBackQ.remove(),
                        (Boolean) WriteBack_Stage.writeBackQ.remove()
                );
            }
            System.out.println("\n>>Registers: \n" + Arrays.toString(RegisterFile.registers));
            if (MemWrite)
                System.out.println(">>Data memory change:\nAddress:" + ALUResult + "\nNew value: " + RegisterFile.registers[Integer.parseInt(instruction.substring(4,8),2)] + "\n");
            else
                System.out.println(">>No data memory changes.\n");
        }
        System.out.println(">>Program finished.");
    }

    public static void main(String[] args) {

        DataMemory.write(0,15);

        ArrayList<String> program = new ArrayList<>();
        program.add("1101000000000101"); //ADDI $0, 5
        program.add("1000000100000000"); //LW $1, 0
        program.add("0010001000110100"); //ADD $2, $3, $4
        program.add("0001010101100111"); //OR $5, $6, $7
        program.add("1001100100000000"); //SW $9, 0
        program.add("1010100010100111"); //BEQ $8, $10, HEY

        run(program);
    }
}

