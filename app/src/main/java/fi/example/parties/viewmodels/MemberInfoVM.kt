package fi.example.parties.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import fi.example.parties.data.ImagesRepository
import fi.example.parties.data.MembersRepository
import fi.example.parties.data.RatingsRepository
import fi.example.parties.room.DB
import fi.example.parties.room.entities.Image
import fi.example.parties.room.entities.MemberRating
import fi.example.parties.room.entities.PartyMember
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MemberInfoVM(application: Application,
                   memberPersNumber: Int
): AndroidViewModel(application) {
    private val personNumber: Int
    private val _imageUrl: String
    private val _getMember: LiveData<PartyMember>
    private val _getRating: LiveData<Int>
    private val _getRatingObj: LiveData<MemberRating>
    private val _getBitmap = MutableLiveData<Bitmap?>()
    private val membersRepository: MembersRepository
    private val ratingsRepository: RatingsRepository
    private val imagesRepository: ImagesRepository
    
    val memberByPersNumber: LiveData<PartyMember>
        get() = _getMember
    val memberRating: LiveData<Int>
        get() = _getRating
    val memberRatingObj: LiveData<MemberRating>
        get() = _getRatingObj
    val bitmap: LiveData<Bitmap?>
        get() = _getBitmap
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        val memberRatingDao = DB.getInstance(application).memberRatingDao
        val imageDao = DB.getInstance(application).imageDao
        membersRepository = MembersRepository(partyMemberDao)
        ratingsRepository = RatingsRepository(memberRatingDao)
        imagesRepository = ImagesRepository(imageDao)
        
        personNumber = memberPersNumber
        _getMember = membersRepository.getMemberByPersNumber(personNumber)
        _imageUrl = _getMember.value?.picture ?: ""
        _getRating = ratingsRepository.getRating(personNumber)
        _getRatingObj = ratingsRepository.getRatingObj(personNumber)
        getBitmap(_imageUrl)
    }
    
    fun onSetRating(rating: Int) {
        val currentNote = (memberRatingObj.value)?.note ?: ""
        val ratingObj = MemberRating(personNumber, rating, currentNote)
        viewModelScope.launch {
            ratingsRepository.setRating(ratingObj)
        }
    }
    
    fun onSetNote(note: String) {
        val ratingObj = memberRating.value?.let {
            MemberRating(
                personNumber = personNumber,
                rating = it,
                note = note)
        }
        viewModelScope.launch {
            if (ratingObj != null) {
                ratingsRepository.setRating(ratingObj)
            }
        }
    }
    
    fun getBitmap(imageUrl: String) {
        val BASE_URI = "https://avoindata.eduskunta.fi/"

        GlobalScope.launch {
            try {
                val loading = ImageLoader(getApplication())
                val request = ImageRequest.Builder(getApplication())
                    .data(BASE_URI + imageUrl)
                    .build()
    
                val result = (loading.execute(request) as SuccessResult).drawable
                _getBitmap.postValue((result as BitmapDrawable).bitmap)
                Log.d("LOG", "bitmap got: ${result.bitmap}")
            } catch (e: Exception) {
                _getBitmap.postValue(null)
            }
        }
    }
}