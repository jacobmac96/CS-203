
public class ClosestPoints {
    int pointOneX;
    int pointOneY;
    int pointTwoX;
    int pointTwoY;
    float distance;
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
    
}
