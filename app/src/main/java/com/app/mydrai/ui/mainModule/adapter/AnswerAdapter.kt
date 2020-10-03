package com.app.mydrai.ui.mainModule.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.mydrai.R
import com.app.mydrai.core.presentation.invisible
import com.app.mydrai.core.presentation.visible
import com.app.mydrai.data.api.AnswerModel
import com.app.mydrai.ui.mainModule.MainNavigator


class AnswerAdapter(
    var context: Context,
    var arrayListTaxrate: List<String>?,
    mainNavigator: MainNavigator?,
    var taxratelistner: GenderSelectListner
) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAnswer: TextView = view.findViewById(R.id.txtAnswer)
        var etTxt: EditText = view.findViewById(R.id.etTxt)
        var btnSubmit: TextView = view.findViewById(R.id.btnSubmit)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.answer_item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if(arrayListTaxrate?.size!=0)
        {
            return arrayListTaxrate?.size!!

        }else{
            return  1
        }

    }
    interface GenderSelectListner {
        fun onClickOnGender(response: String?)
    }


    override fun onBindViewHolder(holder: AnswerAdapter.ViewHolder, position: Int) {
          if(arrayListTaxrate!!.size.equals(0))
          {
              holder.txtAnswer.visibility=View.GONE
              holder.etTxt.visibility=View.VISIBLE
              holder.btnSubmit.visibility=View.VISIBLE
          }else{
              holder.etTxt.visibility=View.GONE
              holder.btnSubmit.visibility=View.GONE
              holder.txtAnswer.text = arrayListTaxrate?.get(position)
          }

          holder.btnSubmit!!.setOnClickListener {
              var etText= holder.etTxt.text.toString()
              taxratelistner.onClickOnGender(etText)
              notifyDataSetChanged()
          }

        holder.txtAnswer!!.setOnClickListener {
            var response  = arrayListTaxrate?.get(position)
            taxratelistner.onClickOnGender(response)
            notifyDataSetChanged()
        }
    }
}