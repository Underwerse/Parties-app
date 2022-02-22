package fi.example.parties.recyclerviews

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.R

class MembersAdapter(private val members : List<String>, private val onClickListener: MemberOnClickListener):
	RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {
	inner class MembersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
		val memberName: TextView = itemView.findViewById(R.id.tv_member_name)
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
		val itemView = LayoutInflater.from(parent.context)
			.inflate(R.layout.member_item, parent, false)
		
		return MembersViewHolder(itemView)
	}
	
	override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
		val member = members[position]
		holder.memberName.text = member
		holder.memberName.setOnClickListener {
			onClickListener.onClick(member)
		}
	}
	
	override fun getItemCount() = members.size
}