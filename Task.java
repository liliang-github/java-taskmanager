/**
 * 任务实体类
 */
public class Task {
  private String name;
  private int pid;
  private String memory;

  public Task(String name, int pid, String memory) {
    this.name = name;
    this.pid = pid;
    this.memory = memory;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPid() {
    return this.pid;
  }

  public void setPid(int pid) {

  }

  public String getMemory() {
    return memory;
  }

  public void setMemory(String memory) {
    this.memory = memory;
  }

  @Override
  public int hashCode() {
    return this.pid;
  }

  @Override
  public boolean equals(Object obj) {
    return this.name.equals(((Task) obj).name);
  }

  @Override
  public String toString() {
    return String.format("%s\t%d\t%sK", name, pid, memory);
  }
}