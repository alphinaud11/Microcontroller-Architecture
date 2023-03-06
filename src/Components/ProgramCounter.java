package Components;

public class ProgramCounter {

    public static int value = 0;

    public static void setValue(int newValue) {
        value = newValue;
    }

    public static void increment() {
        setValue(value + 2);
    }
}

