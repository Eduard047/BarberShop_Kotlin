package com.example.barbershop_00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.barbershop_00.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonServices.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_ServiceListFragment)
        }
        binding.buttonBooking.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_BookingFragment)
        }
        binding.buttonMasterProfile.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_MasterProfileFragment)
        }
        binding.buttonSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_ScheduleFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
