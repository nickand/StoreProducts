package meli.com.meliproducts.ui.main

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Base class for fragments. It requires to implement @see clearBindings}
 */
abstract class  BaseFragment : Fragment() {

    //view to display error messages
    private var snackbar: Snackbar? = null

    override fun onDestroyView() {
        super.onDestroyView()
        clearBindings()
    }

    /**
     * Helper function to display error message.
     * @param view parent view.
     * @param message String containing the message to be displayed.
     * @param actionLabel String to display as the Action label.
     * @param duration How long to display the message.
     * @param callback function to execute on the Action callback
     */
    protected fun displayErrorMessage(
        view: View,
        message: String,
        actionLabel: String? = null,
        duration: Int = Snackbar.LENGTH_INDEFINITE,
        callback: (() -> Unit)? = null
    ) {
        snackbar = Snackbar.make(view, message, duration)
        actionLabel?.let { snackbar!!.setAction(actionLabel){
            callback?.invoke()
        } }
        snackbar!!.show()
    }

    /**
     * To avoid memory leaks, clear any view bindings.
     */
    abstract fun clearBindings()
}