class Parser {
    String commandName;
    String[] args;
    Parser(){
        args=null;

    }
    //This method will divide the input into commandName and args
    // where "input" is the string command entered by the user
    public boolean parse(String input){
        String[] arr;
        arr = input.split(" "); // Holding the first string which is the command itself
        commandName = arr[0];// Command name is now the first index in the array
        // For loop that copies all the arguments of the command except for the command name (arr[0]).
        args=new String[arr.length-1];
        for(int i = 1 ;i<arr.length;i++){
            args[i-1]=arr[i];
        }
        //System.out.println(commandName+" "+args.toString());
        return true;
    }
    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }
    public void clear(){
        commandName="";
        args=null;
    }
}