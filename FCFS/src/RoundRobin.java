public class RoundRobin {
    private String processName;
    private int burstTime;
    private int waitngTime;
    private int turnAroundTime;

    public RoundRobin() {
    }

    public RoundRobin(String processName, int burstTime, int waitngTime, int turnAroundTime) {
        this.processName = processName;
        this.burstTime = burstTime;
        this.waitngTime = waitngTime;
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

    public int getWaitngTime() {
        return waitngTime;
    }

    public void setWaitngTime(int waitngTime) {
        this.waitngTime = waitngTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public RoundRobin copy(RoundRobin roundRobin){
        return new RoundRobin(roundRobin.getProcessName(), roundRobin.getBurstTime(), roundRobin.getWaitngTime(), roundRobin.getTurnAroundTime());
    }

    @Override
    public String toString() {
        return "RoundRobin{" +
                "processName='" + processName + '\'' +
                ", burstTime=" + burstTime +
                ", waitngTime=" + waitngTime +
                ", turnAroundTime=" + turnAroundTime +
                '}';
    }
}
