package com.app.mydrai.ui.mainModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mydrai.R
import com.app.mydrai.data.api.QuestionModel
import java.util.*


class QuestionAdapter(
    var context: Context,
    var arrayListTaxrate: ArrayList<QuestionModel>,
    var taxratelistner: QuestionAdapterSelectionListner
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtQuestion: TextView = view.findViewById(R.id.txtQuestion)
        var imgAppLogo: ImageView = view.findViewById(R.id.imgAppLogo)
        var ansListRecycler: RecyclerView = view.findViewById(R.id.recyclerAndList)
    }

    interface QuestionAdapterSelectionListner {
        fun onClickOnQuesAdapter(position: Int, genderModel: QuestionModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.questions_item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrayListTaxrate.size

    }


    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.txtQuestion.setText(arrayListTaxrate?.get(position)?.mainQuestion)


    /*    val resId: Int = R.anim.layout_animation_from_right
        val animation: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, resId)
        holder.ansListRecycler.setLayoutAnimation(animation)*/

        val answerAdapter = AnswerAdapter(context, arrayListTaxrate?.get(position)?.answerModel!!)
        holder.ansListRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        holder.ansListRecycler.adapter = answerAdapter


        val animation1 = AnimationUtils.loadAnimation(context,
            R.anim.slide_down_anim
        )
        holder.imgAppLogo.startAnimation(animation1)


    }
}