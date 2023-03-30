package etec.com.br.gustavohj.phaser.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import etec.com.br.gustavohj.phaser.MainActivity;
import etec.com.br.gustavohj.phaser.Model.ToDoModel;
import etec.com.br.gustavohj.phaser.R;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private MainActivity activity;


    public ToDoAdapter(MainActivity Activity) {
        this.activity = activity;
    }

    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quest_layout, parent, false);

        return new RecyclerView.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

    }

}
