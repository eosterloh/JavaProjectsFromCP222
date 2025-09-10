public class Location {
    privat int displacement;
    public Location(int dis) {
        //this.displacement = dis;
        setDisplacement(dis)
    }

    public int getDisplacement() {
        return displacement;
    }
    public void addDisplacement(int displaced) {
        displacement += displaced;
    }
}
