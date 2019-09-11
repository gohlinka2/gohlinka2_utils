package cz.hlinkapp.gohlinka2_utils2.fragments.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import cz.hlinkapp.gohlinka2_utils2.dialogs.ActionCompletedDialog
import cz.hlinkapp.gohlinka2_utils2.dialogs.ErrorMessageDialog
import cz.hlinkapp.gohlinka2_utils2.dialogs.InfoDialog

/**
 * A base Fragment class containing several utilities.
 * Almost all of the app's fragments should implement this class.
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDependencies(savedInstanceState)
        initViews(savedInstanceState)
    }

    /**
     * Child fragments should initialize their views in this method. This will get called automatically in [onActivityCreated] of the Fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    protected open fun initViews(savedInstanceState: Bundle?) {}

    /**
     * Child fragments should initialize their primary dependencies, such as the ViewModel, in this method.
     * This is also the appropriate place to put Dagger injection.
     * This is called automatically in [onActivityCreated] of the Fragment, but before [initViews], so that you can use the initialised dependencies for your views.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    protected open fun initDependencies(savedInstanceState: Bundle?) {}

    @get:LayoutRes
    protected abstract val layoutId : Int

    /**
     * Shows an error message dialog.
     * @param titleText The error title to display.
     * @param descriptionText The error description to display.
     */
    protected fun showErrorDialog(titleText: String, descriptionText : String) {
        val bundle = Bundle()
        bundle.putString(ErrorMessageDialog.ARG_TITLE_TEXT, titleText)
        bundle.putString(ErrorMessageDialog.ARG_DESCRIPTION_TEXT,descriptionText)
        val dialog = ErrorMessageDialog()
        dialog.arguments = bundle
        dialog.show(activity!!.supportFragmentManager, ErrorMessageDialog.TAG)
    }

    /**
     * Shows an error message dialog with an exception text.
     * @param titleText The error title to display.
     * @param descriptionText The error description to display.
     * @param exceptionText The name of the exception that happened.
     */
    protected fun showErrorDialog(titleText: String, descriptionText : String, exceptionText : String?) {
        val bundle = Bundle()
        bundle.putString(ErrorMessageDialog.ARG_TITLE_TEXT, titleText)
        bundle.putString(ErrorMessageDialog.ARG_DESCRIPTION_TEXT,descriptionText)
        bundle.putString(ErrorMessageDialog.ARG_EXCEPTION_TEXT,exceptionText)
        val dialog = ErrorMessageDialog()
        dialog.arguments = bundle
        dialog.show(activity!!.supportFragmentManager, ErrorMessageDialog.TAG)
    }

    /**
     * Returns true if the error dialog is currently shown, false otherwise.
     */
    protected fun isErrorDialogShown() : Boolean {
        activity?.supportFragmentManager?.executePendingTransactions()
        val dialogFragment = activity?.supportFragmentManager?.findFragmentByTag(ErrorMessageDialog.TAG) as androidx.fragment.app.DialogFragment?
        return dialogFragment?.isVisible ?: false
    }

    /**
     * Shows an error message dialog with default message texts.
     */
    protected fun showErrorDialog() {
        val dialog = ErrorMessageDialog()
        dialog.show(activity!!.supportFragmentManager, ErrorMessageDialog.TAG)
    }

    /**
     * Shows an info message dialog.
     * @param titleText The info title to display.
     * @param descriptionText The info description to display.
     */
    protected fun showInfoDialog(titleText: String, descriptionText : String) {
        val bundle = Bundle()
        bundle.putString(InfoDialog.ARG_TITLE_TEXT, titleText)
        bundle.putString(InfoDialog.ARG_DESCRIPTION_TEXT,descriptionText)
        val dialog = InfoDialog()
        dialog.arguments = bundle
        dialog.show(activity!!.supportFragmentManager, InfoDialog.TAG)
    }

    /**
     * Shows an action-completed message dialog.
     * @param titleText The title to display.
     * @param descriptionText The description to display.
     */
    protected fun showActionCompletedDialog(titleText: String, descriptionText : String) {
        val bundle = Bundle()
        bundle.putString(InfoDialog.ARG_TITLE_TEXT, titleText)
        bundle.putString(InfoDialog.ARG_DESCRIPTION_TEXT,descriptionText)
        val dialog = ActionCompletedDialog()
        dialog.arguments = bundle
        dialog.show(activity!!.supportFragmentManager, ActionCompletedDialog.TAG)
    }

}