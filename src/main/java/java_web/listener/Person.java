package java_web.listener;

public class Person {
private PersonListener listener;

public void eat(){
if (listener != null)
    listener.doEat(new Event(this));
}

public void run(){
    if (listener != null)
        listener.doRun(new Event(this));
}

public void registerListener(PersonListener listener){
    this.listener = listener;
}

public static void main(String[] args) {

Person person = new Person();
person.registerListener(new PersonListener() {
    @Override
    public void doEat(Event e) {
        Person p = e.getSource();
        System.out.println(p+"在吃东西");
    }

    @Override
    public void doRun(Event e) {
        Person p = e.getSource();
        System.out.println(p+"在跑步");
    }
});

    person.eat();
    person.run();

}

}
