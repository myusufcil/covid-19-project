package com.application.viewholder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
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

import kotlinx.android.synthetic.main.item_news_card.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/*
Created by Mustafa Tekkılıç 
*/

class CoronaNewsViewHolder (view: View):BaseViewHolder(view){

    var name: TextView = view.findViewById(R.id.tv_news_name)

    var date:TextView=view.findViewById(R.id.tv_news_date)
    var source:TextView=view.findViewById(R.id.tv_news_source)
    var image:ImageView= view.findViewById(R.id.iv_news_image)
    var rootView:ConstraintLayout=view.findViewById(R.id.newsCardViewRoot)

    lateinit var dateFormat:String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener) {
        val item = baseModel as NewsDTO
        item.let {
            name.text=item.name

            source.text=item.source
            Picasso.get().load(item.image).into(image)

            //Tarih -- Saat
            val input =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val dateOutput = SimpleDateFormat("dd/MM/yyyy -- HH:mm:ss")
//            val dateOutput = SimpleDateFormat("dd/MM/yyyy")
//            val clockOutput = SimpleDateFormat("HH:mm:ss")
            var d: Date? = null
            try {
                d = input.parse(item.date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
//            val clockFormat = clockOutput.format(d)
            dateFormat = dateOutput.format(d)
            date.text=dateFormat

            //






            Picasso.get().load(item.image).into(image)
            //TODO TARİH PARSE EDİLİP BASILACAK
        }
        rootView.setOnClickListener {
            var bundle= Bundle()
            val intent = Intent(rootView.context, NewsDetailActivity::class.java)
            intent.putExtra("image", item.image)
            intent.putExtra("date", dateFormat)
            intent.putExtra("source", item.source)
            intent.putExtra("description", item.description)
            startActivity(rootView.context,intent,bundle)
        }
    }
}