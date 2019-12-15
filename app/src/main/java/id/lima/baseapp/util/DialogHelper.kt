package id.lima.baseapp.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import com.bumptech.glide.Glide
import id.lima.baseapp.R
import id.lima.baseapp.interfaces.DialogClickListener

/**
 * Created with love by muhwid on 04/02/19.
 */
class DialogHelper {
    fun initCustomDialog(context: Context?, dialogView: View?): AppCompatDialog {
        val dialog = AppCompatDialog(context, R.style.CustomDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogView)
        dialog.setCanceledOnTouchOutside(true)
        if (dialog.window != null) {
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return dialog
    }

    fun showDialogYesNo(context: Context, image: Int?, title: String, content: String, btnPositive: String?, btnNegative: String?, listener: DialogClickListener?) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_failed)

        val ivDialog = dialog.findViewById(R.id.ivDialog) as ImageView
        val tvTitle = dialog.findViewById(R.id.tvTitle) as TextView
        val tvContent = dialog.findViewById(R.id.tvContent) as TextView
        val posButton = dialog.findViewById(R.id.btnPositive) as TextView
        val negButton = dialog.findViewById(R.id.btnNegative) as TextView

        if (image != null) {
            Glide.with(context).load(image).into(ivDialog)
        }

        posButton.visibility = if (btnPositive != null) View.VISIBLE else View.GONE
        negButton.visibility = if (btnNegative != null) View.VISIBLE else View.GONE

        tvTitle.text = title
        tvContent.text = content
        posButton.text = btnPositive
        negButton.text = btnNegative

        posButton.setOnClickListener {
            listener?.onClickYes()
            dialog.dismiss()
        }
        negButton.setOnClickListener {
            listener?.onClickNo()
            dialog.dismiss()
        }

        dialog.show()
    }

    fun showAlertDialog(context: Context?, title: String?, content: String?, yes: String?, no: String?, listener: DialogClickListener) {
        val alertDialog2 = AlertDialog.Builder(context!!)
        if (title != null) alertDialog2.setTitle(title)
        alertDialog2.setMessage(content)
        alertDialog2.setIcon(R.drawable.ic_launcher_background)
        if (yes != null) {
            alertDialog2.setPositiveButton(yes
            ) { dialog, which ->
                listener.onClickYes()
                dialog.cancel()
            }
        }
        if (no != null) {
            alertDialog2.setNegativeButton(no
            ) { dialog, which ->
                listener.onClickNo()
                dialog.cancel()
            }
        }
        alertDialog2.show()
    }

    companion object {
        var instance: DialogHelper? = null
            get() {
                if (field == null) {
                    field = DialogHelper()
                }
                return field
            }
            private set
    }
}