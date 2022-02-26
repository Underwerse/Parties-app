package fi.example.parties.retrofit

import fi.example.parties.room.entities.PartyMember
import retrofit2.http.GET

interface ApiService {
	
	@GET("~peterh/mps.json")
	fun getMembers() : List<PartyMember>
}