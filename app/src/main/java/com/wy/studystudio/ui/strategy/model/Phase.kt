package com.wy.studystudio.ui.strategy.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:02
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
data class Phase(var id: Long, var interval: Long, var strategyId: Long = 0) : Parcelable {

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Phase

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<Phase> {

        @JvmStatic
        val MINUTE_INTERVAL = 60L

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
        val days = interval /DAY_INTERVAL
        if(days > 0){
            return "${days}天"
        }

        interval %= DAY_INTERVAL
        val hours = interval / HOUR_INTERVAL
        if(hours > 0){
            return "${hours}小时"
        }

        interval %= HOUR_INTERVAL
        val minute = interval / MINUTE_INTERVAL
        return "${minute}分钟"
    }

}