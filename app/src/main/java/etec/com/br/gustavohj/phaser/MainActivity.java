package etec.com.br.gustavohj.phaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import etec.com.br.gustavohj.phaser.Adapter.ToDoAdapter;
import etec.com.br.gustavohj.phaser.Model.ToDoModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        // -> com isso faz com que set quem controla o layout em questão, ou seja, o `questsRecyclerView`
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // criando adapter das tasks
        tasksAdapter = new ToDoAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        // criando a task
        ToDoModel task = new ToDoModel();
        task.setQuest("Teste de task");
        task.setStatus(0);
        task.setId(1);

        // adicionando tasks a lista de tasks
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        // setando a lista de tarefas no ToDoAdapter
        tasksAdapter.setTasks(taskList);
    }
}