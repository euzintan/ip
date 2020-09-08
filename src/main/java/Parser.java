import java.io.IOException;
import java.util.Scanner;

public class Parser {
    /**
     * Returns appropriate task after processing userInput. Calls on ui to give user a response
     * @param userInput
     * @param taskList
     * @param ui
     * @return Task to be used by Duke instance
     * @throws ToDoException
     * @throws deadlineException
     * @throws eventException
     */
    public Task processAddTaskInput(String userInput, TaskList taskList, Ui ui) throws ToDoException, deadlineException, eventException {
        String taskType = userInput.split(" ")[0];
        Task thisTask = null;
        switch (taskType) {
            case "todo":
                if (userInput.equals("todo")) throw new ToDoException();
                thisTask = new Task(userInput.replace("todo ", ""));
                ui.showToDoMessage(thisTask, taskList);
                return thisTask;
            case "deadline":
                if (userInput.equals("deadline")) {
                    throw new deadlineException();
                }
                String[] StringArr = userInput.split(" /by");
                thisTask = new Deadline(StringArr[0].replace("deadline ", ""), StringArr[1]);
                ui.showDeadlineMessage(thisTask, taskList);
                return thisTask;
            case "event":
                if (userInput.equals("event")) throw new eventException();
                StringArr = userInput.split(" /at");
                thisTask = new Event(StringArr[0].replace("event ", ""), StringArr[1]);
                ui.showEventMessage(thisTask, taskList);
        }
        return thisTask;
    }

    /**
     * Processes userInput and calls on ui to give appropriate response to the user
     * @param userInput
     * @param taskList
     * @param ui
     * @throws IOException
     * @throws DukeException
     */
    public void processOtherActionInput(String userInput, TaskList taskList, Ui ui) throws IOException, DukeException {
        String actionType = userInput.split(" ")[0];
        switch (actionType) {
            case "done" :
                if (userInput.equals("done")) throw new DukeException();
                int doneIndex = Integer.parseInt(userInput.replace("done ", ""));
                assert doneIndex >= 1 : "Index must be a positive value";
                taskList.taskCompleted(doneIndex);
                System.out.println("\n");
                break;
            case "delete" :
                if (userInput.equals("delete")) throw new deleteException();
                int indexDeleted = Integer.parseInt(userInput.replace("delete ", ""));
                assert indexDeleted >= 1 : "Index must be a positive value";
                taskList.deleteTask(indexDeleted);
                break;
            case "find" :
                if (userInput.equals("find")) throw new searchException();
                break;
            default :
                throw new DukeException();
        }
    }
}
