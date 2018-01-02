import com.sun.org.apache.xpath.internal.operations.Mod;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.List;

public class Main {

    public Main() {
        //firstComeFirstServe();
        //shortestJobFirst();
        //shortestJobFirstNonPreemtive();
        //shortestJobFirstPreemtive();
        //priority();
        //roundRobin();


    }

    private void shortestJobFirstPreemtive() {
        List<NonPreemtive> models = new ArrayList<>();
        List<NonPreemtive> tempModelList = new ArrayList<>();


        System.out.println("Enter the number of process");
        Scanner scanner = new Scanner(System.in);
        int numberOfprocess = scanner.nextInt();
        int totalTime = 0;

        for (int i = 1; i <= numberOfprocess; i++) {

            System.out.println("Name of the process: " + i);
            int nameOfProcess = scanner.nextInt();
            System.out.println("Arrival time: "+ i);
            int arrivalTime = scanner.nextInt();
            System.out.println("Burst time of the process: " + i);
            int brustTime = scanner.nextInt();


            NonPreemtive nonPreemtive = new NonPreemtive();
            nonPreemtive.setProcessName(nameOfProcess);
            nonPreemtive.setArrivalTime(arrivalTime);
            nonPreemtive.setBrustTime(brustTime);
            nonPreemtive.setUnitTime(brustTime);
            nonPreemtive.setDone(false);
            totalTime += brustTime;
            models.add(nonPreemtive);
            tempModelList.add(nonPreemtive.copy(nonPreemtive));
        }


        for(int i=0; i<totalTime;){
            int minUnit = totalTime, index = 0;

            for(int j=0; j<numberOfprocess; j++){
                if(models.get(j).getUnitTime() > 0 && models.get(j).getArrivalTime() <=i && models.get(j).getUnitTime() < minUnit){
                    minUnit = models.get(j).getUnitTime();
                    index = j;
                }

            }
            i++;
            models.get(index).setUnitTime(models.get(index).getUnitTime() - 1);
            if (models.get(index).getUnitTime() == 0)
                models.get(index).setFinishTime(i);
        }

        for(int i=0; i<numberOfprocess; i++){
            int waitingTime = models.get(i).getFinishTime() - models.get(i).getBrustTime() - models.get(i).getArrivalTime();
            models.get(i).setWaitingTime(waitingTime);
        }

        double averageTime = 0;
        double turnAroundTime = 0;

        for(int i=0; i<numberOfprocess; i++){
            averageTime+= models.get(i).getWaitingTime();
            turnAroundTime += models.get(i).getWaitingTime() + models.get(i).getBrustTime();
        }

        System.out.println("Average waiting time: "+ averageTime/models.size());
        System.out.println("Average turn around time: "+ turnAroundTime/models.size());


    }

    private void roundRobin() {
        List<RoundRobin> models = new ArrayList<>();
        List<RoundRobin> tempModelList = new ArrayList<>();

        System.out.println("Enter the number of process");
        Scanner scanner = new Scanner(System.in);
        int numberOfprocess = scanner.nextInt();
        int quantumTime = 4;

        for (int i = 1; i <= numberOfprocess; i++) {
            System.out.println("Name of the process: " + i);
            String nameOfProcess = scanner.nextLine();
            nameOfProcess = scanner.nextLine();
            System.out.println("Burst time of the process: " + i);
            int brustTime = scanner.nextInt();

            RoundRobin roundRobin = new RoundRobin();
            roundRobin.setProcessName(nameOfProcess);
            roundRobin.setBurstTime(brustTime);

            models.add(roundRobin);
            tempModelList.add(roundRobin.copy(roundRobin));
        }

        int timing = 0;

        while (true){
            boolean done = true;

            for(int i=0; i<tempModelList.size(); i++){
                int remainingTime = tempModelList.get(i).getBurstTime();
                int anotherTime = models.get(i).getBurstTime();

                if(remainingTime > 0){
                    done = false;
                    if(remainingTime > quantumTime){
                        timing = timing + quantumTime;
                        int remTime = remainingTime - quantumTime;
                        tempModelList.get(i).setBurstTime(remTime);
                    }

                    else{
                        timing = timing + remainingTime;
                        anotherTime = timing - anotherTime;
                        models.get(i).setWaitngTime(anotherTime);
                        tempModelList.get(i).setBurstTime(0);
                    }
                }

            }
            if(done)
                break;
        }
        double averageWatingtime = 0;
        double turnAroundTime = 0;

        for(int i=0; i<models.size(); i++){
            averageWatingtime += models.get(i).getWaitngTime();
            turnAroundTime += models.get(i).getWaitngTime() + models.get(i).getBurstTime();
        }
        System.out.println(averageWatingtime/models.size()+" "+turnAroundTime/models.size());

    }

