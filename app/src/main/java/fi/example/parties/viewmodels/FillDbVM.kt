package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import fi.example.parties.MemberOfParliament
import fi.example.parties.ParliamentMembersData
import fi.example.parties.retrofit.Member
import fi.example.parties.retrofit.MembersApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

enum class MembersApiStatus { LOADING, ERROR, DONE }

class FillDbVM(application: Application) : AndroidViewModel(application) {
    
    private val _membersFromFile: List<MemberOfParliament>
    private val _membersFromNetwork = MutableLiveData<List<Member>>()
    private val _status = MutableLiveData<MembersApiStatus>()
    
    val membersFromFile: List<MemberOfParliament>
        get() = _membersFromFile
    val membersFromNetwork: LiveData<List<Member>>
        get() = _membersFromNetwork
    
    init {
        Log.i("LOG", "FillDbVM created")
        _membersFromFile = ParliamentMembersData.members
        getMembersFromNetwork()
    }
    
    override fun onCleared() {
        super.onCleared()
        Log.i("LOG", "FillDbVM destroyed")
    }
    
    private fun getMembersFromNetwork() {
        Log.d("LOG", "Getting from API start")
    
        GlobalScope.launch {
            _status.postValue(MembersApiStatus.LOADING)
            
            try {
            	_membersFromNetwork.postValue(MembersApi.RETROFIT_SERVICE_MEMBERS.getMembers())
                _status.postValue(MembersApiStatus.DONE)
                Log.d("LOG", "Getting members from API done")
            } catch (e: Exception) {
                _status.postValue(MembersApiStatus.ERROR)
                _membersFromNetwork.postValue(ArrayList())
            }
        }
    }
}