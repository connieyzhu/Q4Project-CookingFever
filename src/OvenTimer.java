import java.util.Timer;
import java.util.TimerTask;
public class OvenTimer {
	Timer timer;
	protected int index;

    public OvenTimer(int seconds, int ind) {
        timer = new Timer();
        index = ind;
        timer.schedule(new RemindTask(), seconds*1000); // schedule the task
    }

    class RemindTask extends TimerTask {
        public void run() {
            for(int i = 3; i < Runner.getObjectList().size(); i++) {
            	Runner.getObject(index).bakeChange(); 
            	
            }
            timer.cancel(); //Terminate the timer thread
        }
    }
	
	public static void main(String args[]) {
     
    }

}
