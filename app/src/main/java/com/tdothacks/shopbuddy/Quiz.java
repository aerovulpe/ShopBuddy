package com.tdothacks.shopbuddy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by aerisvulpe on 22/02/15.
 */

public class Quiz {
    private ArrayList<QuestionNode> mNodes;
    private HashMap<Node, Integer> mVector;


    {
        mNodes = new ArrayList<>();
        mVector = new HashMap<>();

        mNodes.add(new QuestionNode(Node.Accessories, new String[]{"Would you like to receive news about the latest accessories?",
                "Do you believe you are likely to purchase accessories to help you in your daily life?"}));
        mNodes.add(new QuestionNode(Node.Baby_Products, new String[]{"Have you, or do you know anyone who has a baby?",
                "Are you in need of baby products?"}));
        mNodes.add(new QuestionNode(Node.Books, new String[]{"Would you consider yourself to be an avid book reader?",
                "Do you enjoy reading books?"}));
        mNodes.add(new QuestionNode(Node.Camera_and_Photos, new String[]{"Are you interested in photography?",
                "Do you like taking pictures in your spare time?"}));
        mNodes.add(new QuestionNode(Node.Cell_Phones, new String[]{"Are you currently looking for a new cell phone?",
                "Would you say you use a cell phone regularly?"}));
        mNodes.add(new QuestionNode(Node.Consumer_Electronics, new String[]{"Would you say you follow the newest trends in tech?",
                "Do you like having high-tech gadgets and toys?"}));
        mNodes.add(new QuestionNode(Node.Home_and_Garden, new String[]{"Do you have a garden that you maintain regularly?",
                "Would you be interested in starting your own garden?"}));
        mNodes.add(new QuestionNode(Node.Music, new String[]{"Would you like to receive news about new music releases?",
                "Would you say music is a very important aspect of your life?"}));
        mNodes.add(new QuestionNode(Node.Musical_Instruments, new String[]{"Are you interested in learning to play an instrument?",
                "Do you, or do you know anyone who plays music instruments?"}));
        mNodes.add(new QuestionNode(Node.Office_Products, new String[]{"Are you interested in purchasing new office equipment?",
                "Do you have an office or home office?"}));
        mNodes.add(new QuestionNode(Node.Outdoors, new String[]{"Would you consider yourself to be an avid outdoorsman?",
                "Do you enjoy the outdoors?"}));
        mNodes.add(new QuestionNode(Node.Personal_Computers, new String[]{"Are you in the market for a new personal computer?",
                "Are you interested in a new computer?"}));
        mNodes.add(new QuestionNode(Node.Software, new String[]{"Are you interested in purchasing computer software?",
                "Do you spend a lot of time in front of a computer?"}));
        mNodes.add(new QuestionNode(Node.Sports, new String[]{"Are you interested in purchasing sporting equipment?",
                "Do you play sports in your spare time?"}));
        mNodes.add(new QuestionNode(Node.Tools_and_Home_Improvement, new String[]{"Are you currently renovating or interested in renovating your home?",
                "Do you see a home renovation in your future?"}));
        mNodes.add(new QuestionNode(Node.Toys_and_Games, new String[]{"Do you have small children?",
                "Do you know of anyone with small children?"}));
        mNodes.add(new QuestionNode(Node.Video_Games, new String[]{"Do you have any gaming consoles at home?",
                "Do you spend a lot of time playing video games?"}));
        mNodes.add(new QuestionNode(Node.Health, new String[]{"Are you interested in health products?",
                "How conscientious are you about your health?"}));
        mNodes.add(new QuestionNode(Node.Price, new String[]{"How much are you willing to spend?"}));
        mNodes.add(new QuestionNode(Node.Science, new String[]{"Are you interested in scientific research?",
                "Do you believe science is very important aspect of your life?"}));
        mNodes.add(new QuestionNode(Node.Fitness, new String[]{"Are you interested in fitness equipment?",
                "Do you workout often?"}));

        for (Node node : Node.values()) {
            mVector.put(node, 0);
        }
    }

    public Node getCurrentNode() {
        return mCurrentNode;
    }

    public List<QuestionNode> getCurrentQuestions() {
        return mCurrentQuestions;
    }

    public HashMap<Node, Integer> getVector() {
        return mVector;
    }

    public ArrayList<QuestionNode> getNodes() {
        return mNodes;
    }

    private List<QuestionNode> mCurrentQuestions;
    private Node mCurrentNode;

    public void startQuiz() {
        Collections.shuffle(mNodes);
        mCurrentQuestions = mNodes.subList(0, 20);
    }

    public Node finishQuiz() {
        Node highest = Node.values()[0];

        for (Node node : Node.values()) {
            if (mVector.get(node) > mVector.get(highest)) highest = node;
        }

        return highest;
    }

    public QuestionNode askQuestion() {
        QuestionNode questionNode = mCurrentQuestions.remove(new Random()
                .nextInt(mCurrentQuestions.size()));
        mCurrentNode = questionNode.getNode();
        return questionNode;
    }

    public void incrementVectorValue(Node node) {
        mVector.put(node, mVector.get(node) + 1);
    }

    public void decrementVectorValue(Node node) {
        mVector.put(node, mVector.get(node) - 1);
    }

    public static enum Node {
        Accessories, Baby_Products, Books, Camera_and_Photos, Cell_Phones, Consumer_Electronics,
        Home_and_Garden, Music, Musical_Instruments, Office_Products, Outdoors, Personal_Computers,
        Software, Sports, Tools_and_Home_Improvement, Toys_and_Games, Video_Games, Health, Price,
        Science, Fitness
    }

    public static class QuestionNode {
        private final Node mNode;
        private final String[] mQuestions;

        public QuestionNode(Node node, String[] questions) {
            mNode = node;
            mQuestions = questions;
        }

        public String[] getQuestions() {
            return mQuestions;
        }

        public Node getNode() {
            return mNode;
        }
    }
}
