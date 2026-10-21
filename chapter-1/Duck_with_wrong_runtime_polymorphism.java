abstract class Duck {
    public abstract void display();

    public void swim() {
        System.out.println("(All ducks float, even decoys!)");
    }

    public void quack() {
        System.out.println("DEFAULT QUACK SOUND.");
    }

    public void fly() {
        System.out.println("All ducks can fly.");
    }
}

class WoodenDecoyDuck extends Duck {
    @Override
    public void display() {
        System.out.println("Looks like a wooden decoy duck.");
    }

    @Override
    public void quack() {
        System.out.println("Do nothing");
    }

    @Override
    public void fly() {
        System.out.println("Do nothing");
    }
}

public class Duck_with_wrong_runtime_polymorphism {
    public static void main(String[] args) {
        // Create an instance of the first duck type
        Duck woodenDecoyDuck = new WoodenDecoyDuck();

        System.out.println("==============================");
        System.out.println("=== Running Wooden Decoy Duck ===");
        System.out.println("==============================");
        woodenDecoyDuck.display(); 
        woodenDecoyDuck.quack(); // Wrong use of Runtime Polymorphism.
        woodenDecoyDuck.swim(); 
        woodenDecoyDuck.fly(); // Wrong use of Runtime Polymorphism.
    }
}
    