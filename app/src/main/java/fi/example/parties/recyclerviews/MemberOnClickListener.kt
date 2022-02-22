package fi.example.parties.recyclerviews

import fi.example.parties.room.entities.PartyMember

interface MemberOnClickListener {
	fun onClick(member: String)
}