package com.maxxxwk.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxxxwk.recyclerview.databinding.PersonListItemBinding

class PersonTreeAdapter(private val root: Person) :
    RecyclerView.Adapter<PersonTreeAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PersonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person, kinshipDegree: Int) {
            val paddingInDp = 32 * kinshipDegree
            val paddingInPx =
                (paddingInDp * binding.root.resources.displayMetrics.density + 0.5f).toInt()
            binding.tvName.setPadding(paddingInPx, 0, 0, 0)
            binding.tvName.text = "Name: ${person.name}"
            binding.tvAge.setPadding(paddingInPx, 0, 0, 0)
            binding.tvAge.text = "Age: ${person.age}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PersonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return root.getSizeOfSubtree()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val relative = root.getRelativeByListPosition(position)
        relative?.let {
            holder.bind(it.person, it.kinshipDegree)
        }
    }
}