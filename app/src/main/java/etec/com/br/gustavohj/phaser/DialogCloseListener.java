package etec.com.br.gustavohj.phaser;

import android.content.DialogInterface;

/**
 * The DialogCloseListener interface is used to notify the parent activity when a dialog is closed.
 * The parent activity should implement this interface to handle the dialog close event.
 */
public interface DialogCloseListener {
    /**
     * Handles the closure of a dialog.
     *
     * @param dialog the dialog that was closed
     */
    void handleDialogClose(DialogInterface dialog);
}