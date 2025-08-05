package JDprogram;

class DataBase {
    private String name;
    private int rollno;
    private  String department;

    public DataBase(String name , int rollno , String department) {
        this.name = name;
        this.rollno = rollno;
        this.department = department;
    }

    public String getName(){
        return name;
    }
    public int getRollno(){
        return rollno;
    }
    public String getDept(){
        return department;
    }
    public String toString(){
        return String.format("%s\t %d\t %s",name,rollno,department);
    }
}
