package fi.example.parties.screens

import android.app.Application
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.example.parties.R
import fi.example.parties.databinding.FragmentMemberInfoBinding
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.viewmodels.MemberInfoVM
import fi.example.parties.viewmodels.MemberInfoVMFactory

class MemberInfoFragment: Fragment() {
    val bundle = Bundle()
    private lateinit var binding: FragmentMemberInfoBinding
    private lateinit var vmMemberInfo: MemberInfoVM
    private lateinit var vmMemberInfoFactory: MemberInfoVMFactory

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_member_info,container,false)
    
        val application = Application()
        
        val selectedMemberParty = arguments?.getString("selectedParty") ?: ""
        val selectedMemberPersNumber = arguments?.getInt("memberPersNumber") ?: 0
    
        bundle.putString("selectedParty", selectedMemberParty)
        
        vmMemberInfoFactory = MemberInfoVMFactory(application, selectedMemberPersNumber)
        vmMemberInfo = ViewModelProvider(this, vmMemberInfoFactory)
            .get(MemberInfoVM::class.java)
        binding.memberInfoVM = vmMemberInfo
        binding.tvNote.movementMethod = ScrollingMovementMethod()
        
        vmMemberInfo.memberByPersNumber.observe(viewLifecycleOwner) {
            updateMemberData(it)
        }
        
        vmMemberInfo.memberRating.observe(viewLifecycleOwner) {
            binding.tvMemberRating.text = "Current rating: ${it?.toString() ?: "none"}/3"
        }
    
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
    
    private fun updateMemberData(member: PartyMember) {
        val imgResName = "@drawable/" + member.party
        val imageID = resources.getIdentifier(imgResName, "drawable", activity?.getPackageName())
        binding.imgParty.setImageResource(imageID)
        binding.tvMemberName.text = member.last + ", " + member.first + ", " + member.bornYear
//        binding.tvMemberBirthYear.text = "Borned in " + member.bornYear.toString()
        binding.tvDistrict.text = "District: " + member.constituency
        binding.tvMemberTwitter.text = "Twitter: " +
                if (member.twitter != "") member.twitter else "none"
    }
}