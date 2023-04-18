import java.io.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputFileName, outputFileName;
        Boolean ques;
        // створення файлових потоків
        BufferedReader reader = null;
        BufferedWriter writer = null;
        boolean x = false;
        //while throw в catch ще раз відкрити
        do{
            System.out.print("Введіть назву вхідного файлу: ");
            inputFileName = in.nextLine();
            try {
                reader = new BufferedReader(new FileReader(inputFileName));
                x = true;
            } catch (FileNotFoundException e) {
                System.out.println("Error: input file not known!");
                //System.exit(1);
        }
        }while(!x);
        System.out.print("Введіть назву вихідного файлу: ");
        outputFileName = in.nextLine();
        try {
            File tmpDir = new File(outputFileName);
            boolean exists = tmpDir.exists();
            if(exists){
            System.out.println("Do you want overwrite the file?");
            ques = in.nextBoolean();
            if(ques){
            writer = new BufferedWriter(new FileWriter(outputFileName,false));
            }
            else writer = new BufferedWriter(new FileWriter(outputFileName,true));
            }
            else {
                System.out.println("Do you want to create a file?");
                ques = in.nextBoolean();
                if(ques){
                    writer = new BufferedWriter(new FileWriter(outputFileName));
                }
                else{
                    System.exit(1);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: Could not create source file!");
            System.exit(1);
        }
        // зчитування даних та їх обробка
        Set<String> lines = new TreeSet<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
                System.out.println(line+"->"+lines);
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read data from input file!");
            System.exit(1);
        }

        // запис результатів у вихідний файл
        try {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to write data to output file!");
            System.exit(1);
        }

        System.out.println("The results were successfully written to the file " + outputFileName);
        in.close();
    }
}