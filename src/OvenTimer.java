import java.util.Timer;
import java.util.TimerTask;
public class OvenTimer {
	Timer timer;

    public OvenTimer(int seconds) {
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
        new OvenTimer(5);
    }
}
