package wrong_inheritance;

abstract class Duck {
    // Method 1: Abstract method.
    public abstract void display(); 

    // Method 2: Concrete method.
    public void swim() { 
        System.out.println("\n(All ducks float, even decoys!)");
    }
    
    // Method 3: Concrete Method. 
    // Subclasses MUST override this to define their unique quack.
    public void quack() { 
        System.out.println("DEFAULT QUACK SOUND."); // Base, generic sound.
    }

    public void fly() {
        System.out.println("All ducks can fly.");
    }
}

class RubberDuck extends Duck {
    @Override
    public void display() { 
        System.out.println("Looks like a rubber duck."); 
    }
    
    @Override 
    public void quack() {
        System.out.println("Squeak! Squeak!");
    }

}

public class Duck_with_wrong_inheritance {
    public static void main(String[] args) {
        // Create an instance of the first duck type
        Duck rubberDuck = new RubberDuck();
        
        System.out.println("==============================");
        System.out.println("=== Running Rubber Duck ===");
        System.out.println("==============================");
        rubberDuck.display(); 
        rubberDuck.quack();   
        rubberDuck.swim();    
        rubberDuck.fly();     // THIS IS WHERE INHERITANCE FAILS.
    }
}