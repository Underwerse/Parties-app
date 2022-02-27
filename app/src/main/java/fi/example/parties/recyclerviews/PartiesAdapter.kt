package fi.example.parties.recyclerviews

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.MainActivity.Companion.appContext
import fi.example.parties.R

class PartiesAdapter(private val parties : List<String>, private val onClickListener: PartyOnClickListener):
    RecyclerView.Adapter<PartiesAdapter.PartiesViewHolder>() {
    inner class PartiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val partyName: TextView = itemView.findViewById(R.id.tv_party_name)
        val partyImage: ImageView = itemView.findViewById(R.id.party_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.party_item, parent, false)

        return PartiesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        val party = parties[position]
        holder.partyName.text = getPartyFullName(party)
        holder.partyImage.setImageResource(getDrawableResourse(party))
        holder.partyImage.setOnClickListener {
            onClickListener.onClick(party)
        }
    }

    override fun getItemCount() = parties.size
    
    private fun getDrawableResourse(resName: String): Int {
        val drawableResource = when (resName) {
            "kd" -> R.drawable.kd
            "kesk" -> R.drawable.kesk
            "kok" -> R.drawable.kok
            "ps" -> R.drawable.ps
            "r" -> R.drawable.r
            "sd" -> R.drawable.sd
            "vas" -> R.drawable.vas
            "vihr" -> R.drawable.vihr
            else -> R.drawable.politics
        }
        
        return drawableResource
    }
    
    private fun getPartyFullName(shortName: String): String {
        val fullName = when (shortName) {
            "kd" -> "Christian Democrat"
            "kesk" -> "Centre"
            "kok" -> "National Coalition"
            "ps" -> "Finns"
            "r" -> "Swedish People's"
            "sd" -> "Social Democratic"
            "vas" -> "Left Alliance"
            "vihr" -> "Green League"
            else -> "Movement Now"
        }
        
        return fullName
    }
}