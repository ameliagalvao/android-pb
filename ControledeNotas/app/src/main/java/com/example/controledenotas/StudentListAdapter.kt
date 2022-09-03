package com.example.controledenotas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter(val context: Context, val studentViewModel: StudentViewModel) : ListAdapter<Student, StudentListAdapter.StudentViewHolder>(StudentsComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.level, current.totalScore)
        holder.itemView.findViewById<TextView>(R.id.btnDelete).setOnClickListener { showDeleteDialog(holder, current) }
    }

    fun showDeleteDialog(holder: StudentViewHolder, student: Student) {
        val dialogBuilder = AlertDialog.Builder(holder.itemView.context)
        dialogBuilder.setTitle("Delete")
        dialogBuilder.setMessage("Confirm delete?")
        dialogBuilder.setPositiveButton("Delete", { dialog, whichButton ->
            studentViewModel.delete(student)
        })
        dialogBuilder.setNegativeButton("Cancel", { dialog, whichButton ->
            dialog.cancel()
        })
        val b = dialogBuilder.create()
        b.show()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentName: TextView = itemView.findViewById(R.id.studentName)
        private val studentLevel: TextView = itemView.findViewById(R.id.studentLevel)
        private val studentGrade: TextView = itemView.findViewById(R.id.studentGrade)

        fun bind(name: String?, level: String?, grade: Double?) {
            studentName.text = name
            studentLevel.text = level
            studentGrade.text = grade.toString()
        }

        companion object {
            fun create(parent: ViewGroup): StudentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return StudentViewHolder(view)
            }
        }
    }

    class StudentsComparator : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.studentId == newItem.studentId
        }
    }
}