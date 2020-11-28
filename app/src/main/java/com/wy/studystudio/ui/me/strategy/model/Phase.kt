package com.wy.studystudio.ui.me.strategy.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.wy.studystudio.ui.common.model.BaseModel

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:02
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class Phase(id: Long, var interval: Long, var strategyId: Long = 0) : Parcelable, BaseModel(id) {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(interval)
        parcel.writeLong(strategyId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Phase> {

        @JvmStatic
        val MINUTE_INTERVAL = 60L * 1000

        @JvmStatic
        val HOUR_INTERVAL = 60 * MINUTE_INTERVAL

        @JvmStatic
        val DAY_INTERVAL = 24 * HOUR_INTERVAL

        override fun createFromParcel(parcel: Parcel): Phase {
            return Phase(parcel)
        }

        override fun newArray(size: Int): Array<Phase?> {
            return arrayOfNulls(size)
        }
    }

    fun desc(): String {
        val days = interval / DAY_INTERVAL
        if (days > 0) {
            return "${days}天"
        }

        interval %= DAY_INTERVAL
        val hours = interval / HOUR_INTERVAL
        if (hours > 0) {
            return "${hours}小时"
        }

        interval %= HOUR_INTERVAL
        val minute = interval / MINUTE_INTERVAL
        return "${minute}分钟"
    }

}