package cz.hlinkapp.gohlinka2_utils2.dialogs

import android.app.Dialog
import android.os.Bundle

/**
 * A InfoDialog that should be shown when an action has completed.
 * The parent activity will be finished on dismiss of this dialog.
 * Use the [ARG_TITLE_TEXT] and [ARG_DESCRIPTION_TEXT] Bundle keys to pass title-string and description-string using arguments.
 */
class ActionCompletedDialog : InfoDialog() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        onDismissListener = {activity?.finish()}
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "ActionCompletedDialog"
    }

}