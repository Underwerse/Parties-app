package fi.example.parties.viewmodels

import androidx.lifecycle.ViewModel
import fi.example.parties.room.data.ApiRepository
import androidx.lifecycle.liveData
import fi.example.parties.room.data.Resource
import kotlinx.coroutines.Dispatchers

class ApiVM(private val apiRepository: ApiRepository) : ViewModel() {
	
	fun getMembers() = liveData(Dispatchers.IO) {
		emit(Resource.loading(data = null))
		try {
			emit(Resource.success(data = apiRepository.getMembers()))
		} catch (exception: Exception) {
			emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
		}
	}
}