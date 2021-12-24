package com.example.testlibrarysong.presentation.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class UserListAdapter(
    private val clickListener: UserClickListener
) : ListAdapter<User, UserListViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_fragment_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.openUsersPlaylists(getItem(position))
        }
    }
}

class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)
    fun onBind(user: User) {
        binding.apply {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvEMailOrDescription.text = user.email
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