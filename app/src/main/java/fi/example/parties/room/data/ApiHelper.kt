package fi.example.parties.room.data

class ApiHelper(private val apiService: ApiService) {
	suspend fun getMembers() = apiService.getMembers()
}