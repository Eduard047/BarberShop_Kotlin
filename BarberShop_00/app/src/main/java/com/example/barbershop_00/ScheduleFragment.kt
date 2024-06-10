package com.example.barbershop_00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop_00.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val schedules = listOf(
            Schedule(1, "Іван Іванов", "Понеділок", "09:00", "18:00"),
            Schedule(2, "Петро Петров", "Вівторок", "10:00", "19:00"),
            Schedule(3, "Сергій Сергієв", "Середа", "09:00", "18:00"),
            Schedule(4, "Олег Олександров", "Четвер", "10:00", "19:00"),
            Schedule(5, "Данило Данилов", "П'ятниця", "09:00", "18:00")
        )

        val adapter = ScheduleAdapter(schedules)
        binding.recyclerViewSchedule.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSchedule.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
