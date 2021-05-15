package com.kpatel.covid19tracker.Model;

public class CountryModel {


    String flag, country, cases, todayCases, death, todayDeath, recoveredCase, activeCases, criticalCases;

    public CountryModel() {

    }

    public CountryModel(String flag, String country, String cases, String todayCases, String death, String todayDeath, String recoveredCase, String activeCases, String criticalCases) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.death = death;
        this.todayDeath = todayDeath;
        this.recoveredCase = recoveredCase;
        this.activeCases = activeCases;
        this.criticalCases = criticalCases;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getRecoveredCase() {
        return recoveredCase;
    }

    public void setRecoveredCase(String recoveredCase) {
        this.recoveredCase = recoveredCase;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(String criticalCases) {
        this.criticalCases = criticalCases;
    }


}
