package com.example.barbershop_00

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop_00.databinding.ItemMasterBinding

class MasterAdapter(private val masters: List<Master>) : RecyclerView.Adapter<MasterAdapter.MasterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        val binding = ItemMasterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MasterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        holder.bind(masters[position])
    }

    override fun getItemCount() = masters.size

    class MasterViewHolder(private val binding: ItemMasterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(master: Master) {
            binding.masterName.text = master.name
            binding.masterPhoto.setImageResource(master.photoRes)
            val workDaysText = master.workDays.entries.joinToString(separator = "\n") { (day, hours) ->
                "$day: ${hours.first} - ${hours.second}"
            }
            binding.masterWorkDays.text = workDaysText
        }
    }
}
