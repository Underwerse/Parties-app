package fi.example.parties

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
        
        vmMemberInfo.memberByPersNumber.observe(viewLifecycleOwner) {
            updateMemberData(it)
        }

        binding.btnRandom.setOnClickListener { view : View ->
//            vmPartyMember.setMember()
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
        binding.tvMemberName.text = member.last + ", " + member.first
        binding.tvMemberBirthYear.text = "Borned in " + member.bornYear.toString()
        binding.tvDistrict.text = "District: " + member.constituency
        binding.tvMemberTwitter.text = "Twitter: " +
                if (member.twitter != "") member.twitter else "none"
    }
}