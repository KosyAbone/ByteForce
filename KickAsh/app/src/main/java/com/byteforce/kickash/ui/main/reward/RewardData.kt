package com.byteforce.kickash.ui.main.reward

object RewardData {
    val rewards = listOf<RewardModel>(
        RewardModel(
            provider = "Amazon",
            pointsRequired = 300,
            offerValue = "30%",
            couponLock = false,
            couponRedeemed = false,
            imageURL = "https://m.media-amazon.com/images/G/15/gc/designs/livepreview/default_eng_noto_email_v2016_ca-main._CB652051262_.png"
        ),
        RewardModel(
            provider = "Old Navy",
            pointsRequired = 900,
            offerValue = "40%",
            couponLock = true,
            couponRedeemed = false,
            imageURL = "https://www.gap.com/Asset_Archive/search/logos/Old_Navy.jpg"
        ),
        RewardModel(
            provider = "Tim Hortons",
            pointsRequired = 100,
            offerValue = "25%",
            couponLock = false,
            couponRedeemed = true,
            imageURL = "https://seeklogo.com/images/T/Tim_Hortons-logo-044A25466D-seeklogo.com.png"
        )
//        RewardModel(
//            provider = "Starbucks",
//            pointsRequired = 500,
//            offerValue = "15%",
//            couponLock = false,
//            couponRedeemed = false,
//            imageURL = "https://stories.starbucks.com/uploads/2019/01/Starbucks_Logo_Hi-res.jpg"
//        )
    )
}