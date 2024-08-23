package etec.com.br.gustavohj.phaser.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import etec.com.br.gustavohj.phaser.Model.ToDoModel;

/**
 * The `DatabaseHandler` class is responsible for managing the SQLite database used to store the to-do list items.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String NAME = "toDoListDatabase";
    public static final String TODO_TABLE = "todo";
    public static final String ID = "id";
    public static final String TASK = "task";
    public static final String STATUS = "status";
    public static final String CREATE_TODO_TABLE = "" +
            "CREATE TABLE " + TODO_TABLE + " ( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + "," +
            TASK + " TEXT" + "," +
            STATUS + " INTEGER"
            + ")";
    public static final String CLAUSE = "=?";
    public static final String DROP_TODO_TABLE = "DROP TABLE IF EXISTS " + TODO_TABLE;

    private SQLiteDatabase db;

    /**
     * Constructs a new `DatabaseHandler` instance with the provided context.
     *
     * @param context the context of the application
     */
    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * Executes the CREATE_TODO_TABLE query to create the to-do table.
     *
     * @param sqLiteDatabase the SQLiteDatabase instance
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute the CREATE_TODO_TABLE query to create the to-do table
        db.execSQL(CREATE_TODO_TABLE);
    }

    /**
     * Called when the database needs to be upgraded.
     * Deletes the old to-do table and creates a new one using the onCreate method.
     *
     * @param sqLiteDatabase the SQLiteDatabase instance
     * @param oldVersion     the old version of the database
     * @param newVersion     the new version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Delete the old to-do table
        db.execSQL(DROP_TODO_TABLE);

        // Create the new to-do table
        onCreate(db);
    }

    /**
     * Opens the database for writing operations.
     */
    public void openDatabase() {
        // Get the writable database instance
        db = this.getWritableDatabase();
    }

    /**
     * Inserts a new task into the to-do list.
     *
     * @param task the to-do task to be inserted
     */
    public void insertTask(ToDoModel task) {
        // Create a ContentValues object to hold the task data
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getQuest());
        cv.put(STATUS, 0);

        // Insert the task data into the to-do table
        db.insert(TODO_TABLE, null, cv);
    }

    /**
     * Retrieves all the tasks from the to-do list.
     *
     * @return a list of all the tasks
     */
    @SuppressLint("Range")
    public List<ToDoModel> getAllTask() {
        List<ToDoModel> toDoList = new ArrayList<>();
        Cursor cursor = null;

        db.beginTransaction();
        try {
            // Create a cursor to retrieve the data from the to-do table
            cursor = db.query(TODO_TABLE, null, null, null, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        // Create a new to-do task and add it to the list
                        ToDoModel task = new ToDoModel();
                        task.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        task.setQuest(cursor.getString(cursor.getColumnIndex(TASK)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(STATUS)));
                        toDoList.add(task);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            cursor.close();
        }

        return toDoList;
    }

    /**
     * Updates the status of a task in the to-do list.
     *
     * @param id     the ID of the task to be updated
     * @param status the new status of the task
     */
    public void updateStatus(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);

        db.update(TODO_TABLE, cv, ID + CLAUSE, new String[]{String.valueOf(id)});
    }

    /**
     * Updates the task description of a task in the to-do list.
     *
     * @param id   the ID of the task to be updated
     * @param task the new task description
     */
    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);

        db.update(TODO_TABLE, cv, ID + CLAUSE, new String[]{String.valueOf(id)});
    }

    /**
     * Deletes a task from the database based on the provided ID.
     *
     * @param id the ID of the task to be deleted
     */
    public void deleteTask(int id) {
        db.delete(TODO_TABLE, ID + CLAUSE, new String[]{String.valueOf(id)});
    }
}
