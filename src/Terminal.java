import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Terminal extends Parser {
    Parser parser;

    //Implement each command in a method, for example:
    public void echo(String argument) {
        System.out.println(argument);
    }

    public void pwd() {
        System.out.println(getArgs());
    }

    public void cd(String[] args) {

    }

    public void ls() {

    }

    public void touch(String args) {
        String[] file; //Array that holds all the paths that are separated by a "\".
        String FileName = ""; //Holds the last name in the path.
        file = (args.split("\\"));//holds each path in a separate index.
        for (int i = 0; i < file.length; i++) {
            FileName = file[i].toString(); //To reach the final name.
        }
        File NewFile = new File(FileName);
    }

    public void cp(String args) throws IOException {
        String[] files;
        files = (args.split("\\"));
        String FirstFile = files[0];
        String SecondFile = files[1];
        FileWriter Wfile = new FileWriter(SecondFile);
        Scanner myReader = new Scanner(FirstFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Wfile.write(data);
        }
        myReader.close();
        Wfile.close();
    }

    public void rm(String FileName) {
        File file = new File(FileName);
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("Nothing to delete");
        }
    }

    public void cat(String file) throws IOException {
        FileWriter Wfile = new FileWriter(file);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Wfile.write(data);
        }
        myReader.close();
        Wfile.close();
    }

    // ... //This method will choose the suitable command method to be called
    public void chooseCommandAction() {

    }

    public static void main(String[] args) {
        try {

        } catch() {

        }
    }
}
