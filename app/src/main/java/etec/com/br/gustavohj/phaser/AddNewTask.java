package etec.com.br.gustavohj.phaser;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import etec.com.br.gustavohj.phaser.Model.ToDoModel;
import etec.com.br.gustavohj.phaser.Utils.DatabaseHandler;

/**
 * The AddNewTask class represents a bottom sheet dialog fragment for adding or editing a to-do item.
 * It provides the UI and logic for creating and updating to-do items.
 */
public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private Button newTaskSaveButton;

    private DatabaseHandler db;

    /**
     * Creates a new instance of the AddNewTask fragment.
     *
     * @return a new instance of the AddNewTask fragment
     */
    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    /**
     * Called when the fragment is created.
     * Sets the style of the dialog.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    /**
     * Creates the view for the dialog.
     *
     * @param inflater the LayoutInflater to inflate the view
     * @param container the parent ViewGroup
     * @param savedInstanceState the saved instance state
     * @return the created view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_quest, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    /**
     * Called when the view is created.
     * Initializes the UI components and sets up the logic for adding or editing a to-do item.
     *
     * @param view the view created
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = Objects.requireNonNull(getView()).findViewById(R.id.newTaskText);
        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            assert task != null;
            if(task.length()>0) {
                newTaskSaveButton.setTextColor(
                        ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark)
                );
            }
        }

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("") || s.toString().isEmpty() || s.toString().equals(" ")){
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryDark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(v -> {
            String text = newTaskText.getText().toString();
            if(finalIsUpdate){
                db.updateTask(bundle.getInt("id"), text);
            }
            else {
                ToDoModel task = new ToDoModel();
                task.setTask(text);
                task.setStatus(0);
                db.insertTask(task);
            }
            dismiss();
        });
    }

    /**
     * Called when the dialog is dismissed.
     * Notifies the parent activity of the dialog close event.
     *
     * @param dialog the dialog that was dismissed
     */
    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener) {
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
