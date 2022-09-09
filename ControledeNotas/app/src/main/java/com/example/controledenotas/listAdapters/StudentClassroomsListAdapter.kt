package com.example.controledenotas.listAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.controledenotas.R
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.viewModels.StudentClassroomsViewModel

class StudentClassroomsListAdapter(val context: Context, val studentClassroomsViewModel: StudentClassroomsViewModel) : ListAdapter<StudentClassrooms, StudentClassroomsListAdapter.StudentClassroomsViewHolder>(
    StudentClassroomsComparator()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassroomsViewHolder {
        return StudentClassroomsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StudentClassroomsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
        holder.itemView.findViewById<TextView>(R.id.btnDelete).setOnClickListener { showDeleteDialog(holder, current) }
    }

    fun showDeleteDialog(holder: StudentClassroomsViewHolder, classroom: StudentClassrooms) {
        val dialogBuilder = AlertDialog.Builder(holder.itemView.context)
        dialogBuilder.setTitle("Delete")
        dialogBuilder.setMessage("Confirm delete?")
        dialogBuilder.setPositiveButton("Delete", { dialog, whichButton ->
            studentClassroomsViewModel.delete(classroom)
        })
        dialogBuilder.setNegativeButton("Cancel", { dialog, whichButton ->
            dialog.cancel()
        })
        val b = dialogBuilder.create()
        b.show()
    }

    class StudentClassroomsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val classroomName: TextView = itemView.findViewById(R.id.studentName)

        fun bind(name: String?) {
            classroomName.text = name
        }

        companion object {
            fun create(parent: ViewGroup): StudentClassroomsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return StudentClassroomsViewHolder(view)
            }
        }
    }

    class StudentClassroomsComparator : DiffUtil.ItemCallback<StudentClassrooms>() {
        override fun areItemsTheSame(oldItem: StudentClassrooms, newItem: StudentClassrooms): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StudentClassrooms, newItem: StudentClassrooms): Boolean {
            return oldItem.name == newItem.name
        }
    }
}