package etec.com.br.gustavohj.phaser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import etec.com.br.gustavohj.phaser.Adapter.ToDoAdapter;

/**
 * The RecyclerItemTouchHelper class is an implementation of the ItemTouchHelper.SimpleCallback
 * that handles swipe gestures on the RecyclerView items.
 * It provides the logic for deleting and editing to-do items.
 */
public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ToDoAdapter adapter;

    /**
     * Constructs a new RecyclerItemTouchHelper instance.
     *
     * @param adapter the ToDoAdapter instance to be used
     */
    RecyclerItemTouchHelper(ToDoAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    /**
     * Handles the movement of items in the RecyclerView.
     * This method is not used in this implementation.
     *
     * @param recyclerView the RecyclerView
     * @param viewHolder the ViewHolder being moved
     * @param target the target ViewHolder
     * @return false, indicating that the items cannot be moved
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    /**
     * Handles the swiping of items in the RecyclerView.
     * Deletes the item if swiped to the left, or edits the item if swiped to the right.
     *
     * @param viewHolder the ViewHolder that was swiped
     * @param direction the direction of the swipe (left or right)
     */
    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            // Show a confirmation dialog before deleting the item
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());

            builder.setTitle("Delete Quest");
            builder.setMessage("Are you sure you want to delete this Quest?");

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    adapter.deleteItem(position);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            adapter.editItem(position);
        }
    }

    /**
     * Draws the background and icons for the swiped item in the RecyclerView.
     *
     * @param c the Canvas to draw on
     * @param recyclerView the RecyclerView
     * @param viewHolder the ViewHolder being drawn
     * @param dX the horizontal displacement of the view
     * @param dY the vertical displacement of the view
     * @param actionState the current action state (drag or swipe)
     * @param isCurrentlyActive whether the view is currently active
     */
    @Override
    public void onChildDraw(
            Canvas c,
            RecyclerView recyclerView,
            RecyclerView.ViewHolder viewHolder,
            float dX,
            float dY,
            int actionState,
            boolean isCurrentlyActive
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        Drawable icon;
        ColorDrawable background;

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        if (dX > 0) {
            // Swipe to the right (edit)
            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.baseline_edit);
            background = new ColorDrawable(ContextCompat.getColor(adapter.getContext(), R.color.colorPrimaryDark));
        } else {
            // Swipe to the left (delete)
            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.baseline_delete);
            background = new ColorDrawable(Color.RED);
        }

        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX > 0) {
            // Swipe to the right
            int iconLeft = itemView.getLeft() + iconMargin;
            int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicHeight();

            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(
                    itemView.getLeft(),
                    itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                    itemView.getBottom()
            );
        } else if (dX < 0) {
            // Swipe to the left
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;

            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(
                    itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(),
                    itemView.getRight(),
                    itemView.getBottom()
            );
        } else {
            // No swipe
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(c);
        icon.draw(c);
    }
}