package com.example.controledenotas.listAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.controledenotas.R
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.viewModels.StudentClassViewModel

class StudentClassListAdapter (val context: Context, val StudentClassViewModel: StudentClassViewModel) : ListAdapter<StudentClassrooms, StudentClassListAdapter.StudentClassViewHolder>(
    StudentClassComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassViewHolder {
        return StudentClassViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StudentClassViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id.toString(), current.name) }

    class StudentClassViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentClassName: TextView = itemView.findViewById(R.id.tvClassName)
        private val studentClassId: TextView = itemView.findViewById(R.id.tvClassID)

        fun bind(name: String?, id: String?) {
            studentClassName.text = name
            studentClassId.text = id.toString()
        }

        companion object {
            fun create(parent: ViewGroup): StudentClassViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return StudentClassViewHolder(view)
            }
        }
}

    class StudentClassComparator : DiffUtil.ItemCallback<StudentClassrooms>() {
        override fun areItemsTheSame(oldItem: StudentClassrooms, newItem: StudentClassrooms): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StudentClassrooms, newItem: StudentClassrooms): Boolean {
            return oldItem.id == newItem.id
        }
    }
}