package com.example.barbershop_00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop_00.databinding.FragmentServiceListBinding

class ServiceListFragment : Fragment() {

    private var _binding: FragmentServiceListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val services = listOf(
            Service(1, "Стрижка", "Класична чоловіча стрижка", 15.0, R.drawable.ic_haircut),
            Service(2, "Бриття", "Гладке бриття небезпечною бритвою", 20.0, R.drawable.ic_shave),
            Service(3, "Укладання", "Модне укладання волосся", 10.0, R.drawable.ic_styling),
            Service(4, "Масаж голови", "Розслаблюючий масаж голови", 25.0, R.drawable.ic_head_massage),
            Service(5, "Фарбування волосся", "Професійне фарбування волосся", 50.0, R.drawable.ic_hair_coloring)
        )

        val adapter = ServiceAdapter(services)
        binding.recyclerViewServices.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewServices.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
