package com.wy.studystudio.ui.strategy.model

import android.os.Parcel
import android.os.Parcelable
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import kotlin.collections.ArrayList

/**
 *
 * @author  cantalou
 * @date    2020年11月15日 19:36
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
data class Strategy(var id: Long, var name: String = "", var phases: MutableListWithLiveData<Phase> = MutableListWithLiveData<Phase>()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        MutableListWithLiveData(parcel.createTypedArrayList<Phase>(Phase.CREATOR) as ArrayList<Phase>)
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Strategy

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
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