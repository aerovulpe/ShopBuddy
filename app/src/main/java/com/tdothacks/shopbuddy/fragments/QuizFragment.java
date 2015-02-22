package com.tdothacks.shopbuddy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tdothacks.shopbuddy.Quiz;
import com.tdothacks.shopbuddy.R;

import java.util.Random;


public class QuizFragment extends Fragment implements View.OnClickListener {

    private Quiz mQuiz;
    private TextView mQuestionNumber;
    private TextView mQuestionIndex;
    private TextView mQuestionText;
    private ImageButton mYesButton;
    private ImageButton mNoButton;
    private int mQuestionCnt;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuiz = new Quiz();
        mQuiz.startQuiz();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

        mQuestionNumber = (TextView) rootView.findViewById(R.id.questionNumber);
        mQuestionIndex = (TextView) rootView.findViewById(R.id.question_index);
        mQuestionText = (TextView) rootView.findViewById(R.id.question_text);
        mYesButton = (ImageButton) rootView.findViewById(R.id.yesButton);
        mNoButton = (ImageButton) rootView.findViewById(R.id.noButton);

        mYesButton.setOnClickListener(this);
        mNoButton.setOnClickListener(this);
        setUpQuestion();

        return rootView;
    }

    private void setUpQuestion() {
        mQuestionCnt++;
        if (mQuestionCnt > 20) {
            mQuiz.finishQuiz();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ProductFragment())
                    .commit();
        } else {
            String[] questionsList = mQuiz.askQuestion().getQuestions();
            mQuestionNumber.setText(mQuestionCnt + "");
            mQuestionIndex.setText("Question " + mQuestionCnt + " of 20");
            mQuestionText.setText(questionsList[new Random().nextInt(questionsList.length)]);
        }
    }

    @Override
    public void onClick(View v) {
        setUpQuestion();
        if (v.getId() == R.id.yesButton) {
            if (mQuiz.getCurrentNode() != null) mQuiz.incrementVectorValue(mQuiz.getCurrentNode());
        } else if (v.getId() == R.id.noButton) {
            if (mQuiz.getCurrentNode() != null) mQuiz.decrementVectorValue(mQuiz.getCurrentNode());
        }
    }
}
