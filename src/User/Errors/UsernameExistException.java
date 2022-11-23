package User.Errors;

public class UsernameExistException extends Exception{
    public UsernameExistException(String s){
        super(s);
    }
}
