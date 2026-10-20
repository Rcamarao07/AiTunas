package Semana5;

public final class Time {
    final int minutos;
    final int segundos;
    public Time(int minutos,int segundos){
        this.minutos=minutos;
        this.segundos=segundos;
    }
    public Time(String tempo){
        String[] partesTempo=tempo.split(":");
        this.minutos=Integer.parseInt(partesTempo[0]);
        this.segundos=Integer.parseInt(partesTempo[1]);
    }
    public int getMinutos() {
        return this.minutos;
    }
    public int getSegundos(){
        return this.segundos;
    }
    public int totalSegundos(){
        return getMinutos()*60+getSegundos();
    }
    public Time newAddTime(Time tempo){
        int totalSoma = this.totalSegundos() + tempo.totalSegundos();
        int novosMinutos = totalSoma / 60;
        int novosSegundos = totalSoma % 60;
        return new Time(novosMinutos, novosSegundos);
    }
    public Time newSubtractTime(Time tempo){
        if(this.totalSegundos()< tempo.totalSegundos()){
            throw new IllegalArgumentException("O tempo tem de ser menor que o original");
        }
        int totalSoma = this.totalSegundos() - tempo.totalSegundos();
        int novosMinutos = totalSoma / 60;
        int novosSegundos = totalSoma % 60;
        return new Time(novosMinutos, novosSegundos);
    }
    public boolean isBigger(Time tempo){
        return tempo.totalSegundos()>this.totalSegundos();
    }
    public boolean isSmaller(Time tempo){
        return tempo.totalSegundos()<this.totalSegundos();
    }
    @Override
    public String toString(){
        return String.format("%02d:%02d",this.minutos,this.segundos);
    }
}
