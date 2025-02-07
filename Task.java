import java.time.LocalDate;

public class Task {
    private static int lastId = 0; 
    private int id;
    private String desc;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    enum status 
    {
        TODO,
        INPROGRESS,
        DONE
    
    }

    private status status;

    Task(String desc) {
        this.id = ++lastId;
        this.desc = desc;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.status = status.TODO;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public status getStatus() {
        return this.status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        this.updatedAt = LocalDate.now();
    }

    public void setStatus(status status) {
        this.status = status;
        this.updatedAt = LocalDate.now();
    }

    public String toString() {
        return "Task: " + this.id + " - " + this.desc + " - " + this.createdAt + " - " + this.updatedAt + " - " + this.status;
    }

    
}
