import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class TaskWriter {
    private static final String FILE_PATH = "tasks.json";
    private List<Task> tasks;

    TaskWriter(){
        tasks = new ArrayList<>();
        if (!Files.exists(Paths.get(FILE_PATH))) {
            createEmptyFile();
        }
        readTasksFromFile();
    }

    private void createEmptyFile() {
        try {
            Files.createFile(Paths.get(FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readTasksFromFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            content = content.strip();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
            }
            if (!content.isEmpty()) {
                String[] jsonTasks = content.split("\\},\\s*\\{");
                for (String jsonTask : jsonTasks) {
                    jsonTask = jsonTask.trim();
                    if (!jsonTask.startsWith("{")) {
                        jsonTask = "{" + jsonTask;
                    }
                    if (!jsonTask.endsWith("}")) {
                        jsonTask = jsonTask + "}";
                    }
                    Task task = Task.fromJson(jsonTask);
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Task createTask(String desc) {
        Task task = new Task(desc);
        return task;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void writeTasksToFile() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(tasks.get(i).toJson());
                if (i < tasks.size() - 1) {
                    sb.append(",\n");
                }
            }
            sb.append("\n]");
            Files.write(Paths.get(FILE_PATH), sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTaskDesc(int id, String newDesc) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDesc(newDesc);
                break;
            }
        }
    }

    public void updateTaskStatus(int id, String newStatus) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(Status.valueOf(newStatus));
                break;
            }
        }
    }

    public void listAllTasks(){
        for (Task task : tasks){
            System.out.println(task);
        }
    }

    public void listTasks(String status){
        for (Task task : tasks){
            if (task.getStatus().getValue().equals(status))
            {
                System.out.println(task);
            }
        }
    }

    public void deleteTask(int id){
        for (Task task : tasks){
            if (task.getId() == id){
                tasks.remove(id-1);
                break;
            }
        }
    }
}