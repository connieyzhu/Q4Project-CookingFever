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
        	Runner.getObject(0).change();
        	Runner.getObject(1).change();
        	Runner.getObject(2).change();
            timer.cancel(); //Terminate the timer thread
        }
    }

}
