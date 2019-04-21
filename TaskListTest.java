import java.util.Timer;
import java.util.TimerTask;

public class TaskListTest {
  public static void main(String[] args) {
    TaskManager tm = TaskManager.getInstance();
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        for (Task task : tm.getTaskList()) {
          System.out.println(task);
        }
      }
    }, 0, 5000);
  }
}