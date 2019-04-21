import java.io.*;
import java.util.*;

/**
 * 任务进程类
 */
class ProcessInfo {
  private Process process;
  private BufferedReader br;
  private PrintWriter pw;

  /**
   * 初始化任务对象
   * 
   * @param cmd 初始化执行的cmd命令
   */
  public ProcessInfo(String cmd) {
    try {
      this.process = Runtime.getRuntime().exec(cmd);
      this.br = new BufferedReader(new InputStreamReader(this.process.getInputStream(), "gb2312"));
      this.pw = new PrintWriter(this.process.getOutputStream());
    } catch (Exception e) {
      throw new RuntimeException("进程任务创建失败");
    }
  }

  /**
   * 读取一行结果
   */
  public String readLine() {
    String str = "读取失败";
    try {
      while (true) {
        str = br.readLine();
        if (str != null && str.trim().length() != 0) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  /**
   * 读取所有任务结果并转换为任务集合对象
   * 
   * @return 当前计算机任务集合
   */
  public List<Task> readList() {
    List<Task> list = new ArrayList<Task>();
    String str = null;
    try {
      while ((str = br.readLine()) != null) {
        Task t = stringToTask(str);
        if (t != null)
          list.add(t);
        else if (str.trim().length() == 0) {
          break;
        }
      }
    } catch (Exception e) {

    }
    return list;
  }

  /**
   * 将String转换为任务对象
   * 
   * @param taskStr 需转换的任务
   * @return 转换后的结果
   */
  private Task stringToTask(String taskStr) {
    if (taskStr.endsWith("K") && taskStr.contains(".exe")) {
      String[] tasks = taskStr.split(" +");
      if (!TaskManager.excludeList.contains(tasks[0]))
        return new Task(tasks[0], Integer.parseInt(tasks[1]), tasks[4]);
    }
    return null;
  }

  /**
   * 执行输入的命令
   * 
   * @param cmd 需要执行的命令
   */
  public void input(String cmd) {
    pw.println(cmd);
    pw.flush();
  }

  /**
   * @return the process
   */
  public Process getProcess() {
    return process;
  }

  /**
   * @param br the br to set
   */
  public void setBr(BufferedReader br) {
    this.br = br;
  }

  /**
   * @return the br
   */
  public BufferedReader getBr() {
    return br;
  }

  /**
   * @param pw the pw to set
   */
  public void setPw(PrintWriter pw) {
    this.pw = pw;
  }

  /**
   * @param process the process to set
   */
  public void setProcess(Process process) {
    this.process = process;
  }

  /**
   * @return the pw
   */
  public PrintWriter getPw() {
    return pw;
  }
}