    private void priority() {
        List<PriorityModel> models = new ArrayList<>();
        List<PriorityModel> tempModelList = new ArrayList<>();


        System.out.println("Enter the number of process");
        Scanner scanner = new Scanner(System.in);
        int numberOfprocess = scanner.nextInt();

        for (int i = 1; i <= numberOfprocess; i++) {

            System.out.println("Name of the process: " + i);
            String nameOfProcess = scanner.nextLine();
            nameOfProcess = scanner.nextLine();
            System.out.println("Burst time of the process: " + i);
            int brustTime = scanner.nextInt();
            System.out.println("priority of the process");
            int priority = scanner.nextInt();

            PriorityModel priorityModel = new PriorityModel();
            priorityModel.setProcessName(nameOfProcess);
            priorityModel.setBurstTime(brustTime);
            priorityModel.setPriority(priority);

            models.add(priorityModel);
            tempModelList.add(priorityModel.copy(priorityModel));
        }

        Collections.sort(models, (t1, t2) -> t1.getPriority() - t2.getPriority());
        Collections.sort(tempModelList, (t1, t2) -> t1.getPriority() - t2.getPriority());

        double timing = 0;
        double averageWatingTime = 0;
        double averageTurnAroundTime = 0;

        for(int i=0; i<tempModelList.size(); i++){
            timing = timing + tempModelList.get(i).getBurstTime();
            double startTime = timing - tempModelList.get(i).getBurstTime();
            models.get(i).setWaitingTime(startTime);
            averageWatingTime += startTime;
            averageTurnAroundTime = averageWatingTime + models.get(i).getBurstTime() + models.get(i).getWaitingTime();
        }


        System.out.println(averageWatingTime/models.size() + " " + averageTurnAroundTime/models.size());





    }

    private void shortestJobFirstNonPreemtive() {
        List<NonPreemtive> models = new ArrayList<>();
        List<NonPreemtive> tempModelList = new ArrayList<>();


        System.out.println("Enter the number of process");
        Scanner scanner = new Scanner(System.in);
        int numberOfprocess = scanner.nextInt();

        for (int i = 1; i <= numberOfprocess; i++) {

            System.out.println("Name of the process: " + i);
            int nameOfProcess = scanner.nextInt();
            System.out.println("Arrival time: "+ i);
            int arrivalTime = scanner.nextInt();
            System.out.println("Burst time of the process: " + i);
            int brustTime = scanner.nextInt();


            NonPreemtive nonPreemtive = new NonPreemtive();
            nonPreemtive.setProcessName(nameOfProcess);
            nonPreemtive.setArrivalTime(arrivalTime);
            nonPreemtive.setBrustTime(brustTime);
            nonPreemtive.setDone(false);

            models.add(nonPreemtive);
            tempModelList.add(nonPreemtive.copy(nonPreemtive));
        }
        Collections.sort(models, (t1, t2) -> t1.getArrivalTime() - t2.getArrivalTime());
        Collections.sort(tempModelList, (t1, t2) -> t1.getArrivalTime() - t2.getArrivalTime());

        int timing = 0;
        double anotherTime = 0;
        double waitingTime = 0;
        double turnAroundTime = 0;

        timing = timing + tempModelList.get(0).getBrustTime();
        anotherTime = timing - tempModelList.get(0).getBrustTime();
        anotherTime = anotherTime - tempModelList.get(0).getArrivalTime();
        waitingTime += anotherTime;
        turnAroundTime += anotherTime + tempModelList.get(0).getBrustTime();
        tempModelList.get(0).setDone(true);
        boolean done = true;

        while(true){
            done = true;
            List<NonPreemtive> anotherList = new ArrayList<>();


            for(int i=0; i<tempModelList.size(); i++){
                if(tempModelList.get(i).getArrivalTime() <= timing && tempModelList.get(i).isDone() == false){
                    anotherList.add(tempModelList.get(i).copy(tempModelList.get(i)));
                    done = false;
                }
            }

            int min = 1000;
            NonPreemtive nonPreemtive = null;

            for(int i=0; i<anotherList.size(); i++){
                if(anotherList.get(i).getBrustTime() < min){
                    min = anotherList.get(i).getBrustTime();
                    nonPreemtive = anotherList.get(i);
                }
            }

            if(nonPreemtive != null){
                timing = timing + nonPreemtive.getBrustTime();
                anotherTime = timing - nonPreemtive.getBrustTime();
                anotherTime = anotherTime - nonPreemtive.getArrivalTime();
                waitingTime += anotherTime;
                turnAroundTime += anotherTime + nonPreemtive.getBrustTime();

                for(int i=0; i<tempModelList.size(); i++){
                    if(tempModelList.get(i).getProcessName() == nonPreemtive.getProcessName()){
                        tempModelList.get(i).setDone(true);
                        break;
                    }

                }
            }


            if (done)
                break;
        }
        System.out.println(waitingTime/models.size());
        System.out.println(turnAroundTime/models.size());

    }

