package edu.eci.cvds.servlet;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
@ManagedBean(name = "BackingBean")
@ApplicationScoped
public class BackingBean {
    private double mean;
    private double mode;
    private double deviation;
    private double variance;
    private ArrayList<String> todos;
    private ArrayList<Integer> data;

    public BackingBean() {
        todos = new ArrayList<>();
    }



    public void calculate(String num){
        data = new ArrayList<>();
        String[] temp = num.split(";");
        for(String s: temp){
            data.add(Integer.parseInt(s));
        }
        todos.add(data.toString());
        calculateMean(data);
        calculateMode(data);
        calculateVariance(data);
        calculateStandardDeviation(data);
    }

    public double calculateMode(ArrayList<Integer> data){
        int maxValue = 0, maxCount = 0, i, j;
        for (i = 0; i < data.size(); i++) {
            int count = 0;
            for (j = 0; j < data.size(); j++) {
                if (data.get(j) == data.get(i) && i != j)
                    ++count;
            }
            if(maxCount < count){
                maxCount = count;
                maxValue = data.get(i);
            }
        }
        mode = maxValue;
        return mode;
    }

    public double calculateVariance(ArrayList<Integer> data){
        for(int i = 0 ; i<data.size(); i++){
            double range;
            range = Math.pow(data.get(i) - calculateMean(data),2f);
            variance += range;
        }
        variance /= 10f;
        return variance;
    }

    public double calculateStandardDeviation(ArrayList<Integer> data){
        deviation = Math.sqrt(calculateVariance(data));
        return deviation;
    }



    public double calculateMean(ArrayList<Integer> data){
        double acum = 0;
        for(Integer i: data){
            acum += i;
        }
        mean = acum/data.size();
        return mean;
    }

    public void restart(){
        data.clear();
        setMean(0);
        setDeviation(0);
        setMode(0);
        setVariance(0);
    }

    public ArrayList<String> getAnteriores() {
        return todos;
    }
    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMode() {
        return mode;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }
}
