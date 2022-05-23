import java.util.Timer;
import java.util.TimerTask;
public class CoffeeTimer {
	Timer timer;

    public CoffeeTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000); // schedule the task
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("You have a notification!");
            timer.cancel(); //Terminate the timer thread
        }
    }

    public static void main(String args[]) {
        new CoffeeTimer(5);
    }

}
