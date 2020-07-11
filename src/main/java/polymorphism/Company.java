package polymorphism;

public class Company {
    private String companyName;
    private int roomsQuantity;

    public Company(String companyName, int roomsQuantity) {
        System.out.println("jestem w klasie Company");
        this.companyName = companyName;
        this.roomsQuantity = roomsQuantity;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", roomsQuantity=" + roomsQuantity +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }
}
