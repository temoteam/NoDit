package ru.temoteam.nodit.Code;

public class Lesson {
    private String name;
    private String dz;
    private byte mark;

    public Lesson(String name,String mark,String dz){
        this.dz=dz;
        try {
            this.mark = Byte.parseByte(mark);
        }
        catch (RuntimeException e){
            this.mark = 0;
        }
        this.name=name;
    }

    public Lesson(String name,byte mark,String dz){
        this.dz=dz;
        this.mark=mark;
        this.name=name;
    }

    @Override
    public String toString() {
        return name+"; "+dz+":"+mark;
    }
}
