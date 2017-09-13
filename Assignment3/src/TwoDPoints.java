/*********************************************************************/
/*Jacob MacDermaid                                                   */
/*Login ID: macd0919                                                 */
/*CS-203, Summer 2017                                                */
/*Programming Assignment 3                                           */
/*TwoDPoints Class: This class stores the x and y coordinates a point*/
/*********************************************************************/

public class TwoDPoints {

    int xPoint;
    int yPoint;
    
    /****************************************************************/
    /*Method: ClosestPoints                                         */
    /*Purpose: constructor for 2D points                            */   
    /*Parameters:                                                   */
    /*          int newXPoint: x coordinate                         */
    /*          int newYPoint: y coordinate                         */
    /*  Returns:                                                    */
    /****************************************************************/
    
    TwoDPoints(int newXPoint, int newYPoint)
    {
        xPoint = newXPoint;
        yPoint = newYPoint;
    }
    
    /****************************************************************/
    /*Method: setXPoint/ getXPoint                                  */
    /*Purpose: Accessor and mutator for setting x coordinate        */   
    /*Parameters:                                                   */
    /*          int newXPoint: x coordinate                         */
    /*  Returns:    int: x coordinate                               */
    /****************************************************************/
    
    public void setXPoint(int newXPoint)
    {
        xPoint = newXPoint;
    }
    
    public int getXPoint()
    {
        return xPoint;
    }
    
    /****************************************************************/
    /*Method: setYPoint/ getYPoint                                  */
    /*Purpose: Accessor and mutator for setting y coordinate        */   
    /*Parameters:                                                   */
    /*          int newYPoint: Y coordinate                         */
    /*  Returns:    int: Y coordinate                               */
    /****************************************************************/
    
    public void setYPoint(int newYPoint)
    {
        yPoint = newYPoint;
    }
    
    public int getYPoint()
    {
        return yPoint;
    }
    
}
