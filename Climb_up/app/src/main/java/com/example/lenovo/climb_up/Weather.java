package com.example.lenovo.climb_up;

/**
 * Created by lenovo on 2018-03-13.
 */
public class Weather {
    //추가한 변수
    private int image;
    private String hour;
    private String weather;
    private String temp;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getWeather() {

        return weather;
    }

    public String getTemp() {

        return temp;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }


    public Weather(int image, String hour, String temp, String weather) {
        this.image = image;
        this.hour =  hour;
        this.temp = temp;
        this.weather = weather;
    }
}