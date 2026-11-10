package com.example.mp_ex_8_1

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit	var	btnPizzaObj	:	Button
    lateinit	var	btnToppingObj	:	Button
    lateinit	var	ivBgObj	:	ImageView
    lateinit	var	ivPizzaObj	:	ImageView
    lateinit	var	btnOrderObj	:	Button
    lateinit	var	tvPizzaObj	:	TextView
    lateinit	var	tvToppingObj	:	TextView
    lateinit	var	ivDeliveryObj	:	ImageView
    lateinit	var	tvDeliveryObj	:	TextView

    var	drawablePizza	=	intArrayOf(
        R.drawable.pizza1,	R.drawable.pizza2,	R.drawable.pizza3,
        R.drawable.pizza4)
    var	drawableTopping	=	intArrayOf(
        R.drawable.topping1,	R.drawable.topping2,	R.drawable.topping3,
        R.drawable.topping4,	R.drawable.topping5)
    var	imageViewTopping	=	intArrayOf(
        R.id.topping1,	R.id.topping2,	R.id.topping3,
        R.id.topping4,	R.id.topping5)
    var	ivToppingObj	=	arrayOfNulls<ImageView>(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnPizzaObj = findViewById(R.id.button_pizza)
        btnToppingObj = findViewById(R.id.button_toppting)
        btnToppingObj.isEnabled = false
        ivBgObj = findViewById(R.id.imageView_bg)
        ivPizzaObj = findViewById(R.id.pizza)

        for (i in 0..4)
            ivToppingObj[i] = findViewById(imageViewTopping[i])
        btnOrderObj = findViewById(R.id.button_order)
        btnOrderObj.isEnabled = false
        tvPizzaObj = findViewById(R.id.textView_pizza)
        tvToppingObj = findViewById(R.id.textView_toppting)
        btnOrderObj = findViewById(R.id.button_order)
        ivDeliveryObj = findViewById(R.id.delivery)
        tvDeliveryObj = findViewById(R.id.textView_delivery)
        var selectNum = 0
        var oldNum = 0

        btnPizzaObj.setOnClickListener {
            val listItems = arrayOf("마르게리타	피자", "불고기	피자", "양송이	피자", "하와이안	피자")
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setIcon(R.drawable.icon_pizza)
            alertDialog.setTitle("주문할	피자를	선택하세요")
            val itemListener = DialogInterface.OnClickListener { dialog, whitch ->
                Toast.makeText(this, listItems[whitch], Toast.LENGTH_LONG).show()
                selectNum = whitch
            }
            val btnListener = DialogInterface.OnClickListener { dialog, whitch ->
                when (whitch) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                        ivBgObj.isVisible = false
                        ivDeliveryObj.isVisible = false
                        tvDeliveryObj.isVisible = false
                        btnToppingObj.isEnabled = true
                        btnOrderObj.isEnabled = true
                        ivPizzaObj.isVisible = true
                        tvPizzaObj.isVisible = true
                        ivPizzaObj.setImageResource(drawablePizza[selectNum])
                        tvPizzaObj.text = listItems[selectNum]
                        oldNum = selectNum
                        tvToppingObj.text = ""
                    }

                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.cancel()
                    }
                }
                dialog.cancel()
            }

            alertDialog.setSingleChoiceItems(listItems, oldNum, itemListener)
            alertDialog . setPositiveButton ("예", btnListener)
            alertDialog.setNegativeButton("아니오", btnListener)

            alertDialog.show()
        }

        btnToppingObj.setOnClickListener	{
            val	listItems	=	arrayOf("양송이",	"불고기",	"바질","베이컨","해물")
            val	checkedArray	=	booleanArrayOf(false,	false,	false,false,false	)
            val	alertDialog	=	AlertDialog.Builder(this)
            alertDialog.setIcon(R.drawable.icon_pizza)
            alertDialog.setTitle("추가할	토핑을	선택하세요")
            val	itemListener	=	DialogInterface.OnMultiChoiceClickListener	{dialog,	whitch	,	b	->
                Toast.makeText(this,	listItems[whitch],	Toast.LENGTH_LONG).show()
                checkedArray[whitch]	=	b
            }
            val	btnListener	=	DialogInterface.OnClickListener	{dialog,	whitch	->
                when	(whitch)	{
                    DialogInterface.BUTTON_POSITIVE	->	{
                        dialog.dismiss()
                        ivBgObj.isVisible	=false
                        tvToppingObj.isVisible	=	true
                        var	str	="토핑추가	:	";
                        for	(i	in	0..4)	{
                            ivToppingObj[i]?.isVisible	=	false
                            if	(checkedArray[i])	{
                                ivToppingObj[i]?.isVisible	=	true
                                ivToppingObj[i]?.setImageResource(drawableTopping[i])
                                str	+=	listItems[i]	+	"	"
                            }
                        }
                        tvToppingObj.text	=	str
                    }
                    DialogInterface.BUTTON_NEGATIVE	->	{
                        dialog.cancel()
                    }
                }
                dialog.cancel()
            }
            alertDialog.setMultiChoiceItems(listItems,	checkedArray,	itemListener)
            alertDialog.setPositiveButton("예",btnListener)
            alertDialog.setNegativeButton("아니오",btnListener)
            alertDialog.show()
        }

        btnOrderObj.setOnClickListener	{
            val	alertDialog=	AlertDialog.Builder(this)
            alertDialog.setMessage("주문하겠습니까?")
            alertDialog.setIcon(R.drawable.icon_pizza)
            val	listener	=	DialogInterface.OnClickListener	{	dialog,	witch	->
                when(witch){
                    DialogInterface.BUTTON_POSITIVE	->	{
                        ivDeliveryObj.isVisible	=	true
                        tvDeliveryObj.isVisible	=	true
                        dialog.dismiss()
                    }
                    DialogInterface.BUTTON_NEGATIVE	->	{
                        dialog.cancel()
                    }
                }
            }
            alertDialog.setPositiveButton("예",	listener)
            alertDialog.setNegativeButton("아니오",	listener)
            alertDialog.show()
        }
    }
}