package com.application.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.application.adapter.IRecyclerViewClickListener
import com.application.model.IBaseModel

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 14:43    
  myusufcl7@gmail.com
*/

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener)
}