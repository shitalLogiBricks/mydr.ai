package com.app.mydrai.ui.mainModule.adapter


import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.mydrai.R
import com.app.mydrai.data.api.AnswerModel
import java.util.*


class AnswerAdapter(
    var context: Context,
    var arrayListTaxrate: ArrayList<AnswerModel>
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
        return arrayListTaxrate.size

    }


    override fun onBindViewHolder(holder: AnswerAdapter.ViewHolder, position: Int) {


        holder.txtAnswer.text = arrayListTaxrate.get(position).answer


        val animation1 = AnimationUtils.loadAnimation(context,
            R.anim.slide_in_right
        )
        animation1.setRepeatCount(ValueAnimator.INFINITE);
        holder.txtAnswer.startAnimation(animation1)
    }
}