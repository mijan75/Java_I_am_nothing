public class PriorityModel {
    private String processName;
    private int burstTime;
    private int priority;
    private double waitingTime;
    private double turnAroundTime;

    public PriorityModel() {
    }

    public PriorityModel(String processName, int burstTime, int priority, double waitingTime, double turnAroundTime) {
        this.processName = processName;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public PriorityModel copy(PriorityModel priorityModel){
        return new PriorityModel(priorityModel.processName, priorityModel.getBurstTime(), priorityModel.getPriority(), priorityModel.getWaitingTime(), priorityModel.getTurnAroundTime());
    }

    @Override
    public String toString() {
        return "PriorityModel{" +
                "processName='" + processName + '\'' +
                ", burstTime=" + burstTime +
                ", priority=" + priority +
                ", waitingTime=" + waitingTime +
                ", turnAroundTime=" + turnAroundTime +
                '}';
    }
}
