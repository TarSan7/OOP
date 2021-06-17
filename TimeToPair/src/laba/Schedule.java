package laba;

public class Schedule extends Time{
    private String subject;
    private int classroom;

    public Schedule(String subject, int classroom, int h, int m, int s) {
        this.subject = subject;
        this.classroom = classroom;
        this.hours = h;
        this.minutes = m;
        this.seconds = s;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject() { return subject; }

    public int getClassroom(){
        return this.classroom;
    }

    public int getHours(){
        return this.hours;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public int getSeconds(){
        return this.seconds;
    }

    @Anotation(name = "Class that find a time to pair start")
    public void timeToPair(int h, int m, int s){
        int secAll = h * 3600 + m * 60 + s;
        int secStartPair = hours * 3600 + minutes * 60 + seconds;
        if(secAll >= secStartPair){
            System.out.println("Pair has already started.");
        }else{
            System.out.println("Before the start of the pair : " + (secStartPair - secAll) / 3600 + " hours");
            System.out.println((secStartPair - secAll) % 3600 / 60 + " minutes");
            System.out.println((secStartPair - secAll) % 60 + " seconds");
        }
    }
}
