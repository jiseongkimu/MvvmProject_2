package com.example.mvvmproject_2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvmproject_2.R
import com.example.mvvmproject_2.Room.Contact
import com.example.mvvmproject_2.databinding.ItemContactBinding

class ContactAdapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var listItems: List<Contact>? = null

    fun sublitList(list: List<Contact>){
        listItems = list
    }

    // 화면을 최초 로딩하여 만들어진 View가 없는 경우, xml파일을 inflate하여 ViewHolder를 생성한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        listItems?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return listItems?.size ?: 0
    }

    class ViewHolder(
        private val binding: ItemContactBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Contact){
            binding.itemTvInitial = Contact
        }
    }
}