package com.application.ui
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.covid_19.R
import com.application.ui.fragments.CaseUpdateFragment
import com.application.ui.fragments.PreventionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        SystemUIVisibility()

        //Bottom Bar Function
        chipMenuMain.setOnItemSelectedListener {

            when (it) {
                R.id.home_menu -> {
                    loadFragment(CaseUpdateFragment())
                    overridePendingTransition(R.anim.main_slide_top, R.anim.main_slide_bottom)
                }
                R.id.wear_mask_menu->{
                    loadFragment(PreventionFragment())
                    overridePendingTransition(R.anim.ps_slide_top,R.anim.ps_slide_bottom)
                }
            }
        }

        val view: View = chipMenuMain.findViewById(R.id.home_menu)
        view.performClick()
        //Bottom Bar Function
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

//    fun SystemUIVisibility(){
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//    }
}