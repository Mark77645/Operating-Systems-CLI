import java.io.File;
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
    public void echo(String[] argument) {
        for (int i = 0; i < argument.length; i++) {
            System.out.print(argument[i]);
            if (i == argument.length - 1) {
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
            if (filesList[i].isDirectory()) {
                System.out.print(filesList[i].getName() + "\t");
            }
            if (filesList[i].isFile()) {
                System.out.println(filesList[i].getName() + "\t");
            }
        }
    }

    public void touch(String [] args) throws IOException {
        File NewFile = new File(args[0]);
        NewFile.createNewFile();
    }

    public void cp(String [] args) throws IOException {
        File readFile= new File(args[0]);
        FileWriter writeFile = new FileWriter(args[1]);
        Scanner myReader = new Scanner(readFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            writeFile.write(data);
        }
        myReader.close();
        writeFile.close();
    }

    public void rmdir(String[] args_,File curdir) {
        String first = args_[0];

        File[] file_ = curdir.listFiles();
        if (first.contains("*")){
            for (int i = 0; i < file_.length; i++) {
                File file = new File(file_[i].getName());
                if (file.exists() && file.isDirectory() && file.listFiles().length==0){
                 file.delete();
                    System.out.println("done");
                }
            }
        }
//        File file_ = new File(fileName[fileName.length-1]);
//        for (int i = 0; i < fileName.length; i++) {
//            if (file_.isAbsolute()) {
//                for (int j = 0; j < fileName.length; j++) {
//                    File file = new File(file_.getAbsolutePath());
//                    boolean make_ = file.delete();
//                }
//            }
//            boolean file = new File(fileName[i]).delete();
//            if (i == fileName.length - 1) {
//                break;
//            }
//        }

//        if (fileName[0].equals("*") && file_.exists()) {
//            for (File file : filesList) {
//                if (file.length() == 0)
//                    file.delete();
//                if (file.isFile()) {
//                    System.out.println(file.getName() + "\t");
//                }
//            }
//        }

    }


    public void mkdir(String[] fileName) {
        File file_ = new File(fileName[fileName.length-1]);
        for (int i = 0; i < fileName.length; i++) {
            if (file_.isAbsolute()) {
                for (int j = 0; j < fileName.length; j++) {
                    File file = new File(file_.getAbsolutePath());
                    boolean make_ = file.mkdir();
                }
            }
            boolean file = new File(fileName[i]).mkdir();
            if (i == fileName.length - 1) {
                break;
            }
        }
    }

    public void rm(String[] FileName) {
        File file = new File(FileName[0]);
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println(file.getAbsolutePath() + " No such file!");
        }
    }

    public void cat(String [] file) throws IOException {
        File file1 = new File(file[0]);
        Scanner myReader = new Scanner(file1);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    }

    // ... //This method will choose the suitable command method to be called
    public void chooseCommandAction() throws IOException {
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
                if (Arrays.toString(this.parser.getArgs()).contains("-r")) {
                    lsReverse(currFile);
                } else {
                    ls(currFile);
                }
                break;
            case "rm":
                rm(this.parser.getArgs());
                break;
            case "touch":
                touch(this.parser.getArgs());
                break;
            case "mkdir":
                mkdir(this.parser.getArgs());
                break;
            case "rmdir":
                rmdir(this.parser.getArgs(),currFile);
                break;
            case "cat":
                cat(this.parser.getArgs());
                break;
            case "cp":
                cp(this.parser.getArgs());
                break;
            default:
                System.out.println("Error: Command not found or invalid parameters are entered!");
        }
    }

    public static void main(String[] args) throws IOException {

//        File file=new File(".");
//        System.out.println("Current Working Directory: " + file.getAbsolutePath());
//        System.setProperty("user.dir", "/tmp");
//        System.out.println("New Current Working Directory: " + file.getAbsolutePath());

        Terminal terminal = new Terminal();
        Scanner input = new Scanner(System.in);
        String _command = new String();
        boolean flag = true;
        while (flag) {
            System.out.print(">");
            _command = input.nextLine();
            if (_command.equals("exit")) {
                flag = false;
                break;
            }
            terminal.parser.parse(_command);
            terminal.chooseCommandAction();

        }
    }
}
