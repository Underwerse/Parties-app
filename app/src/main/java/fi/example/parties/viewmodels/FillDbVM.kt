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

// Status for future use
enum class MembersApiStatus { LOADING, ERROR, DONE }

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * ViewModel for FillDb View
 */
class FillDbVM(application: Application) : AndroidViewModel(application) {
    
    private val _membersFromFile = ParliamentMembersData.members // get data from local file
    private val _membersFromNetwork = MutableLiveData<List<Member>>()
    private val _status = MutableLiveData<MembersApiStatus>()
    
    val membersFromFile: List<MemberOfParliament>
        get() = _membersFromFile
    val membersFromNetwork: LiveData<List<Member>>
        get() = _membersFromNetwork
    
    init {
        getMembersFromNetwork()
    }
    
    /**
     * Retrieve data from network by API
     */
    private fun getMembersFromNetwork() {
        GlobalScope.launch {
            _status.postValue(MembersApiStatus.LOADING)
            
            try {
            	_membersFromNetwork.postValue(MembersApi.RETROFIT_SERVICE_MEMBERS.getMembers())
                _status.postValue(MembersApiStatus.DONE)
            } catch (e: Exception) {
                _status.postValue(MembersApiStatus.ERROR)
                _membersFromNetwork.postValue(ArrayList())
            }
        }
    }
}