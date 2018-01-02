public class Model {
    private String processName;
    private int brustTime;
    private double waitingTime;
    private double turnAroundTime;

    public Model() {
    }

    public Model(String processName, int brustTime, double waitingTime, double turnAroundTime) {
        this.processName = processName;
        this.brustTime = brustTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public Model copy(Model model){
        return new Model(model.getProcessName(), model.getBrustTime(), model.getWaitingTime(), model.getTurnAroundTime());
    }

    @Override
    public String toString() {
        return "Model{" +
                "processName='" + processName + '\'' +
                ", brustTime=" + brustTime +
                ", waitingTime=" + waitingTime +
                ", turnAroundTime=" + turnAroundTime +
                '}';
    }
}
