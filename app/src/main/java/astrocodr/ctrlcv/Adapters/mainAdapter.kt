package astrocodr.ctrlcv.Adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import astrocodr.ctrlcv.Model.mainModel
import astrocodr.ctrlcv.R
import com.parassidhu.simpledate.toDateStandardConcise

class mainAdapter(private val list: List<mainModel>)
    : RecyclerView.Adapter<mainModelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mainModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return mainModelViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: mainModelViewHolder, position: Int) {
        val movie: mainModel = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class mainModelViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.main_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null
    private var copy: ImageView? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_description)
        copy = itemView.findViewById(R.id.copy)
    }

    fun bind(model: mainModel) {
        mTitleView?.text = model.content
        mYearView?.text = model.date.toDateStandardConcise()
        copy!!.setOnClickListener{v->
            copyTextToClipboard(model.content, copy!!)
        }
    }
    private fun copyTextToClipboard(s:String, v:ImageView) {
        val clipboardManager :ClipboardManager = v.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", s)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(v.context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }
}