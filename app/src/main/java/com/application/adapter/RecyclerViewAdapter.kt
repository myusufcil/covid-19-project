package com.application.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.application.MainActivity
import com.application.covid_19.R
import com.application.model.IBaseModel
import com.application.viewholder.BaseViewHolder
import com.application.viewholder.CaseUpdateViewHolder
import com.application.viewholder.CoronaNewsViewHolder
import com.application.viewholder.PreventionViewHolder
import kotlinx.android.synthetic.main.item_case_update_card.*

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 18:37    
  myusufcl7@gmail.com
*/

class RecyclerViewAdapter(
    var items: MutableList<IBaseModel>,
    var recyclerViewClickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            IBaseModel.TYPE_CASE_UPDATE ->
                CaseUpdateViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_case_update_card,
                        parent,
                        false
                    )
                )
            IBaseModel.TYPE_NEWS ->CoronaNewsViewHolder(layoutInflater.inflate(R.layout.item_news_card,parent,false)
            )



            else ->
                throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (items[position].type) {
      /*      IBaseModel.TYPE_COVİD_VIRUSES_BY_COUNTRIES ->
                (holder as PreventionViewHolder).bindView(
                    items[position],
                    position,
                    recyclerViewClickListener
                )*/
            IBaseModel.TYPE_CASE_UPDATE ->
                (holder as CaseUpdateViewHolder).bindView(
                    items[position],
                    position,
                    recyclerViewClickListener
                )

            IBaseModel.TYPE_NEWS ->
                (holder as CoronaNewsViewHolder).bindView(
                    items[position],
                    position,
                    recyclerViewClickListener
                )
        }
    }

    override fun getItemViewType(position: Int): Int { //  !!!Burayı sor!!!
        return items[position].type
    }
}