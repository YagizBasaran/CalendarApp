import model.Activity;
import model.Person;
import controller.Controller;

public class App {
   
    public static void main(String[] args) {
        
        Person m = new Person();
        Activity a = new Activity();
        new Controller(m,a);
       }

}
