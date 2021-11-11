import java.nio.file.Paths;

class Parser {
    public String commandName;
    public String[] args;

    // This method will divide the input into commandName and args
    // where "input" is the string command entered by the user
    public boolean parse(String input) {
        String[] arr;
        arr = input.split("\\s"); // Holding the first string which is the command itself
        commandName = arr[0].toString();// Command name is now the first index in the array
        // For loop that copies all the arguments of the command except for the command name (arr[0]).
        for (int i = 0; i < arr.length; i++) { 
            args[i] = arr[i + 1];
        }
        return true;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }
}