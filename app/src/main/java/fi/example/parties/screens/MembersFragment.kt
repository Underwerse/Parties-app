package fi.example.parties.screens

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.R
import fi.example.parties.databinding.FragmentMembersBinding
import fi.example.parties.recyclerviews.MemberOnClickListener
import fi.example.parties.recyclerviews.MembersAdapter
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.viewmodels.MembersVMFactory
import fi.example.parties.viewmodels.MembersVM

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Fragment for selected by party's name members list's data screen
 */
class MembersFragment : Fragment() {
	val bundle = Bundle()
	private lateinit var binding: FragmentMembersBinding
	private lateinit var vmMembers: MembersVM
	private lateinit var vmMembersFactory: MembersVMFactory
	private lateinit var selectedParty: String
	
	override fun onCreateView(inflater: LayoutInflater,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?
	): View {
		binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_members,container,false)
		
		val application = Application() // for using app-context
		
		// get data from previous View
		selectedParty = arguments?.getString("selectedParty") ?: ""
		
		vmMembersFactory = MembersVMFactory(application, selectedParty)
		vmMembers = ViewModelProvider(this, vmMembersFactory)
			.get(MembersVM::class.java)
		
		vmMembers.membersList.observe(viewLifecycleOwner) {
			createMembersList(it)
		}
		
		return binding.root
	}
	
	/**
	 * Fills RecyclerView-list with data
	 */
	private fun createMembersList(membersList: List<PartyMember>) {
		val recyclerView: RecyclerView = binding.recyclerViewSelectedPartyMembers
		recyclerView.layoutManager = LinearLayoutManager(requireContext())
		val membersNames = membersList.map { it.first + ' ' + it.last }
		
		recyclerView.adapter = MembersAdapter(membersNames, object:
			MemberOnClickListener {
			override fun onClick(memberName: String) {
				
				// get data for transit to the next View
				val memberPersNumber = membersList.filter {
					it.first == memberName.split(' ')[0]
							&& it.last == memberName.split(' ')[1]
				}[0].personNumber
				
				val memberParty = membersList.filter {
					it.first == memberName.split(' ')[0]
							&& it.last == memberName.split(' ')[1]
				}[0].party
				
				// put data for transit to the next View
				bundle.putString("selectedMember", memberName)
				bundle.putString("selectedParty", memberParty)
				bundle.putInt("memberPersNumber", memberPersNumber)
				view?.findNavController()
					?.navigate(R.id.action_membersFragment_to_memberInfoFragment, bundle)
			}
		})
	}
}