package fi.example.parties.screens

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import fi.example.parties.R
import fi.example.parties.data.ImagesRepository
import fi.example.parties.databinding.FragmentMemberInfoBinding
import fi.example.parties.room.DB
import fi.example.parties.room.entities.Image
import fi.example.parties.room.entities.ImageDao
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.viewmodels.MemberInfoVM
import fi.example.parties.viewmodels.MemberInfoVMFactory
import kotlinx.coroutines.launch

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Fragment for parliament member's data screen
 */
class MemberInfoFragment: Fragment() {
    private val bundle = Bundle()
    private lateinit var binding: FragmentMemberInfoBinding
    private lateinit var vmMemberInfo: MemberInfoVM
    private lateinit var vmMemberInfoFactory: MemberInfoVMFactory
    private lateinit var imagesRepository: ImagesRepository
    private lateinit var imageDao: ImageDao

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_member_info,container,false)
    
        val application = Application() // for using the app-context inside this Fragment
        
        // get data from previous View
        val selectedMemberParty = arguments?.getString("selectedParty") ?: ""
        val selectedMemberPersNumber = arguments?.getInt("memberPersNumber") ?: 0
    
        // put data for transit to back View when Back-btn clicked
        bundle.putString("selectedParty", selectedMemberParty)
        
        vmMemberInfoFactory = MemberInfoVMFactory(application, selectedMemberPersNumber)
        vmMemberInfo = ViewModelProvider(this, vmMemberInfoFactory)
            .get(MemberInfoVM::class.java)
        imageDao = DB.getInstance(requireContext()).imageDao
        imagesRepository = ImagesRepository(imageDao)
        binding.memberInfoVM = vmMemberInfo
        
        // set textview scrolling ability (when a lot of text inside)
        binding.tvNote.movementMethod = ScrollingMovementMethod()
        
        vmMemberInfo.memberByPersNumber.observe(viewLifecycleOwner) {
            updateMemberData(it)
        }
        
        vmMemberInfo.memberRating.observe(viewLifecycleOwner) {
            binding.tvMemberRating.text = "Current rating: ${it?.toString() ?: "0"}/3"
        }
    
        // set textview scrolling ability (when a lot of text inside)
        binding.tvNote.movementMethod = ScrollingMovementMethod()
    
        vmMemberInfo.memberRatingObj.observe(viewLifecycleOwner) {
            binding.tvNote.text = it?.note ?: ""
        }
        
        binding.btnSendNote.setOnClickListener {
            val note = binding.etNote.text
            vmMemberInfo.onSetNote(note.toString())
        }

        binding.btnToBack.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(R.id.action_memberInfoFragment_to_membersFragment, bundle)
        }

        binding.btnToMain.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(R.id.action_memberInfoFragment_to_titleFragment)
        }

        return binding.root
    }
    
    /**
     * Updates TextView-fields, sets member's image
     */
    private fun updateMemberData(member: PartyMember) {
        val imgResName = "@drawable/" + member.party
        val imageID = resources.getIdentifier(imgResName, "drawable", activity?.getPackageName())
        binding.imgParty.setImageResource(imageID)
        binding.tvMemberName.text = member.last + ", " + member.first + ", " + member.bornYear
        binding.tvDistrict.text = "District: " + member.constituency
        binding.tvMemberTwitter.text =
            "Twitter: " + if (member.twitter != "") member.twitter else "none"
        setImage(member)
    }
    
    /**
     * Sets image to ImageView
     */
    private fun setImage(member: PartyMember) {
        lifecycleScope.launch {
            insertImage(member.personNumber, getBitmap(member.picture))
            binding.imgMember.load(getBitmap(member.picture))
        }
    }
    
    /**
     * Inserts image into DB
     */
    private fun insertImage(persNumber: Int, image: Bitmap) {
        lifecycleScope.launch {
            imagesRepository.setImage(Image(persNumber, image))
        }
    }
    
    /**
     * Returns image from network by API
     */
    private suspend fun getBitmap(imageUrl: String): Bitmap {
        val BASE_URI = "https://avoindata.eduskunta.fi/"
        val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data(BASE_URI + imageUrl)
            .build()
        
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}