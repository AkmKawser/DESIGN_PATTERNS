package strategy_pattern;

// ==========================================
// PAGE 13: THE BEHAVIOR INTERFACES
// ==========================================
interface FlyBehavior {
    void fly();
}

interface QuackBehavior {
    void quack();
}

// ==========================================
// PAGE 13: THE CONCRETE BEHAVIOR CLASSES
// ==========================================

// --- Fly Behaviors ---
class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!!"); // Exact text from Page 16 output
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly"); // Exact text from Page 16 output
    }
}

// [PAGE 15, STEP 3]: The new Rocket-powered behavior
class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!"); // Exact text from Page 16 output
    }
}

// --- Quack Behaviors ---
class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack"); // Exact text from Page 16 output
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}

// ==========================================
// PAGE 13 & 14: THE DUCK SUPERCLASS
// ==========================================
abstract class Duck {
    // Page 13: Instance variables of the behavior interface types
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}

    public abstract void display();

    // Page 13: perform methods that delegate to the behavior objects
    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }
    
    // Page 15, Step 1: The dynamic setter methods
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
// PAGE 14: THE SPECIFIC DUCK TYPES
// ==========================================
class MallardDuck extends Duck {
    public MallardDuck() {
        // Page 14, Step 3: Setting behaviors in the constructor
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
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("I'm a decoy duck.");
    }
}

// [PAGE 15, STEP 2]: The new ModelDuck
class ModelDuck extends Duck {
    public ModelDuck() {
        // Model duck begins life grounded, without a way to fly.
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}

// ==========================================
// PAGE 16: THE TEST CLASS (MiniDuckSimulator)
// ==========================================
public class MiniDuckSimulator {
    public static void main(String[] args) {
        
        // Page 16, Step 4: The exact test sequence from the book
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        
        // 1. First call delegates to the FlyNoWay behavior set in the constructor
        model.performFly(); 
        
        // 2. Dynamically change the behavior at runtime!
        model.setFlyBehavior(new FlyRocketPowered());
        
        // 3. Now it flies with a rocket
        model.performFly(); 
    }
}
