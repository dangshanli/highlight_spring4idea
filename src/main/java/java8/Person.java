package java8;

/**
 * @author luzj
 * @description:
 * @date 2018/4/23
 */
public class Person {
    String firstName;
    String lastName;

    Person(){}

    String playGitua(){
        return "gitua";
    }

    Person askPerson(){
        return new Person();
    }

    private String piano(){
        return "piano";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
