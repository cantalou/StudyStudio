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
data class Phase(val id: Int, val interval: Long) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeLong(interval)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Phase> {
        override fun createFromParcel(parcel: Parcel): Phase {
            return Phase(parcel)
        }

        override fun newArray(size: Int): Array<Phase?> {
            return arrayOfNulls(size)
        }
    }

}