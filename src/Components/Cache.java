package Components;

import java.util.HashMap;

public class Cache {

    public static HashMap<String,Object>[] blocks = new HashMap[256];

    public static void fillBlocks(HashMap<String,Object>[] blocks) {
        for (int i=0; i<256; i++) {
            HashMap<String,Object> h = new HashMap<>();
            h.put("Valid",false);
            h.put("Tag",-1111);
            h.put("Data",-1);
            blocks[i] = h;
        }
    }

    public static int access(int address) {
        int index = address % 256;
        int tag = address / 256;
        int output;
        if (!(Boolean)blocks[index].get("Valid")) { //Valid not set
            output = DataMemory.read(address);
            blocks[index].replace("Valid",true);
            blocks[index].replace("Tag",tag);
            blocks[index].replace("Data",output);
        }
        else {
            int currentBlockTag = (int) blocks[index].get("Tag");
            if (tag == currentBlockTag) //Hit
                output = (int) blocks[index].get("Data");
            else { //Miss
                output = DataMemory.read(address);
                blocks[index].replace("Tag",tag);
                blocks[index].replace("Data",output);
            }
        }
        return output;
    }

    public static void update(int address, int data) {
        int index = address % 256;
        int tag = address / 256;
        if ((Boolean)blocks[index].get("Valid")) {
            int currentBlockTag = (int) blocks[index].get("Tag");
            if (tag == currentBlockTag)
                blocks[index].replace("Data",data);
        }
    }
}
