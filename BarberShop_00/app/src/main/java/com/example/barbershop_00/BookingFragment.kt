package com.example.barbershop_00

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop_00.databinding.FragmentBookingBinding
import java.util.*

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    private val bookings = mutableListOf<Booking>()
    private lateinit var adapter: BookingAdapter

    private val masters = listOf(
        Master("Іван Іванов", mapOf("Понеділок" to Pair("09:00", "18:00")), R.drawable.ic_master1),
        Master("Петро Петров", mapOf("Вівторок" to Pair("10:00", "19:00")), R.drawable.ic_master2),
        Master("Сергій Сергієв", mapOf("Середа" to Pair("09:00", "18:00")), R.drawable.ic_master3),
        Master("Олег Олександров", mapOf("Четвер" to Pair("10:00", "19:00")), R.drawable.ic_master4),
        Master("Данило Данилов", mapOf("П'ятниця" to Pair("09:00", "18:00")), R.drawable.ic_master5)
    )

    private val services = listOf("Стрижка", "Бриття", "Укладання", "Масаж голови", "Фарбування волосся")
    private val daysOfWeek = listOf("Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BookingAdapter(bookings)
        binding.recyclerViewBookings.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewBookings.adapter = adapter

        val spinnerMastersAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, masters.map { it.name })
        spinnerMastersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMasters.adapter = spinnerMastersAdapter

        val spinnerDaysAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, daysOfWeek)
        spinnerDaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDays.adapter = spinnerDaysAdapter

        val spinnerServicesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, services)
        spinnerServicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerServices.adapter = spinnerServicesAdapter

        binding.editTextTime.setOnClickListener {
            showTimePickerDialog()
        }

        binding.buttonBook.setOnClickListener {
            val masterName = binding.spinnerMasters.selectedItem.toString()
            val service = binding.spinnerServices.selectedItem.toString()
            val day = binding.spinnerDays.selectedItem.toString()
            val time = binding.editTextTime.text.toString()

            val selectedMaster = masters.find { it.name == masterName }

            if (selectedMaster != null && isValidBooking(selectedMaster, day, time)) {
                val booking = Booking(masterName, "$service о $time")
                bookings.add(booking)
                adapter.notifyDataSetChanged()
                binding.lottieAnimationLoading.visibility = View.GONE
                binding.contentLayout.visibility = View.VISIBLE
                binding.lottieAnimationSuccess.visibility = View.VISIBLE
                binding.lottieAnimationSuccess.playAnimation()
                binding.root.postDelayed({
                    binding.lottieAnimationSuccess.visibility = View.GONE
                }, 2000)
                Toast.makeText(requireContext(), "Запис успішно створено", Toast.LENGTH_SHORT).show()
                binding.editTextTime.text.clear()
            } else {
                Toast.makeText(requireContext(), "Майстер не працює в цей час", Toast.LENGTH_SHORT).show()
            }
        }


        binding.lottieAnimationLoading.visibility = View.VISIBLE
        binding.lottieAnimationLoading.playAnimation()

        binding.root.postDelayed({
            binding.lottieAnimationLoading.visibility = View.GONE
            binding.contentLayout.visibility = View.VISIBLE
        }, 2000)
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            binding.editTextTime.setText(String.format("%02d:%02d", selectedHour, selectedMinute))
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun isValidBooking(master: Master, day: String, time: String): Boolean {
        val workHours = master.workDays[day]

        if (workHours != null) {
            val (startTime, endTime) = workHours
            return time >= startTime && time <= endTime
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
