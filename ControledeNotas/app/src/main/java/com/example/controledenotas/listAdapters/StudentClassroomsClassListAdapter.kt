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
import com.example.controledenotas.viewModels.StudentClassroomsClassViewModel

class StudentClassroomsClassListAdapter (val context: Context, val StudentClassroomsClassViewModel: StudentClassroomsClassViewModel) : ListAdapter<StudentClassrooms, StudentClassroomsClassListAdapter.StudentClassroomsClassViewHolder>(
    StudentClassroomsClassComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassroomsClassViewHolder {
        return StudentClassroomsClassViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StudentClassroomsClassViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id.toString(), current.name) }

    class StudentClassroomsClassViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentClassroomsClassName: TextView = itemView.findViewById(R.id.tvClassName)
        private val studentClassroomsClassId: TextView = itemView.findViewById(R.id.tvClassID)

        fun bind(name: String?, id: String?) {
            studentClassroomsClassName.text = name
            studentClassroomsClassId.text = id.toString()
        }

        companion object {
            fun create(parent: ViewGroup): StudentClassroomsClassViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return StudentClassroomsClassViewHolder(view)
            }
        }
}

    class StudentClassroomsClassComparator : DiffUtil.ItemCallback<StudentClassrooms>() {
        override fun areItemsTheSame(oldItem: StudentClassrooms, newItem: StudentClassrooms): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StudentClassrooms, newItem: StudentClassrooms): Boolean {
            return oldItem.id == newItem.id
        }
    }
}