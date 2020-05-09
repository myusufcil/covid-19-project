package com.application.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.application.covid_19.R
import com.application.model.IBaseModel
import com.application.viewholder.*

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
            IBaseModel.TYPE_NEWS ->
                CoronaNewsViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_news_card,
                        parent,
                        false
                    )
                )
            IBaseModel.TYPE_SYMPTOMS ->
                SymptomsViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_symptoms_card,
                        parent,
                        false
                    )
                )
            IBaseModel.TYPE_PREVENTION ->
                PreventionViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_prevention_card,
                        parent,
                        false
                    )
                )

            else ->
                throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = items.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (items[position].type) {
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
            IBaseModel.TYPE_SYMPTOMS ->
                (holder as SymptomsViewHolder).bindView(
                    items[position],
                    position,
                    recyclerViewClickListener
                )
            IBaseModel.TYPE_PREVENTION ->
                (holder as PreventionViewHolder).bindView(
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