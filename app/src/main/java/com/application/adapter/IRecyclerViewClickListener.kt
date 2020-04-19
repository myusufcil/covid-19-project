package com.application.adapter

import com.application.model.IBaseModel

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 18:40    
  myusufcl7@gmail.com
*/

interface IRecyclerViewClickListener {
    fun onClickListener(position: Int,model: IBaseModel)
    fun onLongClickListener(position: Int,model: IBaseModel)
}