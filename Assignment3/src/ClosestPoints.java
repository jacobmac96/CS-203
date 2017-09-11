
public class ClosestPoints implements Comparable<ClosestPoints>{
    int pointOneX;
    int pointOneY;
    int pointTwoX;
    int pointTwoY;
    float distance;
    
    ClosestPoints(int oneX, int oneY, int twoX, int twoY)
    {
        pointOneX = oneX;
        pointOneY = oneY;
        pointTwoX = twoX;
        pointTwoY = twoY;
    }
    
    ClosestPoints()
    {
        
    }
    
    public void setPoints(int oneX, int oneY, int twoX, int twoY)
    {
        pointOneX = oneX;
        pointOneY = oneY;
        pointTwoX = twoX;
        pointTwoY = twoY;
    }
    
    public String getPoints()
    {
        return ("Point One: (" + pointOneX + "," + pointOneY + ") " +
                "Point Two: (" + pointTwoX + "," + pointTwoY + ") ");
    }
    
    public void setDistance(float distance)
    {
        this.distance = distance;
    }
    public float getDistance()
    {
        return distance;
    }

    @Override
    public int compareTo(ClosestPoints d2) {
        if(this.distance < d2.distance)
            return -1;
        if(this.distance > d2.distance)
            return 1;
        return 0;
    }

    
}
