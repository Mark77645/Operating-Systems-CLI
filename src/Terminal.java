public class Terminal extends Parser {
    Parser parser;
//Implement each command in a method, for example:
    public void echo(String argument){
        System.out.println(argument);
    }
    public String pwd(){
        System.out.println();
    }
    public void cd(String[] args){...}
    // ... //This method will choose the suitable command method to be called
    public void chooseCommandAction(){}

    public static void main(String[] args){...}
}
