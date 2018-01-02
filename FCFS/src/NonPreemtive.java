public class NonPreemtive {
    private int processName;
    private int arrivalTime;
    private int brustTime;
    private double waitingTime;
    private double turnAroundTime;
    private int unitTime;
    private int finishTime;
    private boolean done;

    public NonPreemtive() {
    }

    public NonPreemtive(int processName, int arrivalTime, int brustTime, double waitingTime, double turnAroundTime, boolean done) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.brustTime = brustTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
        this.done = done;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(int unitTime) {
        this.unitTime = unitTime;
    }

    public int getProcessName() {
        return processName;
    }

    public void setProcessName(int processName) {
        this.processName = processName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBrustTime() {
        return brustTime;
    }

    public void setBrustTime(int brustTime) {
        this.brustTime = brustTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(double turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public NonPreemtive copy(NonPreemtive nonPreemtive){
        return new NonPreemtive(nonPreemtive.getProcessName(),nonPreemtive.getArrivalTime(), nonPreemtive.getBrustTime(),nonPreemtive.getWaitingTime(), nonPreemtive.getTurnAroundTime(), nonPreemtive.isDone());
    }

    @Override
    public String toString() {
        return "NonPreemtive{" +
                "processName=" + processName +
                ", arrivalTime=" + arrivalTime +
                ", brustTime=" + brustTime +
                ", waitingTime=" + waitingTime +
                ", turnAroundTime=" + turnAroundTime +
                ", done=" + done +
                '}';
    }
}
