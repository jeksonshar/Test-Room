package com.example.testlibrarysong.presentation.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class UserListAdapter(
    private val clickListener: UserClickListener? = null
) : ListAdapter<User, UserListViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserListViewHolder(private val binding: UserListFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var listener: UserClickListener? = null
    var userHolder: User? = null

    init {
        itemView.setOnClickListener {
            listener.let {
                userHolder?.let { user ->
                    it?.openUsersPlaylists(user)
                }
            }
        }
    }

    fun bind(user: User) {
        userHolder = user
        binding.apply {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvEMailOrDescription.text = user.email
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: UserClickListener?): UserListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = UserListFragmentItemBinding.inflate(inflater, parent, false)
            val viewHolder = UserListViewHolder(binding)
            viewHolder.listener = listener
            return viewHolder
        }
    }
}

class UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}