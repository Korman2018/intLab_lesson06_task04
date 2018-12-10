package task04;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        PhonesConverter.convert(new File("input.txt"),new File("output.txt"));
    }
}
