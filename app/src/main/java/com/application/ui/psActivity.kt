package com.application.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.adapter.IRecyclerViewClickListener
import com.application.adapter.RecyclerViewAdapter
import com.application.covid_19.R
import com.application.dto.SymptomsDTO
import com.application.model.IBaseModel

class psActivity : AppCompatActivity() {

    lateinit var recyclerViewSymptomsAdapter: RecyclerViewAdapter
    lateinit var recyclerViewSymptoms: RecyclerView
    private var symptomsListBaseModel = mutableListOf<IBaseModel>()
    lateinit private var symptomsListObject:SymptomsDTO


    var recyclerViewItemClickListener = object : IRecyclerViewClickListener {
        override fun onClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLongClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    lateinit var symptomsPics:ArrayList<Int>
     var symptomsName= arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ps)

        readyToDataSource()
        getRecyclerViewAdapter()

        for(position in 0..symptomsPics.size-1){
            symptomsListObject= SymptomsDTO(
                picsSymptoms = symptomsPics[position],
                nameSymptoms = symptomsName[position]
            )
            symptomsListBaseModel.add(symptomsListObject)
            recyclerViewSymptomsAdapter.notifyDataSetChanged()
        }


    }

    private fun readyToDataSource() {
        symptomsPics = arrayListOf(
            R.drawable.ates64,
            R.drawable.oksuruk64,
            R.drawable.nefes_darligi64,
            R.drawable.ishal64,
            R.drawable.bulanti64,
            R.drawable.bas_agrisi64,
            R.drawable.burun_akintisi64,
            R.drawable.halsizlik64,
            R.drawable.vucut_agrisi64
        )

        symptomsName=resources.getStringArray(R.array.diseases)
    }

    private fun getRecyclerViewAdapter() {
        recyclerViewSymptoms =
            findViewById(R.id.recyclerViewSymptoms)
        recyclerViewSymptomsAdapter =
            RecyclerViewAdapter(symptomsListBaseModel, recyclerViewItemClickListener)
        recyclerViewSymptoms.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerViewSymptoms.adapter = recyclerViewSymptomsAdapter
    }

}
