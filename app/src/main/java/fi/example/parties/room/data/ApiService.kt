package fi.example.parties.room.data

import fi.example.parties.room.entities.PartyMember
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
	
	@GET("~peterh/mps.json")
	suspend fun getMembers(): Response<PartyMember>
	
	/*@GET("https://avoindata.eduskunta.fi/")
	suspend fun getImages(): Response<PartyMember>*/
}