package etec.com.br.gustavohj.phaser.Model;

/**
 * The ToDoModel class represents a to-do item.
 * It contains the id, status, and task of the to-do item.
 */
public class ToDoModel {
    private int id; // The unique identifier of the to-do item
    private int status; // The status of the to-do item (0 for incomplete, 1 for complete)
    private String task; // The description of the to-do item

    /**
     * Returns the ID of the to-do item.
     *
     * @return the ID of the to-do item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the to-do item.
     *
     * @param id the ID of the to-do item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the status of the to-do item.
     *
     * @return the status of the to-do item
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the to-do item.
     *
     * @param status the status of the to-do item
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns the description of the to-do item.
     *
     * @return the description of the to-do item
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets the description of the to-do item.
     *
     * @param task the description of the to-do item
     */
    public void setTask(String task) {
        this.task = task;
    }
}