package com.example.testlibrarysong.presentation.ui.selectsongs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding
import com.example.testlibrarysong.presentation.ui.users.UserComparator

class SelectSongsRecyclerUsersAdapter : ListAdapter<User, SelectSongsRecyclerUsersViewHolder>(UserComparator()) {

    // убрать этот адаптер, изменив UserListAdapter сделав  private val clickListener: UserClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSongsRecyclerUsersViewHolder {
        return SelectSongsRecyclerUsersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_fragment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SelectSongsRecyclerUsersViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class SelectSongsRecyclerUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)
    fun onBind(user: User) {
        binding.apply {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvEMailOrDescription.text = user.email
        }
    }
}