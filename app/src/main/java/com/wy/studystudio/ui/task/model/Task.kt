package com.wy.studystudio.ui.task.model

import android.os.Parcel
import android.os.Parcelable
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.common.model.BaseModel
import com.wy.studystudio.ui.strategy.model.Phase

/**
 *
 * @date    2020/11/19
 *
 */
class Task(
    id: Long = 0,
    var name: String = "",
    val strategyId: Long = 0,
    var phaseId: Long = 0,
    var finishTime: Long = 0,
    var contents: MutableListWithLiveData<Content> = MutableListWithLiveData()
) : BaseModel(id), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        MutableListWithLiveData(parcel.createTypedArrayList<Content>(Content.CREATOR) as ArrayList<Content>)
    ) {
    }

    constructor() : this(0)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeLong(strategyId)
        parcel.writeLong(phaseId)
        parcel.writeLong(finishTime)
        parcel.writeTypedList(contents)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }


}