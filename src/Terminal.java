import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Terminal extends Parser {
    public Parser parser;
    public Path currentDir;

    Terminal() {
        parser = new Parser();
        currentDir = Paths.get("");
    }

    //Implement each command in a method, for example:
    public void echo(String[] argument) {
        String input = "";
        for (int i = 0; i < argument.length; i++) {
            if (argument[i].equals(">")){
                break;
            }
            input +=argument[i];
            if (i == argument.length - 1) {
                break;
            }
            System.out.print(" ");
        }
        if (check()) {
            try {
                String lastNum = parser.args[parser.args.length-1];
                FileWriter  myWriter = new FileWriter(lastNum);
                myWriter.write(input);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println(input);
            System.out.print("\n");
        }
    }

    public void pwd() {
        if (check()) {
            try {
                String lastNum = parser.args[parser.args.length-1];
                FileWriter  myWriter = new FileWriter(lastNum);
                myWriter.write(currentDir.toAbsolutePath().toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println(currentDir.toAbsolutePath().toString());
            System.out.print("\n");
        }
    }

    public void cd(String[] args) {


        if (args.length == 0) {
            String home = System.getProperty("user.home");
            currentDir = Paths.get(home);
            System.out.println(currentDir.toAbsolutePath().toString());
            System.out.print("\n");
        } else if (Arrays.toString(args).contains("..")) {
            String LastIndex = currentDir.toAbsolutePath().toString();
            if (LastIndex.length() > 2) {
                String LastPlace = LastIndex.substring(LastIndex.lastIndexOf("\\"));
                String LastPath = LastIndex.replace(LastPlace, "");
                File file = new File(LastPath);
                currentDir = file.toPath();
                System.out.println(LastPath);
                System.out.print("\n");
            }
            parser.clear();
        } else {
            String secound = "";
            for (int i = 0; i < args.length - 1; i++) {
                secound = secound + args[i] + " ";
            }
            File file = new File(secound);
            if (file.exists()) {
                Path temp = file.toPath();
                System.out.println(temp.toString());


            }
        }
        parser.clear();
    }


    public void ls(File currList) {
        File[] filesList = currList.listFiles();
        if (check()) {
            FileWriter myWriter = null;
            try {
                String lastNum = parser.args[parser.args.length-1];
                myWriter = new FileWriter(lastNum, true);
                for (int i = 0; i < filesList.length; i++) {
                    myWriter.write(filesList[i].toString());
                    System.out.println("Successfully wrote to the file.");

                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            for (File file : filesList) {
                if (file.isDirectory())
                    System.out.print(file.getName() + "\t");
                if (file.isFile()) {
                    System.out.println(file.getName() + "\t");
                }
            }
        }
    }


    public void lsReverse(File currList) {
        File[] filesList = currList.listFiles();
        if (check()) {
            FileWriter myWriter = null;
            try {
                String lastNum = parser.args[parser.args.length-1];
                myWriter = new FileWriter(lastNum);
                for (int i = filesList.length-1; i >= 0; i--) {
                    myWriter.write(filesList[i].toString());
                    System.out.println("Successfully wrote to the file.");

                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            for (int i = filesList.length-1; i >= 0; i--) {
                if (filesList[i].isDirectory())
                    System.out.print(filesList[i].getName() + "\t");
                if (filesList[i].isFile()) {
                    System.out.println(filesList[i].getName() + "\t");
                }
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

    public void rmdir(String[] args_, File curdir) {

        String first = args_[0];
        File[] file_ = curdir.listFiles();
        if (first.contains("*")) {
            for (int i = 0; i < file_.length; i++) {
                File file = new File(file_[i].getName());
                if (file.exists() && file.isDirectory() && file.listFiles().length == 0) {
                    file.delete();
                    System.out.println("done");
                }
            }
        } else {
            String secound = "";
            for (int i = 0; i < args_.length; i++) {
                secound = secound + args_[i] + " ";
            }
            File file = new File(secound);
            if (file.exists()) {
                file.getAbsolutePath();
                file.delete();
                System.out.println("done");


            }
        }
    }


    public void mkdir(String[] fileName) {
        File file_ = new File(fileName[fileName.length - 1]);
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
        System.out.println("done");
    }

    public void rm(String[] FileName) {
        File file = new File(FileName[0]);
        if (file.exists()) {
            file.delete();
            System.out.println("done");
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

    public boolean check() {
        if (Arrays.toString(parser.getArgs()).contains(">")) {
            return true;
        } else {
            return false;
        }
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
                cd(this.parser.getArgs());
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
                break;
            case "mkdir":
                mkdir(this.parser.getArgs());
                break;
            case "rmdir":
                rmdir(this.parser.getArgs(), currFile);
                break;

            default:
                System.out.println("Error: Command not found or invalid parameters are entered!");
        }
    }

    public static void main(String[] args) {

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
            System.out.println();
        }
    }
}
