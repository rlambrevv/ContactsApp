package com.radoslav.contactsapp.ui.contacts

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.radoslav.contactsapp.data.Contact
import com.radoslav.contactsapp.databinding.ItemContactBinding

class ContactsAdapter(private val listener: OnItemClickListener) : ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ContactsViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position!= RecyclerView.NO_POSITION){
                        val contact = getItem(position)
                        listener.onItemClick(contact)
                    }
                }
            }
        }

        fun bind(contact: Contact){
            binding.apply {
                imgContact.isActivated
                textViewName.text = contact.first_name
                textViewName.paint.isUnderlineText = contact.favourite
                labelFavourite.isVisible = contact.favourite
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(contact: Contact)
        fun onCheckBoxClick(contact: Contact, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) =
            oldItem == newItem

    }
}