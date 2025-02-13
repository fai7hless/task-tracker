import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int lastId = 0; 
    private int id;
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    Task(String desc) {
        this.id = ++lastId;
        this.desc = desc;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = Status.TODO;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void setDesc(String desc) {
        this.desc = desc;
        this.updatedAt = LocalDateTime.now();
    }

    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public String toJson(){
        return "{\n" +
                "  \"id\": " + this.id + ",\n" +
                "  \"desc\": \"" + this.desc + "\",\n" +
                "  \"createdAt\": \"" + this.createdAt + "\",\n" +
                "  \"updatedAt\": \"" + this.updatedAt + "\",\n" +
                "  \"status\": \"" + this.status + "\"\n" +
                "}";
    }

    public static Task fromJson(String json) {
        json = json.replace("{", "").replace("}", "");
        String[] jsonParts = json.split(",");

        String id = "";
        String desc = "";
        String statusString = "";
        String createdAtStr = "";
        String updatedAtStr = "";

        for (String part : jsonParts) {
            String[] keyValue = part.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].strip().replace("\"", "");
                String value = keyValue[1].strip().replace("\"", "");

                switch (key) {
                    case "id":
                        id = value;
                        break;
                    case "desc":
                        desc = value;
                        break;
                    case "status":
                        statusString = value;
                        break;
                    case "createdAt":
                        createdAtStr = value;
                        break;
                    case "updatedAt":
                        updatedAtStr = value;
                        break;
                }
            }
        }

        Status status = Status.valueOf(statusString.toUpperCase().replace(" ", "_"));

        Task task = new Task(desc);
        task.id = Integer.parseInt(id);
        task.status = status;
        try {
            task.createdAt = LocalDateTime.parse(createdAtStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            task.createdAt = LocalDateTime.now();
        }
        try {
            task.updatedAt = LocalDateTime.parse(updatedAtStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            task.updatedAt = LocalDateTime.now();
        }

        if (Integer.parseInt(id) > lastId) {
            lastId = Integer.parseInt(id);
        }

        return task;
    }



    public String toString() {
        return "Task: " + this.id + " - " + this.desc + " - " + this.createdAt + " - " + this.updatedAt + " - " + this.status;
    }


}
