/*********************************************************************/
/*Jacob MacDermaid                                                   */
/*Login ID: macd0919                                                 */
/*CS-203, Summer 2017                                                */
/*Programming Assignment 3                                           */
/*ClosestPoints Class: This class stores the x and y coordinates of  */
/*                        the cloests points and their distance      */
/*********************************************************************/

public class ClosestPoints implements Comparable<ClosestPoints>{
    int pointOneX;
    int pointOneY;
    int pointTwoX;
    int pointTwoY;
    float distance;
    
    /****************************************************************/
    /*Method: ClosestPoints                                         */
    /*Purpose: constructor for Closest point                        */   
    /*Parameters:                                                   */
    /*          int oneX: x coor. of point one                      */
    /*          int oneY: y coor. of point one                      */
    /*          int twoX: x coor. of point two                      */
    /*          int twoY: t coor. of point two                      */
    /*  Returns:                                                    */
    /****************************************************************/
    
    ClosestPoints(int oneX, int oneY, int twoX, int twoY)
    {
        pointOneX = oneX;
        pointOneY = oneY;
        pointTwoX = twoX;
        pointTwoY = twoY;
    }
    
    /****************************************************************/
    /*Method: ClosestPoints                                         */
    /*Purpose: constructor for empty Closest point                  */   
    /*Parameters:                                                   */
    /*  Returns:                                                    */
    /****************************************************************/
    
    ClosestPoints()
    {
        
    }
    
    
    /****************************************************************/
    /*Method: setPoints/getPoints                                   */
    /*Purpose: Accessor and mutator for setting points              */   
    /*Parameters:                                                   */
    /*          int oneX: x coor. of point one                      */
    /*          int oneY: y coor. of point one                      */
    /*          int twoX: x coor. of point two                      */
    /*          int twoY: t coor. of point two                      */
    /*  Returns:     String: output of closest points               */
    /****************************************************************/
    
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
    
    /****************************************************************/
    /*Method: setDistance/getPoints                                 */
    /*Purpose: Accessor and mutator for setting distance            */   
    /*Parameters:                                                   */
    /*          float distance: distance between two points         */
    /*  Returns:     float: distance of closest points              */
    /****************************************************************/
    
    public void setDistance(float distance)
    {
        this.distance = distance;
    }
    public float getDistance()
    {
        return distance;
    }

    /****************************************************************/
    /*Method: compareTo                                             */
    /*Purpose: Accessor and mutator for setting distance            */   
    /*Parameters:                                                   */
    /*          ClosestPoints distanceTwo: distance to compare to   */
    /*  Returns:     int: if distance is great or less than current */
    /****************************************************************/
    
    @Override
    public int compareTo(ClosestPoints distanceTwo) {
        if(this.distance < distanceTwo.distance)
            return -1;
        if(this.distance > distanceTwo.distance)
            return 1;
        return 0;
    }
   
}
