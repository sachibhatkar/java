/*
 * ScoreProcess_83783
 * @author Sachi Bhatkar
 * Program asks user to enter file. If user enters, scores.txt program calculates what was the average homework score for
 * each student, lowest homework, and highest homework for each student. It prints this data alongside the ID. Furthermore,
 * the program calculates what was the totalFinalExam Average and the highest and lowest score received for the final.
 * Date Aug 7, 2020
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ScoreProcess_83783 {
    // declare instance variable, fileName, finalScoreAverage, finalScoreMax, and finalScoreMin
    private String fileName;
    private double finalScoreAverage, finalScoreMax, finalScoreMin;

    // Pass file name to constructor
    public ScoreProcess_83783(String fn) {
        // initialize variables with values
        fileName = fn;
        finalScoreAverage = 0;
        finalScoreMax = 0;
        finalScoreMin = 999999;
    }

    // processFile method goes through file and calculates which elements are homework scores, final scores, midscores,
    // then does calculations based on reading
    public void processFile() throws IOException {
        // variable lineCount for how many lines are in the file
        int lineCount = 0;
        // variable to store totalFinalScore
        double totalAverageFinalScore = 0;

        // create file object
        File file = new File(fileName);

        // check to see if file exists
        if (file.exists()) {
            // create Scanner object
            Scanner inputFile = new Scanner(file);
            // create scores array to score homework input
            int[] scores = new int[8];
            // string format spaces used for later to format
            String format = "%12s";

            // title
            System.out.format(format+format+format+format+"%n", "ID", "Average Hw", "Lowest HW", "Highest HW");

            // readfile for integers
            while (inputFile.hasNextInt()) {

                // first element is student Id, store it
                int studentID = inputFile.nextInt();
                // read from index 2-8
                int countIndex = 0;
                // while loop for storing homework scores in scores array
                while (inputFile.hasNextInt() && countIndex < 8) {
                    // score into array
                    scores[countIndex] = inputFile.nextInt();
                    countIndex++;
                }
                // read midScore
                int midScore = inputFile.nextInt();
                // read final score
                int finalExamScore = inputFile.nextInt();
                // read lab score
                int labScore = inputFile.nextInt();
                // read quiz score
                int quizScore = inputFile.nextInt();

                // if statement to calculate which Final Score was highest, by comparing with previous variable finalScoreMax
                if(finalScoreMax < finalExamScore ){
                    finalScoreMax = finalExamScore;
                }
                // if statement to calculate which final Score was lowest by comparing with previous variable finalScoreMin
                if(finalScoreMin > finalExamScore){
                    finalScoreMin = finalExamScore;
                }

                // calculation for average hw score, highest hw score, and lowest hw score by storing values after
                // calling methods getAverageScore and passing scores array
                double studentHomeworkAverageScore = getAverageScore(scores);
                int studentHomeworkScoreMax = getHighestScore(scores);
                int studentHomeworkScoreMin = getLowestScore(scores);
                // add all average final scores
                totalAverageFinalScore += finalExamScore;
                // calculate how many lines are in the file
                lineCount++;
                // display each Student id, hw average score, lowest hw and highest hw
                System.out.format(format+"%12.2f"+format+format+"%n", studentID, studentHomeworkAverageScore, studentHomeworkScoreMax, studentHomeworkScoreMin);

            }
            // calculate final score average by dividing totalAverageFinalScore by line count
            finalScoreAverage = totalAverageFinalScore/lineCount;

        }
        else {
            // if user input's file isn't found print error message
            System.out.println("Entered file does not exists");
        }


    }
    // method getAverageScore to calculate average of array
    // pass array as a parameter
    public double getAverageScore(int[] scores){
        double totalScore = 0;
        double average = 0;
        // go through each element, add the elements together
        for(int i = 0; i<scores.length; i++){
            totalScore += scores[i];
        }
        // calculate and return average
        average = totalScore/scores.length;
        return average;
    }
    // method to find highest element in an array, pass array as a parameter
    public int getHighestScore(int[] scores){
        // go through each element and compare with first element.
        // see if satisfies condition, if so then store as highestScore
        int highestScore = scores[0];
        for(int i = 0; i<scores.length; i++){
            if(scores[i] > highestScore){
                highestScore = scores[i];
            }
        }
        // return highestScore value
        return highestScore;
    }

    // method to find lowest element in an array, pass array as a parameter
    public int getLowestScore(int[] scores){
        // go through each element and compare with first element.
        // see if satisfies condition, if so then store as lowestScore
        int lowestScore = scores[0];
        for(int i = 0; i<scores.length; i++){
            if(scores[i] < lowestScore){
                lowestScore = scores[i];
            }
        }
        // return lowestScore value
        return lowestScore;
    }

    // method to print final statistics(instance variable values)
    public void printFinalScoreStat() {
        System.out.println("Average Final Score: "+finalScoreAverage);
        System.out.println("Highest Final Score: "+finalScoreMax);
        System.out.println("Lowest Final Score: "+finalScoreMin);
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter a student score file name: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        System.out.println();
        ScoreProcess_83783 sp = new ScoreProcess_83783(filename);
        sp.processFile();
        System.out.println();
        sp.printFinalScoreStat();
    }


}
