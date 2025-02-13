import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        TaskWriter taskWriter = new TaskWriter();

        

        Scanner sc = new Scanner(System.in);
        int x = 0;
        while (x == 0) {
            System.out.println("1. Add task");
            System.out.println("2. Update task description");
            System.out.println("3. Update task status");
            System.out.println("4. Delete task");
            System.out.println("5. List all tasks");
            System.out.println("6. List tasks by status");
            System.out.println("7. Exit");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice;

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter task description: ");
                    String desc = sc.nextLine();
                    // Add task to json file
                    Task task = taskWriter.createTask(desc);
                    taskWriter.addTask(task);
                    break;
                case 2:
                    System.out.println("Enter task id to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new task description: ");
                    String newDesc = sc.nextLine();
                    // Update task in json file
                    taskWriter.updateTaskDesc(updateId, newDesc);
                    break;
                case 3:
                    System.out.println("Enter task id to update: ");
                    int taskId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new task status(TODO,IN_PROGRESS,DONE): ");
                    String newStatus = sc.nextLine();
                    // Update task in json file
                    taskWriter.updateTaskStatus(taskId, newStatus);
                    break;
                case 4:
                    System.out.println("Enter task id to delete: ");
                    int deleteId = sc.nextInt();
                    // Delete task from json file
                    taskWriter.deleteTask(deleteId);
                    break;
                case 5:
                    // list all tasks from json file
                    taskWriter.listAllTasks();
                    break;
                case 6:
                    System.out.println("Enter status (Todo,In progress,Done) to list tasks: ");
                    String status = sc.nextLine();
                    // list tasks by status from json file
                    taskWriter.listTasks(status);
                    break;

                case 7:
                    taskWriter.writeTasksToFile();
                    x = 1;
                    break;
            
                default:
                    break;
            }
        }
    }
        
}