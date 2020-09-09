package com.example.resourcesuser.Fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.resourcesuser.R
import com.example.resourcesuser.View.MainActivity
import kotlinx.android.synthetic.main.dialog_fragment.*
import java.util.*


class FilterFragment : DialogFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.dialog_fragment, container, false)

        val mySpinner: Spinner = root.findViewById(R.id.dialog_spinner)
        val options = arrayOf("","Newest", "Oldest", "Relevance")
        mySpinner.adapter = activity?.applicationContext?.let {
            ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, options)
        }

        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
        }
        mySpinner.gravity = Gravity.END

        val datePick: ImageButton = root.findViewById(R.id.icon_date)
        datePick.setOnClickListener(this)

        val saveButton: Button = root.findViewById(R.id.save_button)
        saveButton.setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.icon_date -> showDatePickerDialog()
            R.id.save_button -> saveFilter()
        }
    }

    private val c: Calendar = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val day = c.get(Calendar.DAY_OF_MONTH)

    private fun showDatePickerDialog() {
        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ _: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
            val month = mMonth + 1
            enter_begin_date.text = ("$mDay-$month-$mYear").toEditable()}, year, month, day)
        dpd.show()
    }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun pickSpinner(): String{
        return dialog_spinner.selectedItem.toString()
    }

    private fun saveFilter(){
        val date: String? = if (enter_begin_date.text.toString() != ""){
            val value: Array<String> = enter_begin_date.text.toString().split("-".toRegex(), 5).toTypedArray()
            if (value[0].toInt() < 10) {
                value[0] = "0" + value[0]
            }
            if (value[1].toInt() < 10) {
                value[1] = "0" + value[1]
            }
            value[2] + value[1] + value[0]
        } else ""

        var news_desk_value = ""
        if ((checkbox1.isChecked)&&(checkbox2.isChecked)&&(checkbox3.isChecked)){
            news_desk_value = resources.getString(R.string.arts) + "20%" + resources.getString(R.string.fashion_style) + "20%" + resources.getString(
                R.string.sports
            )
        } else if ((checkbox1.isChecked)&&(checkbox2.isChecked)){
            news_desk_value = resources.getString(R.string.arts) + "20%" + resources.getString(R.string.fashion_style)
        } else if ((checkbox2.isChecked)&&(checkbox3.isChecked)){
            news_desk_value = resources.getString(R.string.fashion_style) + "20%" + resources.getString(
                R.string.sports
            )
        } else if ((checkbox3.isChecked)&&(checkbox1.isChecked)){
            news_desk_value = resources.getString(R.string.sports) + "20%" + resources.getString(R.string.arts)
        } else if (checkbox1.isChecked){
            news_desk_value = resources.getString(R.string.arts)
        } else if (checkbox2.isChecked){
            news_desk_value = resources.getString(R.string.fashion_style)
        } else if (checkbox3.isChecked){
            news_desk_value = resources.getString(R.string.sports)
        }

        var spinner = ""
        if (pickSpinner().isNotEmpty()) {
            spinner = pickSpinner()
        }

        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("date", date)
        intent.putExtra("sort", spinner.toLowerCase(Locale.ROOT))
        intent.putExtra("news_desk", news_desk_value)

        Log.d("check", "aaaaa")
        startActivity(intent)
    }

}