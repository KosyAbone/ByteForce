package com.byteforce.kickash.ui.main.reward

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byteforce.kickash.R
import com.squareup.picasso.Picasso

class RewardAdapter(private var rewardList: List<RewardModel>) : RecyclerView.Adapter<RewardAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val offerTextField : TextView = view.findViewById(R.id.offerText)
        val couponTextField : TextView = view.findViewById(R.id.couponStatusText)
        val redeemButton : Button = view.findViewById(R.id.redeemButton)
        val rewardImage : ImageView = view.findViewById(R.id.cardBackgroundImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rewards, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rewardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reward = rewardList[position]

        Picasso.get()
            .load(reward.imageURL)
            .into(holder.rewardImage)

        holder.offerTextField.text = reward.offerValue + " off at " + reward.provider
        if(reward.pointsRequired <= RewardFragment.rewardsPoints) {
            if(reward.couponLock and !(reward.couponRedeemed)){
                holder.couponTextField.text = "Coupon Locked"
                holder.redeemButton.isEnabled = false
            }
            else{
                holder.couponTextField.text = "Coupon Unlocked"

                if(!reward.couponRedeemed){
                    holder.redeemButton.isEnabled = true
                    holder.redeemButton.text = "Redeem coupon for " + reward.pointsRequired
                }
                else{
                    holder.redeemButton.isEnabled = false
                    holder.redeemButton.text = "Coupon Redeemed"
                }

            }
        }

        holder.redeemButton.setOnClickListener{
            RewardFragment.rewardsPoints = RewardFragment.rewardsPoints - reward.pointsRequired

            val alertDialog = AlertDialog.Builder(holder.itemView.context)
                .setTitle("Coupon Redeemed")
                .setMessage("Congratulations! Your coupon has been redeemed.")
                .setPositiveButton("OK", null)
                .create()

            alertDialog.show()

            reward.couponRedeemed = true
        }
    }
}