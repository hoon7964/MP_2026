package com.example.mp_ex_7_4

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit	var	m_OkCount	:	TextView
    lateinit	var	m_NoCount	:   TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val	button_ok	:	Button	=	findViewById(R.id.button_ok)
        val	button_no	:	Button	=	findViewById(R.id.button_no)

        button_ok.setOnClickListener	{
            val	num	=	(Integer.parseInt(m_OkCount.text.toString())+1)
            m_OkCount.text	=		num.toString()
        }

        button_no.setOnClickListener	{
            val	num	=	(Integer.parseInt(m_NoCount.text.toString())+1)
            m_NoCount.text	=		num.toString()
        }
    }
    override	fun	onCreateOptionsMenu(menu:	Menu?):	Boolean	{
        menuInflater.inflate(R.menu.menu,menu)

        val	item_ok	=	menu?.findItem(R.id.menu_up)
        item_ok?.setActionView(R.layout.ok_layout	)
        val	oklayout		=	item_ok?.actionView
        if	(oklayout	!=	null)	{
            m_OkCount	=	oklayout.findViewById(R.id.thumbs_up_num)
        }

        val	item_no	=	menu?.findItem(R.id.menu_down)
        item_no?.setActionView(R.layout.no_layout	)
        val	nolayout		=	item_no?.actionView
        if	(nolayout	!=	null)	{
            m_NoCount	=	nolayout.findViewById(R.id.thumbs_up_num)
        }
        return	super.onCreateOptionsMenu(menu)
    }
    override	fun	onOptionsItemSelected(item:	MenuItem):	Boolean	{
        when(item.itemId)	{
            R.id.menu_init	->	{
                m_OkCount.text="0"
                m_NoCount.text	=	"0"
                return	true
            }

            R.id.menu_close	->	{
                finish()
                return	true
            }
        }
        return	super.onOptionsItemSelected(item)
    }
}