package com.wy.studystudio.ui.me.strategy.model

import android.os.Parcel
import android.os.Parcelable
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.common.model.BaseModel
import kotlin.collections.ArrayList

/**
 *
 * @author  cantalou
 * @date    2020年11月15日 19:36
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class Strategy(id: Long = 0, var name: String = "", var phases: MutableListWithLiveData<Phase> = MutableListWithLiveData<Phase>()) : Parcelable,BaseModel(id){

    constructor():this(0)

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

    companion object CREATOR : Parcelable.Creator<Strategy> {
        override fun createFromParcel(parcel: Parcel): Strategy {
            return Strategy(parcel)
        }

        override fun newArray(size: Int): Array<Strategy?> {
            return arrayOfNulls(size)
        }
    }


}