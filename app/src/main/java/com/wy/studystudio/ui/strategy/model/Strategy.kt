package com.wy.studystudio.ui.strategy.model

import android.os.Parcel
import android.os.Parcelable
import kotlin.collections.ArrayList

/**
 *
 * @author  cantalou
 * @date    2020年11月15日 19:36
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
data class Strategy(val id: Long, var name: String, var phases: ArrayList<Phase> = ArrayList()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        ArrayList(parcel.createTypedArrayList(Phase.CREATOR))
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeTypedList(phases)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Strategy> {
        override fun createFromParcel(parcel: Parcel): Strategy {
            return Strategy(parcel)
        }

        override fun newArray(size: Int): Array<Strategy?> {
            return arrayOfNulls(size)
        }
    }

}