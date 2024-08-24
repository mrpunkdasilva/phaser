package etec.com.br.gustavohj.phaser.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import etec.com.br.gustavohj.phaser.AddNewTask;
import etec.com.br.gustavohj.phaser.MainActivity;
import etec.com.br.gustavohj.phaser.Model.ToDoModel;
import etec.com.br.gustavohj.phaser.R;
import etec.com.br.gustavohj.phaser.Utils.DatabaseHandler;

/**
 * The ToDoAdapter class is a RecyclerView adapter that manages the display of to-do items.
 * It provides the logic for creating and binding the view holders for the to-do items.
 */
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList; // The list of to-do items
    private final MainActivity activity; // The reference to the MainActivity
    private final DatabaseHandler db;

    /**
     * Constructs a new ToDoAdapter instance.
     *
     * @param mainActivity the reference to the MainActivity
     */
    public ToDoAdapter(DatabaseHandler db, MainActivity mainActivity) {
        this.db = db;
        this.activity = mainActivity;
    }

    /**
     * Creates a new ViewHolder instance for the to-do item.
     *
     * @param parent   the parent ViewGroup
     * @param viewType the view type
     * @return the created ViewHolder instance
     */
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quest_layout, parent, false);

        return new ViewHolder(itemView);
    }

    /**
     * Binds the data for the to-do item to the ViewHolder.
     *
     * @param holder   the ViewHolder instance
     * @param position the position of the item in the list
     */
    public void onBindViewHolder(ViewHolder holder, int position) {
        db.openDatabase();

        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                db.updateStatus(item.getId(), 1);
            } else {
                db.updateStatus(item.getId(), 0);
            }
        });
    }

    /**
     * Returns the number of items in the to-do list.
     *
     * @return the size of the to-do list
     */
    public int getItemCount() {
        return todoList.size();
    }

    /**
     * Converts a numeric status value to a boolean.
     *
     * @param number the numeric status value
     * @return true if the status is non-zero, false otherwise
     */
    private boolean toBoolean(int number) {
        return number != 0;
    }

    /**
     * Sets the list of to-do items and notifies the adapter of the data change.
     *
     * @param todoList the list of to-do items
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    /**
     * Edits an existing to-do item.
     *
     * @param position the position of the item in the to-do list
     */
    public void editItem(int position) {
        // Get the to-do item at the specified position
        ToDoModel item = todoList.get(position);

        // Create a new bundle to pass the item data to the AddNewTask fragment
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        // Create a new AddNewTask fragment and set the arguments
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);

        // Show the AddNewTask fragment
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    /**
     * Returns the context of the MainActivity.
     *
     * @return the context of the MainActivity
     */
    public Context getContext() {
        return activity;
    }

    /**
     * Deletes a to-do item from the list and the database.
     *
     * @param position the position of the item to be deleted
     */
    public void deleteItem(int position) {
        // Get the to-do item at the specified position
        ToDoModel item = todoList.get(position);

        // Delete the task from the database
        db.deleteTask(item.getId());

        // Remove the item from the to-do list and notify the adapter of the item removal
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * The ViewHolder class holds the views for a single to-do item.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task; // The CheckBox view for the to-do item

        /**
         * Constructs a new ViewHolder instance.
         *
         * @param view the view for the to-do item
         */
        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}