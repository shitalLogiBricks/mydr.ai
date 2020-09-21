package com.app.mydrai.ui.mainModule


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.data.api.AnswerModel
import com.app.mydrai.data.api.QuestionModel
import com.app.mydrai.databinding.ActivityMainBinding
import com.app.mydrai.ui.mainModule.adapter.QuestionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator,
    QuestionAdapter.QuestionAdapterSelectionListner {

    var activityMainBinding: ActivityMainBinding? = null
    val mainViewModel: MainViewModel by viewModel()
    var questionAdapter: QuestionAdapter? = null
    var questionList = ArrayList<QuestionModel>()
    var answerList = ArrayList<AnswerModel>()
    var answerList1 = ArrayList<AnswerModel>()
    var answerList2 = ArrayList<AnswerModel>()
    var answerList3 = ArrayList<AnswerModel>()


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()

        var questionModel = QuestionModel()
        questionModel.mainQuestion = "Main Question"
        questionModel.subQuestion = "sub Question"
        var answerModel = AnswerModel()
        answerModel.answer = "Yes"
        answerList.add(answerModel)
        answerModel = AnswerModel()
        answerModel.answer = "no"
        answerList.add(answerModel)
        questionModel.answerModel = answerList
        questionList.add(questionModel)




        questionModel = QuestionModel()
        questionModel.mainQuestion = "Main Question"
        questionModel.subQuestion = "sub Question"
        var answerModel1 = AnswerModel()
        answerModel1.answer = "Yes"
        answerList1.add(answerModel1)
        answerModel1 = AnswerModel()
        answerModel1.answer = "no"
        answerList1.add(answerModel1)
        questionModel.answerModel = answerList1
        questionList.add(questionModel)



        questionModel = QuestionModel()
        questionModel.mainQuestion = "Main Question"
        questionModel.subQuestion = "sub Question"
       var  answerModel2 = AnswerModel()
        answerModel2.answer = "Yes"
        answerList2.add(answerModel2)
        answerModel2.answer = "no"
        answerList2.add(answerModel2)
        questionModel.answerModel = answerList2
        questionList.add(questionModel)


        questionModel = QuestionModel()
        questionModel.mainQuestion = "Main Question"
        questionModel.subQuestion = "sub Question"
        var answerModel3 = AnswerModel()
        answerModel3.answer = "Yes"
        answerList3.add(answerModel3)
        answerModel3.answer = "no"
        answerList3.add(answerModel3)
        questionModel.answerModel = answerList3
        questionList.add(questionModel)








        questionAdapter =
            QuestionAdapter(
                this@MainActivity,
                questionList,
                this
            )
        activityMainBinding!!.recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        activityMainBinding!!.recyclerView.adapter = questionAdapter


    }

    override fun setUp(savedInstanceState: Bundle?) {

    }

    override fun onClickOnQuesAdapter(position: Int, genderModel: QuestionModel) {

    }


}









