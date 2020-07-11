package polymorphism;

public class OpenSpace extends Company {
    private int openSpaceNo;
    private String openSpaceName;
    private int openSpaceCapacity;

    public OpenSpace(String companyName, int roomsQuantity, int openSpaceNo, String openSpaceName, int openSpaceCapacity) {
        super(companyName, roomsQuantity); // wywołanie konstruktora klasy nadrzednej
        this.openSpaceNo = openSpaceNo;
        this.openSpaceName = openSpaceName;
        this.openSpaceCapacity = openSpaceCapacity;
        System.out.println("Jestem w konstruktorze OpenSpace");
    }

    // metoda do liczenia wolnych pokoi w firmie
    public int calculateFreeRooms() {
        System.out.println("Pozostało wolnych pokoi " +  (super.getRoomsQuantity() -1));
        return super.getRoomsQuantity() -1;
    }

    @Override
    public String toString() {
        return "OpenSpace{" +
                "openSpaceNo=" + openSpaceNo +
                ", openSpaceName='" + openSpaceName + '\'' +
                ", openSpaceCapacity=" + openSpaceCapacity +
                "} " + super.toString();
    }

    public int getOpenSpaceNo() {
        return openSpaceNo;
    }

    public void setOpenSpaceNo(int openSpaceNo) {
        this.openSpaceNo = openSpaceNo;
    }

    public String getOpenSpaceName() {
        return openSpaceName;
    }

    public void setOpenSpaceName(String openSpaceName) {
        this.openSpaceName = openSpaceName;
    }

    public int getOpenSpaceCapacity() {
        return openSpaceCapacity;
    }

    public void setOpenSpaceCapacity(int openSpaceCapacity) {
        this.openSpaceCapacity = openSpaceCapacity;
    }
}
