package com.istech.roomdatabse.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.istech.roomdatabse.R
import com.istech.roomdatabse.databinding.NotesLayoutBinding
import com.istech.roomdatabse.models.Note


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var mList = ArrayList<Note?>()
    private lateinit var context: Context
    var onClick: OnClick? = null

    interface OnClick {
        fun onClickHolder(model: Note, type: Int)
    }

    fun updateData(list: List<Note?>) {
        mList = list as ArrayList<Note?>
        notifyDataSetChanged()
    }

    fun loadMore(data: List<Note>?) {
        mList.addAll(data!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notes_layout, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mList[position]


        holder.binding?.apply {
            if (model != null) {
                tvTitle.text = model.title

                tvDesc.text = model.description

                ivDelete.setOnClickListener {
                    onClick?.onClickHolder(model, 0)
                }

            }

        }

        holder.itemView.setOnClickListener {
            onClick?.onClickHolder(model!!, 1)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: NotesLayoutBinding? = DataBindingUtil.bind(itemView)

        init {
            binding?.executePendingBindings()
        }
    }
}