    private void shortestJobFirst() {
        List<Model> models = new ArrayList<>();
        List<Model> tempModelList = new ArrayList<>();

        System.out.println("Enter the number of process");
        Scanner scanner = new Scanner(System.in);
        int numberOfprocess = scanner.nextInt();

        for (int i = 1; i <= numberOfprocess; i++) {
            System.out.println("Name of the process: " + i);
            String nameOfProcess = scanner.nextLine();
            nameOfProcess = scanner.nextLine();
            System.out.println("Burst time of the process: " + i);
            int brustTime = scanner.nextInt();

            Model model = new Model();
            model.setProcessName(nameOfProcess);
            model.setBrustTime(brustTime);

            models.add(model);
            tempModelList.add(model.copy(model));
        }

        Collections.sort(models, (t1, t2) -> t1.getBrustTime() - t2.getBrustTime());
        double averageWatingTime = 0;
        double tempAverageWatingTime = 0;
        double turnArourdTime = 0;
        double tempTurnAroundTime = 0;

        for (int i = 0; i < numberOfprocess; i++) {
            if (i > 0) {
                tempAverageWatingTime += models.get(i - 1).getBrustTime();
                averageWatingTime += tempAverageWatingTime;
            }
            tempTurnAroundTime += models.get(i).getBrustTime();
            turnArourdTime += tempTurnAroundTime;
        }
        System.out.println("Average waiting time: " + averageWatingTime / models.size());
        System.out.println("Average turnaround time: " + turnArourdTime / models.size());

    }

    private void firstComeFirstServe() {
        List<Model> models = new ArrayList<>();
        List<Model> tempModelList = new ArrayList<>();

        System.out.println("Enter the number of process");
        Scanner scanner = new Scanner(System.in);
        int numberOfprocess = scanner.nextInt();

        for (int i = 1; i <= numberOfprocess; i++) {
            System.out.println("Name of the process: " + i);
            String nameOfProcess = scanner.nextLine();
            nameOfProcess = scanner.nextLine();
            System.out.println("Burst time of the process: " + i);
            int brustTime = scanner.nextInt();

            Model model = new Model();
            model.setProcessName(nameOfProcess);
            model.setBrustTime(brustTime);

            models.add(model);
            tempModelList.add(model.copy(model));
        }
        double timing = 0;
        double averageWatingTime = 0;
        double averageTurnAroundTime = 0;

        for(int i=0; i<tempModelList.size(); i++){
            timing = timing + tempModelList.get(i).getBrustTime();
            double startTime = timing - tempModelList.get(i).getBrustTime();
            models.get(i).setWaitingTime(startTime);
            averageWatingTime += startTime;
            System.out.println(startTime);
            System.out.println(models.get(i).getBrustTime());
            averageTurnAroundTime = averageWatingTime + models.get(i).getBrustTime() + models.get(i).getWaitingTime();
        }


        System.out.println(averageWatingTime/models.size() + " " + averageTurnAroundTime/models.size());

    }

    public static void main(String[] args) {
        new Main();
    }
}
