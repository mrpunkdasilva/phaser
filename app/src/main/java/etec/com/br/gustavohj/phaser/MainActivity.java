package etec.com.br.gustavohj.phaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import etec.com.br.gustavohj.phaser.Adapter.ToDoAdapter;
import etec.com.br.gustavohj.phaser.Model.ToDoModel;
import etec.com.br.gustavohj.phaser.Utils.DatabaseHandler;

/**
 * The MainActivity class represents the main activity of the application.
 * It manages the display of tasks using a RecyclerView and a ToDoAdapter.
 */
public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView tasksRecyclerView; // The RecyclerView that displays the tasks
    private ToDoAdapter tasksAdapter; // The adapter that manages the task list
    private FloatingActionButton fab;

    private DatabaseHandler db;
    private List<ToDoModel> taskList; // The list of tasks

    /**
     * Called when the activity is created.
     * Initializes the task list, RecyclerView, and ToDoAdapter.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>(); // Initialize the task list
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView); // Find the RecyclerView
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set the layout manager

        // Create the adapter and set it to the RecyclerView
        tasksAdapter = new ToDoAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        // Create a sample task and add it to the task list
        ToDoModel task = new ToDoModel();
        task.setQuest("Teste de task");
        task.setStatus(0);
        task.setId(1);

        // Add multiple tasks to the task list
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        // Set the task list in the ToDoAdapter
        tasksAdapter.setTasks(taskList);

        fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        // Preencher as tasks no layout
        taskList = db.getAllTask();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        // adicionar ouvinte de clique ao botão flutuante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    /**
     * Handles the closure of a dialog.
     * Updates the task list, reverses the order, and notifies the adapter of the data change.
     *
     * @param dialog the dialog that was closed
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTask();

        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}