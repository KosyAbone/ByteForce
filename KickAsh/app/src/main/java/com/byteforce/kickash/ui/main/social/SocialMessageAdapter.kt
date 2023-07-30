package com.byteforce.kickash.ui.main.social

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.byteforce.kickash.R

class SocialMessageAdapter(private var data: MutableList<SocialMessage>): RecyclerView.Adapter<SocialMessageAdapter.ItemViewHolder>() {


    private val OTHER_USER_TYPE = 0
    private val SELF_USER_TYPE = 1
    private val MESSAGE_ITEM_PER_PAGE = 50



    abstract class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        open fun bindItemDataToView(item: SocialMessage) {}
    }
    class BasicMessageViewHolder(itemView: View) : ItemViewHolder(itemView) {

        private val messageBody: TextView = itemView.findViewById(R.id.socialMessageBody)
        private val messageDateTime: TextView = itemView.findViewById(R.id.socialMessageDatetime)
        private val messageUserName: TextView = itemView.findViewById(R.id.socialMessageUserDisplayName)
        override fun bindItemDataToView(item: SocialMessage) {
            messageBody.text = item.messageBody
            messageDateTime.text = item.getTimeString()
            messageUserName.text = item.senderId
        }
    }

    class SelfMessageViewHolder(itemView: View) : ItemViewHolder(itemView) {


        private val messageBody: TextView = itemView.findViewById(R.id.socialSelfMessageBody)
        private val messageDateTime: TextView = itemView.findViewById(R.id.socialSelfMessageDatetime)
        private val messageUserName: TextView = itemView.findViewById(R.id.socialMessageUserSelfDisplayName)
        override fun bindItemDataToView(item: SocialMessage) {
            messageBody.text = item.messageBody
            messageDateTime.text = item.getTimeString()
            messageUserName.text = item.senderId
        }
    }

    class SocialMessageDiffCallback(
        private val oldList: List<SocialMessage>,
        private val newList: List<SocialMessage>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldMessage = oldList[oldItemPosition]
            val newMessage = newList[newItemPosition]

            return oldMessage.messageBody == newMessage.messageBody &&
                    oldMessage.timestamp == newMessage.timestamp &&
                    oldMessage.senderId == newMessage.senderId
        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemViewHolder: ItemViewHolder = when (viewType) {
            OTHER_USER_TYPE->{
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_social_message, parent, false)
                BasicMessageViewHolder(itemView)
            }

            SELF_USER_TYPE->{
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_social_message_self, parent, false)
                SelfMessageViewHolder(itemView)
            }

            else->
                throw IllegalArgumentException("")
        }

        return itemViewHolder
    }
    override fun getItemViewType(position: Int): Int {
        val currentItem = data[position]
        return when (getUserMessageType(currentItem.senderId)) {
            OTHER_USER_TYPE -> OTHER_USER_TYPE
            SELF_USER_TYPE -> SELF_USER_TYPE
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
    private fun getUserMessageType(senderId: String): Int {
        //Should be checking if current user is same user as message owner
        return if (senderId == "tester") 1 else 0
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getCurrentPageNumber(): Int {
        return ((itemCount / MESSAGE_ITEM_PER_PAGE)+1)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bindItemDataToView(currentItem)
    }

    fun appendMessages(messages: List<SocialMessage>, index: Int? = null) {
        if ((index != null) && !checkIndexValidity(messages, index)) {
            Log.d("ERROR", "Invalid index given to social message list adapter")
            return
        }else{
            val newData = ArrayList(data)
            if (index == null) {
                newData.addAll(messages)
            }else {
                newData.addAll(index, messages)
            }
            val diffResult = DiffUtil.calculateDiff(SocialMessageDiffCallback(data, newData))

            data.clear()
            data.addAll(newData)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    fun updateMessages(newData: List<SocialMessage>) {
        val oldData = ArrayList(data)
        val diffResult = DiffUtil.calculateDiff(SocialMessageDiffCallback(oldData, newData))

        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun checkIndexValidity(list: List<Any>, index: Int): Boolean {
        return (index >= 0 && index < list.size)
    }

}