package fi.example.parties

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.example.parties.databinding.FragmentDbHandleBinding
import fi.example.parties.recyclerviews.PartiesAdapter
import fi.example.parties.room.data.ApiHelper
import fi.example.parties.room.data.ApiService
import fi.example.parties.room.data.Status
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.viewmodels.ApiVM
import fi.example.parties.viewmodels.ApiVMFactory
import fi.example.parties.viewmodels.DbHandleVM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DbHandleFragment : Fragment() {
    private lateinit var binding: FragmentDbHandleBinding
    private lateinit var vmDbHandle: DbHandleVM
//    private val parliamentMembers = ParliamentMembersData.members
    
    
    
    
    
    
    private lateinit var apiVM: ApiVM
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_db_handle, container, false)
    
        
        
        
        
        
        apiVM = ViewModelProvider(
            this,
            ApiVMFactory(ApiHelper(ApiService.RetrofitBuilder.apiService)))
            .get(ApiVM::class.java)
    
        apiVM.getMembers().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { users ->
                            fillDb(users)
                            Log.d("LOG", "Data received: ${users[0]}")
                        }
                        
                    }
                    Status.ERROR -> {
//                        recyclerView.visibility = View.VISIBLE
//                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
//                        progressBar.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
                    }
                }
            }
        }
    
    
    
    
    
    
    
    
    
    
        vmDbHandle = ViewModelProvider(this).get(DbHandleVM::class.java)

        binding.btnAddToDb.setOnClickListener {
//            insertDataToDB()
        }

        binding.btnCleanDb.setOnClickListener {
            vmDbHandle.deleteAll()
            Toast.makeText(requireContext(), "DB has been cleaned.", Toast.LENGTH_SHORT).show()
            view?.findNavController()
                ?.navigate(R.id.action_dbHandleFragment_to_partiesFragment)
        }

        return binding.root
    }
    
    
    
    
    private fun fillDb(list: List<PartyMember>) {
        list.forEach {
            val member = PartyMember(
                it.personNumber,
                it.seatNumber,
                it.last,
                it.first,
                it.party,
                it.minister,
                it.picture,
                it.twitter,
                it.bornYear,
                it.constituency
            )
            vmDbHandle.addMember(member)
        }
        Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
        view?.findNavController()
            ?.navigate(R.id.action_dbHandleFragment_to_partiesFragment)
    }
    
    
    
    

    /*private fun insertDataToDB() {
        parliamentMembers.forEach {
            val member = PartyMember(
                it.personNumber,
                it.seatNumber,
                it.last,
                it.first,
                it.party,
                it.minister,
                it.picture,
                it.twitter,
                it.bornYear,
                it.constituency
            )
            vmDbHandle.addMember(member)
        }
        Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
        view?.findNavController()
            ?.navigate(R.id.action_dbHandleFragment_to_partiesFragment)
    }*/
}