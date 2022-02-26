package fi.example.parties.room.data

import fi.example.parties.room.entities.PartyMember
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
	
	@GET("~peterh/mps.json")
	suspend fun getMembers(): List<PartyMember>
	
	object RetrofitBuilder  {
		
		var BASE_URL = "https://users.metropolia.fi/"
		
		private fun getRetrofit(): Retrofit {
			
			return Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
		}
		
		val apiService: ApiService = getRetrofit().create(ApiService::class.java)
	}
}