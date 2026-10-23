package implementing_duck_behaviors_page_13;

// ==========================================
// 1. THE BEHAVIOR INTERFACES
// ==========================================
interface FlyBehavior {
    void fly();
}

interface QuackBehavior {
    void quack();
}

// ==========================================
// 2. THE CONCRETE BEHAVIOR CLASSES
// (These replace the repeated code in your ducks)
// ==========================================

// --- Fly Behaviors ---
class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with wings!");
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly.");
    }
}

// --- Quack Behaviors ---
class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak!");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}

// ==========================================
// 3. THE DUCK SUPERCLASS (Now uses Composition)
// ==========================================
abstract class Duck {
    
    // We define the behavior types as variables.
    // Notice we are "programming to the interface" here!
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}

    public abstract void display();

    // The Duck delegates the action to the behavior object
    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }
    
    // Allows us to change behavior at runtime
    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

    public void swim() {
        System.out.println("All ducks float, even decoys.");
    }
}

// ==========================================
// 4. THE SPECIFIC DUCK TYPES
// ==========================================

class MallardDuck extends Duck {
    public MallardDuck() {
        // Here we assign the specific behaviors in the constructor
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck.");
    }
}

class RedheadDuck extends Duck {
    public RedheadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a Redhead duck.");
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        // Rubber ducks squeak and cannot fly
        quackBehavior = new Squeak();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("I'm a rubber duck.");
    }
}

class DecoyDuck extends Duck {
    public DecoyDuck() {
        // Decoy ducks are silent and cannot fly
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("I'm a decoy duck.");
    }
}

// ==========================================
// 5. MAIN METHOD
// ==========================================
public class Duck_with_interfaces {
    public static void main(String[] args) {
        
        System.out.println("==============================");
        System.out.println("=== Running Mallard Duck ===");
        System.out.println("==============================");
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performQuack(); // Note: method renamed to performQuack
        mallard.performFly();   // Note: method renamed to performFly
        mallard.swim();

        System.out.println("\n==============================");
        System.out.println("=== Running Rubber Duck ===");
        System.out.println("==============================");
        Duck rubber = new RubberDuck();
        rubber.display();
        rubber.performQuack(); 
        rubber.performFly();   
        rubber.swim();

        // The magic of this new design: Changing behavior at runtime!
        System.out.println("\n==============================");
        System.out.println("=== Magic: Rubber Duck gets rocket power! ===");
        System.out.println("==============================");
        rubber.setFlyBehavior(new FlyWithWings()); // We just gave it wings!
        rubber.performFly(); // Now it flies!
    }
}
