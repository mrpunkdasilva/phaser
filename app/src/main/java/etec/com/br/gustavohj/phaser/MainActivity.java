package etec.com.br.gustavohj.phaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView questsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        questsRecyclerView = findViewById(R.id.questsRecyclerView);
        // -> com isso faz com que set quem controla o layout em questão, ou seja, o `questsRecyclerView`
        questsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}