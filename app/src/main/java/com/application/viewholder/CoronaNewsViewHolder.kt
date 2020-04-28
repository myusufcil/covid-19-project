package com.application.viewholder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.application.adapter.IRecyclerViewClickListener
import com.application.covid_19.R
import com.application.dto.NewsDTO
import com.application.model.IBaseModel
import com.application.ui.NewsDetailActivity
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/*
Created by Mustafa Tekkılıç 
*/

class CoronaNewsViewHolder (view: View):BaseViewHolder(view){

    var name: TextView = view.findViewById(R.id.tv_news_name)
    var image:ImageView= view.findViewById(R.id.iv_news_image)
    var newsDate:TextView= view.findViewById(R.id.item_news_card_date)
    var rootView:ConstraintLayout=view.findViewById(R.id.newsCardViewRoot)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener) {
        val item = baseModel as NewsDTO
        item.let {
            name.text=item.name
            Picasso.get().load(item.image).into(image)
            //TO DO TARİH PARSE EDİLİP BASILACAK
            newsDate.text=item.date

        }
        rootView.setOnClickListener {
            var bundle= Bundle()
            val intent = Intent(rootView.context, NewsDetailActivity::class.java)
            intent.putExtra("url", item.url)
            startActivity(rootView.context,intent,bundle)
        }
    }
}