package fi.example.parties

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.example.parties.databinding.FragmentPartyDetailsBinding
import fi.example.parties.viewmodels.PartyMemberVM
//import fi.example.parties.viewmodels.PartyMemberVMFactory

class PartyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPartyDetailsBinding
    private lateinit var vmPartyMember: PartyMemberVM
//    private lateinit var vmPartyMemberFactory: PartyMemberVMFactory
    private lateinit var selectedParty: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_party_details,container,false)

        Log.i("ParliamentMemberViewModel", "Called ViewModelProvider.get()")
        selectedParty = arguments?.getString("selectedParty") ?: ""

        binding.tvPartyTitle.text = "Selected party: " + selectedParty

//        vmPartyMemberFactory = PartyMemberVMFactory(selectedParty)
//        vmPartyMember = ViewModelProvider(this,vmPartyMemberFactory)
//            .get(PartyMemberVM::class.java)

//        vmPartyMember.newMember().observe(viewLifecycleOwner) {
//            val imgResName = "@drawable/" + selectedParty
//            val imageID = resources.getIdentifier(imgResName, "drawable", activity?.getPackageName())
//            binding.imgParty.setImageResource(imageID)
//            binding.tvMemberName.text = it.last + ", " + it.first
//            binding.tvMemberBirthYear.text = "Borned in " + it.bornYear.toString()
//            binding.tvDistrict.text = "District: " + it.constituency
//            binding.tvMemberTwitter.text = "Twitter: " +
//                    if (it.twitter != "") it.twitter else "none"
//        }

        binding.btnRandom.setOnClickListener { view : View ->
//            vmPartyMember.setMember()
        }

        binding.btnToBack.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_partyDetailsFragment_to_selectedPartyMembersFragment)
        }

        binding.btnToMain.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_partyDetailsFragment_to_titleFragment)
        }

        return binding.root
    }
}