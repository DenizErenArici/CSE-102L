import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class Ex4_20220808040 {
    public static void main(String[] args) {

    }
}
class Computer {
    Computer() {
    }

    ;
    public CPU cpu;
    public RAM ram;

    public Computer(CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public void run() {
        int total = 0;
        int capacity = ram.getCapacity();

        for (int i = 0; i < capacity; i++) {
            int value = ram.getValue(i, i);
            total += value;
        }
        cpu.compute(0, 0);

        ram.setValue(0, 0, total);


    }

    @Override
    public String toString() {
        return "Computer: " + cpu + "" + ram;
    }
}

class CPU {
    CPU() {
    }

    ;
    private String name;
    private double clock;

    public CPU(String name, double clock) {
        this.name = name;
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public double getClock() {
        return clock;
    }

    public int compute(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "CPU: " + getName() + "" + getClock() + "Ghz";
    }
}

class RAM {
    RAM() {
    }

    ;
    private String type;
    private int capacity;
    private int[][] memory;

    public RAM(String type, int capacity) {
        this.initMemory();
        this.type = type;
        this.capacity = capacity;

    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    private void initMemory() {
        memory = new int[capacity][capacity];

        Random rand = new Random();

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                memory[i][j] = rand.nextInt(11);
            }
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < memory.length && j >= 0 && j < memory.length;
    }

    public int getValue(int i, int j) {
        if (check(i, j) == true) {
            return memory[i][j];
        }
        return -1;
    }

    public void setValue(int i, int j, int value) {
        if (check(i, j))
            memory[i][j] = value;
    }

    @Override
    public String toString() {
        return "RAM: " + getType() + "" + getCapacity() + "GB";
    }
}

class Laptop extends Computer {
    Laptop() {
    }

    ;
    private int milliAmp;
    private int battery;

    public Laptop(CPU cpu, RAM ram, int milliAmp) {
        super(cpu, ram);
        this.milliAmp = milliAmp;
        battery = milliAmp * 30 / 100;
    }

    public int batteryPercentage() {
        return (battery * 100) / (milliAmp * 30 / 100);
    }

    public void charge() {
        int i = 0;
        while (batteryPercentage() < 90) {
            battery += (milliAmp * 2 / 100);
        }
        if (batteryPercentage() >= 90) {
            battery = milliAmp * 90 / 100;
        }
    }

    public void run() {
        if (batteryPercentage() < 5)
            charge();
        else {
            battery -= milliAmp * 3 / 100;
            super.run();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "" + battery;
    }
}

class Desktop extends Computer {
    Desktop() {
    }

    ;
    private ArrayList<String> peripherals;

    public Desktop(CPU cpu, RAM ram, String... peripherals) {
        super(cpu, ram);
        this.peripherals = new ArrayList<String>(Arrays.asList(peripherals));

    }

    @Override
    public void run() {
        int total = 0;
        int sizeOfRam = ram.getCapacity();
        for (int i = 0; i < sizeOfRam; i++) {
            for (int j = 0; j < sizeOfRam; j++) {
                total += cpu.compute(total, ram.getValue(i, j));
            }
        }
    }

    public void plugIn(String peripheral) {
        peripherals.add(peripheral);
    }

    public String plugOut() {
        if (peripherals.isEmpty()) {
            return null;
        }
        return peripherals.remove(peripherals.size() - 1);
    }

    public String plugOut(int index) {
        if (peripherals.isEmpty()) {
            return null;
        }
        return peripherals.remove(index);
    }

    @Override
    public String toString() {
        String space = "";
        for (String peripheral : peripherals) {
            space += peripheral + " ";
        }
        return super.toString() + " " + space;
    }
}