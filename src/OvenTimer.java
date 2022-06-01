import java.util.Timer;
import java.util.TimerTask;
public class OvenTimer {
	Timer timer;
	protected Object obj;

    public OvenTimer(int seconds, Object o) {
        timer = new Timer();
        obj = o;
        timer.schedule(new RemindTask(), seconds*1000); // schedule the task
    }

    class RemindTask extends TimerTask {
        public void run() {
            for(int i = 3; i < Runner.getObjectList().size(); i++) {
            	if(Runner.getObjectList().get(i).equals(obj)) {
            		Runner.getObjectList().get(i).bakeChange(); 
            	}
            	
            }
            timer.cancel(); //Terminate the timer thread
        }
    }

}
