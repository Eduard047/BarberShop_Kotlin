package com.example.barbershop_00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop_00.databinding.FragmentMasterProfileBinding

class MasterProfileFragment : Fragment() {

    private var _binding: FragmentMasterProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMasterProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val masters = listOf(
            Master("Іван Іванов", mapOf("Понеділок" to Pair("09:00", "18:00")), R.drawable.ic_master1),
            Master("Петро Петров", mapOf("Вівторок" to Pair("10:00", "19:00")), R.drawable.ic_master2),
            Master("Сергій Сергієв", mapOf("Середа" to Pair("09:00", "18:00")), R.drawable.ic_master3),
            Master("Олег Олександров", mapOf("Четвер" to Pair("10:00", "19:00")), R.drawable.ic_master4),
            Master("Данило Данилов", mapOf("П'ятниця" to Pair("09:00", "18:00")), R.drawable.ic_master5)
        )

        binding.recyclerViewMasters.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMasters.adapter = MasterAdapter(masters)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
