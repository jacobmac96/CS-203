
public class TwoDPoints {

    int xPoint;
    int yPoint;
    int length;
    TwoDPoints(int newXPoint, int newYPoint)
    {
        xPoint = newXPoint;
        yPoint = newYPoint;
    }
    
    public void setXPoint(int newXPoint)
    {
        xPoint = newXPoint;
    }
    
    public int getXPoint()
    {
        return xPoint;
    }
    
    public void setYPoint(int newYPoint)
    {
        yPoint = newYPoint;
    }
    
    public int getYPoint()
    {
        return yPoint;
    }
    public String getXYPoint()
    {
        return (xPoint + " " + yPoint);
    }
    
}
