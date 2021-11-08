class Parser {
    public String commandName;
    public String[] args;
    //This method will divide the input into commandName and args
    // where "input" is the string command entered by the user
    public boolean parse(String input){
        String [] arr;
        arr=input.split("\\s");
        commandName=arr[0].toString();
        args=arr;
        return true;
    }
    public String getCommandName(){
        return commandName;
    }
    public String[] getArgs(){
        return args;
    }
}