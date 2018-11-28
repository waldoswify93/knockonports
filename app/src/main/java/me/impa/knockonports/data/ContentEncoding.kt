/*
 * Copyright (c) 2018 Alexander Yaburov
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package me.impa.knockonports.data

import android.util.Base64
import java.lang.Exception

enum class ContentEncoding {
    RAW {
        override fun decode(data: String?): ByteArray = (data ?: "").toByteArray()
    },
    BASE64 {
        override fun decode(data: String?): ByteArray =
                try {
                    Base64.decode(data, Base64.DEFAULT)
                } catch (_: Exception) {
                    byteArrayOf()
                }
    },
    HEX {
        override fun decode(data: String?): ByteArray =
                try {
                    if (data == null) {
                        byteArrayOf()
                    } else {
                        ByteArray(data.length / 2) { data.substring(it * 2, it * 2 + 2).toInt(16).toByte() }
                    }
                } catch (_: Exception) {
                    byteArrayOf()
                }
    };

    abstract fun decode(data: String?): ByteArray

    companion object {
        val values = ContentEncoding.values()

        fun fromOrdinal(ordinal: Int): ContentEncoding = if (ordinal in 0 until values.size) values[ordinal] else RAW
    }
}