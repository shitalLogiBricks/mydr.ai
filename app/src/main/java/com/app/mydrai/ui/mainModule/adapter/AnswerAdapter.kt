package com.app.mydrai.ui.mainModule.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.mydrai.R
import com.app.mydrai.data.api.AnswerModel
import com.app.mydrai.ui.mainModule.MainNavigator


class AnswerAdapter(
    var context: Context,
    var arrayListTaxrate: List<String>?,
    mainNavigator: MainNavigator?
) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAnswer: TextView = view.findViewById(R.id.txtAnswer)
    }

    interface AnswerAdapterSelectionListner {
        fun clickOnAnswer(position: Int, genderModel: AnswerModel)
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
        return arrayListTaxrate?.size!!

    }


    override fun onBindViewHolder(holder: AnswerAdapter.ViewHolder, position: Int) {
        holder.txtAnswer.text = arrayListTaxrate?.get(position)
     /*   val animation1 = AnimationUtils.loadAnimation(context,
            R.anim.slide_in_right
        )
        animation1.setRepeatCount(ValueAnimator.INFINITE);
        holder.txtAnswer.startAnimation(animation1)*/
    }
}