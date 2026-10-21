package interfaces;

interface Flyable {
    void fly();
}

interface Quackable {
    void quack();
}

abstract class Duck {
    public abstract void display();

    public void swim() {
        System.out.println("All ducks float, even decoys.");
    }
}

class MallardDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {
        System.out.println("I'm a Mallard duck.");
    }

    @Override
    public void fly() {
        System.out.println("Mallard duck is flying.");
    }

    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}

class RedheadDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {
        System.out.println("I'm a Redhead duck.");
    }

    @Override
    public void fly() {
        System.out.println("Redhead duck is flying.");
    }

    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}

class RubberDuck extends Duck implements Quackable {
    @Override
    public void display() {
        System.out.println("I'm a rubber duck.");
    }

    @Override
    public void quack() {
        System.out.println("Squeak!");
    }
}

class DecoyDuck extends Duck {
    @Override
    public void display() {
        System.out.println("I'm a decoy duck.");
    }
}


public class Duck_with_interfaces {
    public static void main(String[] args) {
        // Mallard: can fly and quack
        MallardDuck mallard = new MallardDuck();

        System.out.println("==============================");
        System.out.println("=== Running Mallard Duck ===");
        System.out.println("==============================");
        mallard.display(); // Runs Mallard's specific display() logic
        mallard.quack();   // Runs Mallard's specific quack() logic (Quackable)
        mallard.fly();     // Runs Mallard's specific fly() logic (Flyable)
        mallard.swim();    // Runs the shared, concrete logic from Duck.

        System.out.println("\n\n==============================");
        System.out.println("=== Running Redhead Duck ===");
        System.out.println("==============================");
        RedheadDuck redhead = new RedheadDuck();
        redhead.display(); // Runs Redhead's specific display() logic
        redhead.quack();   // Runs Redhead's specific quack() logic (Quackable)
        redhead.fly();     // Runs Redhead's specific fly() logic (Flyable)
        redhead.swim();    // Runs the shared, concrete logic from Duck.

        System.out.println("\n\n==============================");
        System.out.println("=== Running Rubber Duck ===");
        System.out.println("==============================");
        RubberDuck rubber = new RubberDuck();
        rubber.display();  // Runs Rubber's specific display() logic
        rubber.quack();    // Runs Rubber's specific quack() logic (Quackable)
        rubber.swim();     // Runs the shared, concrete logic from Duck.

        System.out.println("\n\n==============================");
        System.out.println("=== Running Decoy Duck ===");
        System.out.println("==============================");
        Duck decoy = new DecoyDuck();
        decoy.display();   // Runs Decoy's specific display() logic
        decoy.swim();      // Runs the shared, concrete logic from Duck.
    }
}