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

package me.impa.knockonports.viewadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.stephenvinouze.advancedrecyclerview.core.adapters.RecyclerAdapter
import me.impa.knockonports.database.entity.Sequence

class SequenceAdapter(context: Context) : RecyclerAdapter<Sequence>(context) {

    var onAction: ((Int, Sequence) -> Unit)? = null
    var onItemClicked: ((Sequence) -> Unit)? = null

    override fun onBindItemView(view: View, position: Int) {
        when (view) {
            is SequenceView -> view.bind(items[position],
                    onAction = { actionId, sequence -> onAction?.invoke(actionId, sequence) },
                    onClick = { onItemClicked?.invoke(it) })
        }
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View  = SequenceView(context)

}