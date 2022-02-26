package fi.example.parties.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.example.parties.room.data.ApiHelper
import fi.example.parties.room.data.ApiRepository

class ApiVMFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
	
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ApiVM::class.java)) {
			return ApiVM(ApiRepository(apiHelper)) as T
		}
		throw IllegalArgumentException("Unknown class name")
	}
	
}