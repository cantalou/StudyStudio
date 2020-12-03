package com.wy.studystudio.extension

import android.content.Context
import android.content.SharedPreferences
import com.wy.studystudio.SSApplication
import java.lang.IllegalStateException
import kotlin.collections.HashSet
import kotlin.reflect.KProperty

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月28日 17:55
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
var instance: SP = SP(SSApplication.INSTANCE.getSharedPreferences("config", Context.MODE_PRIVATE))
val Any.sp: SP
    get() {
        return instance
    }

class SP(val sp: SharedPreferences) {
    var reciteModel: Boolean by SPDelegate
    var coverPercent: String by SPWithValueDelegate("80")
    var taskReciteMode: MutableSet<String> by SPDelegate
}

class SPWithValueDelegate<T>(private val defaultValue: T) {
    operator fun getValue(sp: SP, property: KProperty<*>): T {
        sp.sp.apply {
            return when (defaultValue) {
                is String -> {
                    getString(property.name, defaultValue)
                }
                is Boolean -> {
                    getBoolean(property.name, defaultValue)
                }
                is Int -> {
                    getInt(property.name, defaultValue)
                }
                is Float -> {
                    getFloat(property.name, defaultValue)
                }
                is Long -> {
                    getLong(property.name, defaultValue)
                }
                is Set<*> -> {
                    getStringSet(property.name, mutableSetOf())
                }
                else -> IllegalStateException("Unknown property type  ${property}")
            } as T
        }
    }

    operator fun setValue(sp: SP, property: KProperty<*>, t: T) {
        sp.sp.edit().apply {
            when (t) {
                is String -> {
                    putString(property.name, t)
                }
                is Boolean -> {
                    putBoolean(property.name, t)
                }
                is Int -> {
                    putInt(property.name, t)
                }
                is Float -> {
                    putFloat(property.name, t)
                }
                is Long -> {
                    putLong(property.name, t)
                }
                is Set<*> -> {
                    putStringSet(property.name, t as Set<String>)
                }
                else -> IllegalStateException("Unknown property type  $property")
            }
        }.apply()
    }
}

object SPDelegate {

    private val wrapperSetMap = mutableMapOf<String, HashSet<String>>()

    class WrapperSet(val name: String, val sp: SharedPreferences) : HashSet<String>() {
        override fun add(element: String): Boolean {
            val values = sp.getStringSet(name, mutableSetOf())
            values!!.add(element)
            sp.edit().putStringSet(name, values).apply()
            return super.add(element)
        }

        override fun remove(element: String): Boolean {
            val values = sp.getStringSet(name, mutableSetOf())
            values!!.remove(element)
            sp.edit().putStringSet(name, values).apply()
            return super.remove(element)
        }
    }

    operator fun <T> getValue(sp: SP, property: KProperty<*>): T {
        sp.sp.apply {
            val name = property.name
            return when (property.returnType.classifier) {
                String::class -> {
                    getString(name, "") as String
                }
                Boolean::class -> {
                    getBoolean(name, false)
                }
                Int::class -> {
                    getInt(name, 0)
                }
                Float::class -> {
                    getFloat(name, 0.toFloat())
                }
                Long::class -> {
                    getLong(name, 0)
                }
                Set::class -> {
                    var wrapperSet = wrapperSetMap.get(name)
                    if (wrapperSet == null) {
                        wrapperSet = WrapperSet(name, sp.sp)
                        wrapperSetMap.put(name, wrapperSet)
                        wrapperSet.addAll(getStringSet(name, mutableSetOf())!!)
                    }
                    wrapperSet
                }
                else -> IllegalStateException("Unknown property type  $property")
            } as T
        }
    }

    operator fun <T> setValue(sp: SP, property: KProperty<*>, t: T) {
        sp.sp.edit().apply {
            when (t) {
                is String -> {
                    putString(property.name, t)
                }
                is Boolean -> {
                    putBoolean(property.name, t)
                }
                is Int -> {
                    putInt(property.name, t)
                }
                is Float -> {
                    putFloat(property.name, t)
                }
                is Long -> {
                    putLong(property.name, t)
                }
                is Set<*> -> {
                    putStringSet(property.name, t as Set<String>)
                }
                else -> IllegalStateException("Unknown property type  ${property}")
            }
        }.apply()
    }
}