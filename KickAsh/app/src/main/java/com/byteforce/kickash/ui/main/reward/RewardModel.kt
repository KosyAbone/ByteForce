package com.byteforce.kickash.ui.main.reward

data class RewardModel(
    val provider : String,
    val pointsRequired : Int,
    val offerValue : String,
    val couponLock : Boolean,
    var couponRedeemed : Boolean,
    val imageURL : String
)
