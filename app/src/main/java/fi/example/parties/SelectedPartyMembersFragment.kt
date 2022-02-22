package fi.example.parties

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.recyclerviews.MemberOnClickListener
import fi.example.parties.recyclerviews.SelectedPartyMembersListAdapter
import fi.example.parties.viewmodels.MemberVMFactory
import fi.example.parties.viewmodels.MembersListVM

class SelectedPartyMembersFragment() : Fragment() {
	val bundle = Bundle()
	private lateinit var binding: fi.example.parties.databinding.FragmentSelectedPartyMembersBinding
	private lateinit var vmMembersList: MembersListVM
	private lateinit var vmMembersListFactory: MemberVMFactory
	private lateinit var selectedParty: String
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle? ): View? {
		binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_selected_party_members,container,false)
		val application = Application()
		selectedParty = arguments?.getString("selectedParty") ?: ""
		
		vmMembersListFactory = MemberVMFactory(application, selectedParty)
		vmMembersList = ViewModelProvider(this, vmMembersListFactory).get(MembersListVM::class.java)
		
		vmMembersList.membersList.observe(viewLifecycleOwner) {
			createMembersList(it)
		}
		
		return binding.root
	}
	
	fun createMembersList(membersList: List<String>) {
		val recyclerView: RecyclerView = binding.recyclerViewSelectedPartyMembers
		recyclerView.layoutManager = LinearLayoutManager(requireContext())
		recyclerView.adapter = SelectedPartyMembersListAdapter(membersList, object:
			MemberOnClickListener {
			override fun onClick(member: String) {
				Log.d("click", "member = $member")
				bundle.putString("selectedMember", member)
				view?.findNavController()
					?.navigate(R.id.action_selectedPartyMembersFragment_to_partyDetailsFragment, bundle)
			}
		})
	}
}