package com.byteforce.kickash.ui.main.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byteforce.kickash.R
import kotlin.random.Random

class SocialMessageAdapter(private var data: List<SocialMessage>, private val socialMessageRecyclerAdapterListener: SocialMessageRecyclerAdapterListener): RecyclerView.Adapter<SocialMessageAdapter.ItemViewHolder>() {

    private val listener = socialMessageRecyclerAdapterListener

    private val OTHER_USER_TYPE = 0
    private val SELF_USER_TYPE = 1

    abstract class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        open fun bindItemDataToView(item: SocialMessage) {}
    }
    class BasicMessageViewHolder(itemView: View) : ItemViewHolder(itemView) {

        private val messageBody: TextView = itemView.findViewById(R.id.socialMessageBody)
        private val messageDateTime: TextView = itemView.findViewById(R.id.socialMessageDatetime)
        override fun bindItemDataToView(item: SocialMessage) {
            messageBody.text = item.messageBody
            messageDateTime.text = item.getTimeString()
        }
    }

    class SelfMessageViewHolder(itemView: View) : ItemViewHolder(itemView) {


        private val messageBody: TextView = itemView.findViewById(R.id.socialSelfMessageBody)
        private val messageDateTime: TextView = itemView.findViewById(R.id.socialSelfMessageDatetime)
        override fun bindItemDataToView(item: SocialMessage) {
            messageBody.text = item.messageBody
            messageDateTime.text = item.getTimeString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemViewHolder: ItemViewHolder

        val type = getUserMessageType()

        when (type) {
            OTHER_USER_TYPE->{
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_social_message, parent, false)
                itemViewHolder = BasicMessageViewHolder(itemView)
            }
            SELF_USER_TYPE->{
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_social_message_self, parent, false)
                itemViewHolder = SelfMessageViewHolder(itemView)
            }
            else->
                throw IllegalArgumentException("")
        }

        return itemViewHolder
    }

    private fun getUserMessageType(): Int {
        //Should be checking if current user is same user as message owner
        return Random.nextInt(2)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bindItemDataToView(currentItem)
    }

    fun updateMessages(messages: List<SocialMessage>) {
        data = messages
        notifyDataSetChanged()
    }

    interface SocialMessageRecyclerAdapterListener{
        //fun onItemClick(data: SocialMessage?)
        //fun onItemDelete(position: Int)
        //fun deleteItem(data: SocialMessage)
        fun onItemUpdate()
    }
}