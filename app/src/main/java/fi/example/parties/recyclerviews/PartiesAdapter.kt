package fi.example.parties.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.R

class PartiesAdapter(private val parties : List<String>, private val onClickListener: PartyOnClickListener):
    RecyclerView.Adapter<PartiesAdapter.PartiesViewHolder>() {
    inner class PartiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val partyName: TextView = itemView.findViewById(R.id.tv_party_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.party_item, parent, false)

        return PartiesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        val party = parties[position]
        holder.partyName.text = party
        holder.partyName.setOnClickListener {
            onClickListener.onClick(party)
        }
    }

    override fun getItemCount() = parties.size
}