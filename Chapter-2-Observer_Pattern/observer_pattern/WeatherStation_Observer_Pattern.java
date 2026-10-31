/*
 * Head First Design Patterns - Chapter 2: The Observer Pattern
 * Final Weather Station code (pull version), in a single runnable file.
 *
 * Page numbers below refer to the printed page numbers in the chapter PDF.
 *
 *   Overall class design (diagram) .................... p. 57
 *   Weather-O-Rama requirements ....................... pp. 38-41
 *   The Observer Pattern defined / class diagram ...... pp. 51-52
 *   Loose coupling principle .......................... pp. 53-54
 *   Push vs. pull (fireside chat) ..................... pp. 63-64
 *   Switching the design to pull ...................... pp. 68-69
 *
 * Run:  java WeatherStation.java      (Java 11+)
 *  or:  javac WeatherStation.java && java WeatherStation
 */
import java.util.ArrayList;
import java.util.List;

// Test harness: p. 61 ("Power up the Weather Station")
// Heat index display added in the p. 62 exercise.
public class WeatherStation {
    public static void main(String[] args) {
        // p. 61: first, create the WeatherData object
        WeatherData weatherData = new WeatherData();

        // p. 61: create the displays and pass them the WeatherData object
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData); // p. 62

        // p. 61: simulate new weather measurements
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}

// Subject interface: p. 58 (structure on p. 52, used in the design on p. 57).
// Objects use it to register as observers and to remove themselves.
interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

// Observer interface: p. 58 (original push version passed temp, humidity, pressure).
// Changed to the no-argument pull version on p. 69.
interface Observer {
    public void update();
}

// DisplayElement interface: p. 58. All display elements just implement display().
interface DisplayElement {
    public void display();
}

// WeatherData implements Subject: p. 59 (original class and getters: p. 40).
// Pull changes to notifyObservers(): p. 69.
class WeatherData implements Subject {
    private List<Observer> observers;   // p. 59: ArrayList holds the observers
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<Observer>();   // p. 59: created in the constructor
    }

    // p. 59: when an observer registers, add it to the end of the list
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    // p. 59: when an observer un-registers, take it off the list
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // p. 59: tell all observers about the state.
    // p. 69: pull version calls update() with no arguments.
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // p. 40: called whenever the measurements have been updated;
    // p. 59: it now just notifies the observers.
    public void measurementsChanged() {
        notifyObservers();
    }

    // p. 59: test method standing in for a real weather station device
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    // p. 40: getters supplied by Weather-O-Rama; observers pull state with these (p. 69)
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

// CurrentConditionsDisplay: p. 60 (pull version of update(): p. 69).
class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    // p. 60: constructor gets the Subject and registers the display as an observer.
    // The reference is kept so we could un-register later.
    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    // p. 69: pull the values we need from the Subject, then display
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }

    // p. 60: just prints the most recent temp and humidity
    public void display() {
        System.out.println("Current conditions: " + temperature
            + "F degrees and " + humidity + "% humidity");
    }
}

// StatisticsDisplay: p. 71 ("one more display to update, the Avg/Min/Max display").
// The book leaves this one to the reader (code is in its download); it tracks
// min/avg/max temperature (p. 57). Output format shown on p. 61.
class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    // pull style (p. 69): only temperature is needed here
    public void update() {
        float temp = weatherData.getTemperature();
        tempSum += temp;
        numReadings++;

        if (temp > maxTemp) {
            maxTemp = temp;
        }
        if (temp < minTemp) {
            minTemp = temp;
        }
        display();
    }

    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
            + "/" + maxTemp + "/" + minTemp);
    }
}

// ForecastDisplay: Code Magnets exercise on p. 70, solution on p. 76.
// Forecast based on the barometer (p. 57); output shown on p. 61.
class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update() {
        lastPressure = currentPressure;
        currentPressure = weatherData.getPressure();
        display();
    }

    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
}

// HeatIndexDisplay: "Sharpen your pencil" exercise on p. 62
// (formula given there; expected output 82.95535 / 86.90124 / 83.64967).
class HeatIndexDisplay implements Observer, DisplayElement {
    private float heatIndex = 0.0f;
    private WeatherData weatherData;

    public HeatIndexDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update() {
        float t = weatherData.getTemperature();
        float rh = weatherData.getHumidity();
        heatIndex = computeHeatIndex(t, rh);
        display();
    }

    // p. 62: heat index combines temperature (T) and relative humidity (RH)
    private float computeHeatIndex(float t, float rh) {
        return (float) ((16.923 + (0.185212 * t) + (5.37941 * rh)
            - (0.100254 * t * rh) + (0.00941695 * (t * t))
            + (0.00728898 * (rh * rh)) + (0.000345372 * (t * t * rh))
            - (0.000814971 * (t * rh * rh)) + (0.0000102102 * (t * t * rh * rh))
            - (0.000038646 * (t * t * t)) + (0.0000291583 * (rh * rh * rh))
            + (0.00000142721 * (t * t * t * rh))
            + (0.000000197483 * (t * rh * rh * rh))
            - (0.0000000218429 * (t * t * t * rh * rh))
            + 0.000000000843296 * (t * t * rh * rh * rh))
            - (0.0000000000481975 * (t * t * t * rh * rh * rh)));
    }

    public void display() {
        System.out.println("Heat index is " + heatIndex);
    }
}
