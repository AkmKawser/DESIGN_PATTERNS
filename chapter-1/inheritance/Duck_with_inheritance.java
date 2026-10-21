package inheritance;

abstract class Duck {
    
    // Method 1: Abstract Method. 
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
}

// --- Subclass Example 1: MallardDuck ---
class MallardDuck extends Duck {
    @Override   //polymorphism: overriding the abstract method display() from Duck
    public void display() { 
        System.out.println("Looks like a mallard (Beautiful and green)."); 
    }
    
    // Overriding the common quack method for specific sound logic.
    @Override   //polymorphism: overriding the quack() method from Duck
    public void quack() { 
        System.out.print("Quack! Quack!"); // Unique implementation for Mallards.
    }
}

// --- Subclass Example 2: RedheadDuck (The Added Code) ---
class RedheadDuck extends Duck {
    @Override
    public void display() { 
        System.out.println("Looks like a male redhead."); 
    }
    
    // Overriding the common quack method for specific sound logic.
    @Override
    public void quack() { 
        System.out.print("Quak! Quak!"); // Unique implementation for Redheads.
    }
}


public class Duck_with_inheritance {
    public static void main(String[] args) {
        // Create an instance of the first duck type
        Duck mallard = new MallardDuck();
        
        System.out.println("==============================");
        System.out.println("=== Running Mallard Duck ===");
        System.out.println("==============================");
        mallard.display(); // Runs Mallard's specific display() logic
        mallard.quack();   // Runs Mallard's specific quack() logic
        mallard.swim();    // Runs the shared, concrete logic from Duck.

        System.out.println("\n\n==============================");
        System.out.println("=== Running Redhead Duck ===");
        System.out.println("==============================");
        Duck redhead = new RedheadDuck();
        redhead.display(); // Runs Redhead's specific display() logic
        redhead.quack();   // Runs Redhead's specific quack() logic
        redhead.swim();    // Runs the shared, concrete logic from Duck.
    }
}
