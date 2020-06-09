package com.application.viewholder

import android.view.View
import android.widget.TextView
import com.application.adapter.IRecyclerViewClickListener
import com.application.covid_19.R
import com.application.dto.CaseUpdateDTO
import com.application.model.IBaseModel

/*
  Created by Muhammed Yusuf ÇİL
  22.04.2020 - 22:18    
  myusufcl7@gmail.com
*/

class CaseUpdateViewHolder(view: View) : BaseViewHolder(view) {

    var totalDeath: TextView = view.findViewById(R.id.tv_item_case_update_card_death_number)
    var totalRecovered: TextView = view.findViewById(R.id.tv_item_case_update_card_recovered_number)
    var totalInfected: TextView = view.findViewById(R.id.tv_item_case_update_card_infected_number)
    var newInfected: TextView = view.findViewById(R.id.tv_item_case_update_new_infected_number)
    var newDeath: TextView = view.findViewById(R.id.tv_item_case_update_new_death_number)

    override fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener) {
        val item = baseModel as CaseUpdateDTO
        item.let {
            totalDeath.text=item.totalDeaths
            totalInfected.text=item.totalCases

            if( item.totalRecovered=="N/A"){
                totalRecovered.text="Mevcut Veri Yok..."
                totalRecovered.textSize =15f
            }else{
                totalRecovered.text=item.totalRecovered
            }

            if( item.newCases == "" ){
                newInfected.text="Veriler Güncelleniyor..."
                newInfected.textSize =15f
            }else{
                newInfected.text=item.newCases
            }
            if(item.newDeaths==""){
                newDeath.text="Veriler Güncelleniyor..."
                newDeath.textSize =15f
            }else{
                newDeath.text=item.newDeaths
            }
        }
    }
}