package com.wy.studystudio.ui.task.model

import android.os.Parcel
import android.os.Parcelable
import com.wy.studystudio.ui.common.model.BaseModel

/**
 *
 * @date    2020/11/19
 *
 */
class Content(id: Long = 0, var type: Int = 0, val taskId: Long = 0, var content: String = "") : BaseModel(id), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString()!!
    ) {
    }

    constructor() : this(0)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeInt(type)
        parcel.writeLong(taskId)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Content> {

        const val TYPE_TEXT = 0

        const val TYPE_IMAGE = 1

        const val TYPE_VIDEO = 2


        override fun createFromParcel(parcel: Parcel): Content {
            return Content(parcel)
        }

        override fun newArray(size: Int): Array<Content?> {
            return arrayOfNulls(size)
        }
    }
}