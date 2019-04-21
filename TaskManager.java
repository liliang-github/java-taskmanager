
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务操作类
 * 
 */
public class TaskManager implements Runnable {
  protected static List<String> excludeList = new ArrayList<String>();
  private static TaskManager tm = new TaskManager();
  private ProcessInfo process;

  static {
    try {
      BufferedReader configbr = new BufferedReader(
          new InputStreamReader(TaskManager.class.getClassLoader().getResourceAsStream("excludeList.config")));
      String str = null;
      while ((str = configbr.readLine()) != null) {
        if (!str.startsWith("#"))
          excludeList.add(str);
      }
      configbr.close();
    } catch (Exception e) {
      System.out.println("配置文件读取失败");
    }
    new Thread(tm).start();
  }

  private TaskManager() {
    process = new ProcessInfo("cmd");
  }

  public static TaskManager getInstance() {
    return tm;
  }

  public List<Task> getTaskList() {
    List<Task> list = null;
    while (true) {
      list = process.readList();
      if (list.size() != 0) {
        break;
      }
    }
    return list;
  }

  public void kill(String killNameOrId) {
    process.input(String.format("taskkill -t -f -im %s", killNameOrId));
  }

  public static Task stringToTask(String taskStr) {
    if (taskStr.endsWith("K") && taskStr.contains(".exe")) {
      String[] tasks = taskStr.split(" +");
      if (!excludeList.contains(tasks[0]))
        return new Task(tasks[0], Integer.parseInt(tasks[1]), tasks[4]);
    }
    return null;
  }

  public static void print(Object obj) {
    System.out.println(obj);
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(1000);
        process.input("tasklist");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
