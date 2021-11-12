import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Terminal extends Parser {
    public Parser parser;

    Terminal() {
        parser = new Parser();
    }

    //Implement each command in a method, for example:
    public void echo(String[] argument) { ;

        for (int i = 0 ; i<argument.length;i++){
            System.out.print(argument[i]);
            if(i==argument.length-1){
                break;
            }
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void pwd() {
        Path currentDir = Paths.get("");
        System.out.println(currentDir.toAbsolutePath().toString());
        System.out.print("\n");
    }

    public void cd(String[] args) {

    }

    public void ls(File currList) {
        File[] filesList = currList.listFiles();
        for (File file : filesList) {
            if (file.isDirectory())
                System.out.print(file.getName() + "\t");
            if (file.isFile()) {
                System.out.println(file.getName() + "\t");
            }
        }
    }

    public void lsReverse(File currList) {
        File[] filesList = currList.listFiles();

        for (int i = filesList.length - 1; i >= 0; i--) {


        }

        for (File file : filesList) {
            if (file.isDirectory())

                System.out.print(file.getName() + "\t");
            if (file.isFile()) {
                System.out.println(file.getName() + "\t");
            }
        }
    }

    public void touch(String args) {
        String[] file; //Array that holds all the paths that are separated by a "\".
        String FileName = ""; //Holds the last name in the path.
        file = (args.split(""));//holds each path in a separate index.
        for (int i = 0; i < file.length; i++) {
            FileName = file[i].toString(); //To reach the final name.
        }
        File NewFile = new File(FileName);
    }

    public void cp(String args) throws IOException {
        String[] files;
        files = (args.split(""));
        String FirstFile = files[0];
        String SecondFile = files[1];
        FileWriter writeFile = new FileWriter(SecondFile);
        Scanner myReader = new Scanner(FirstFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            writeFile.write(data);
        }
        myReader.close();
        writeFile.close();
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
        FileWriter writeFile = new FileWriter(file);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            writeFile.write(data);
        }
        myReader.close();
        writeFile.close();
    }

    // ... //This method will choose the suitable command method to be called
    public void chooseCommandAction() {
        String thisCommand = this.parser.getCommandName();
        File currFile = new File(".");
        switch (thisCommand) {
            case "echo":
                echo(this.parser.getArgs());
                break;
            case "pwd":
                pwd();
                break;
            case "cd":
                break;
            case "ls":
                ls(currFile);
                break;
            case "ls -r":
                lsReverse(currFile);
                break;
            case "touch":
                break;

        }
    }

    public static void main(String[] args) {

        Terminal terminal = new Terminal();
        Scanner input = new Scanner(System.in);
        String _command = new String();
        boolean flag = true;
        while (flag) {

            _command = input.nextLine();
            if(_command.equals("exit")){
                flag=false;
                break;
            }
            terminal.parser.parse(_command);
            terminal.chooseCommandAction();



        }
//        String[] arr;
//        String copy = new String();
//        arr = _command.split(" ");
//        System.out.println(arr[0]);
//        for(int i = 1 ;i<arr.length;i++){
//            copy+=arr[i]+" ";
//        }
//        System.out.println(copy);

//        Parser parser = new Parser();
//        parser.parse(_command);
//        Path currentDir = Paths.get("");
//        System.out.println(currentDir.toAbsolutePath());


    }
}
