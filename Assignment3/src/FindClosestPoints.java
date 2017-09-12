import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindClosestPoints{

    static Scanner pointsScanner = null;
    static int numOfPoints = 0;
    static TwoDPoints setOfPoints[] = null;
    static ClosestPoints minPoints = new ClosestPoints();
    /****************************************************************/
    /*Method: main                                                  */
    /*Purpose: gets file name from arg and starts the sort          */   
    /*Parameters:                                                   */
    /*  int String[] args    file matrix information is stored      */
    /*  Returns: void:                                              */
    /****************************************************************/
    
    public static void main(String[] args)
    {
        String inputFile = null; //stores file name
        try{
            inputFile = args[0];//text file name for matrix information
        }catch(ArrayIndexOutOfBoundsException noFile){
            //error if no file was found
            System.out.println("Please enter a file name.");
            System.exit(1);
        }
        //creates TwoDPoints array from input file
        if(getPoints(inputFile))
        {
            //perform quick sort on x coordinates
            quickSort(0, (numOfPoints - 1));
            //create new array for points sorted by x
            TwoDPoints xSetOfPoints[] = new TwoDPoints[numOfPoints];
            System.arraycopy(setOfPoints, 0, xSetOfPoints, 0, numOfPoints);
            //perform merge sort on y coordinates
            if(mergeSort(setOfPoints))
            {
                //create new array for points sorted by y
                TwoDPoints ySetOfPoints[] = setOfPoints;
                efficientClosestPair(xSetOfPoints, ySetOfPoints);
                printPoints();
                System.out.println("The closest points are" + minPoints.getPoints());
                System.out.println(minPoints.distance);
            }
        }
            
    }
    
    //remove-for debugging
    public static void printPoints() {
        // TODO Auto-generated method stub
        for(int i = 0; i < numOfPoints; i++)
        {
            System.out.println(setOfPoints[i].getXYPoint());
        }
    }


    /****************************************************************/
    /*Method: getPoints                                             */
    /*Purpose: reads the input file and creates a TwoDPoints array  */   
    /*Parameters:                                                   */
    /*          String: inputFile file to read from                 */
    /*  Returns: boolean: if the array was created or not           */
    /****************************************************************/
    
    public static boolean getPoints(String inputFile)
    {
        int currPoint = 0;//keep track of line in file
        File pointsFile = new File(inputFile);//create file from input string
        try {//try to create scanner for file if available
            pointsScanner = new Scanner(pointsFile);
        } catch (FileNotFoundException noFile) {
            System.out.println("Please check if file is in correct location");
            System.exit(2);
        }
        //get the number of points
        if(pointsScanner.hasNextInt())
        {
            numOfPoints = pointsScanner.nextInt();
            pointsScanner.nextLine();
        }
        else
        {
            //no number of points was given
            System.out.println("Make sure number of points is an integer");
            return false;
        }
        //init the points array with number of points
        setOfPoints = new TwoDPoints[numOfPoints];
        //keep scanning through line to get edges
        while(pointsScanner.hasNextInt() && currPoint < numOfPoints)//check if file has another line
        {
            //get x and y coordinate of point
            int newX = pointsScanner.nextInt();
            int newY = pointsScanner.nextInt();    
            //add point to array
            setOfPoints[currPoint++] = new TwoDPoints(newX, newY);
            if(pointsScanner.hasNextLine())
                pointsScanner.nextLine();
                        
        }
        return true;    
    }
    
    /****************************************************************/
    /*Method: quickSort                                             */
    /*Purpose: performs sort on x coordinates using a quick sort    */
    /*                                                      method  */   
    /*Parameters:                                                   */
    /*          int first: first position to look in array          */
    /*          int last: last position to look in array            */
    /*  Returns: void                                               */
    /****************************************************************/
    
    public static void quickSort(int first, int last)
    {
        if(first < last)
        {
            int mid = hoarePartitioning(first , last);
            quickSort(first, mid-1);
            quickSort(mid+1, last);
        }
    }

    /****************************************************************/
    /*Method: hoarePartitioning                                     */
    /*Purpose: uses hoare partitioning algorithm to sort            */  
    /*Parameters:                                                   */
    /*          int left: left position to look in array            */
    /*          int right: right position to look in array          */
    /*  Returns: int: new left(pivot) point                         */
    /****************************************************************/

    public static int hoarePartitioning(int left, int right) {
        int pivotX = setOfPoints[left].getXPoint();//pivot is first element
        int leftToRight = left;//variable that scans from left to right
        int rightToLeft = right + 1;//variable that scans from right to left
        do{
            //increase left point until bigger x is found than pivot
            do
            { 
                leftToRight++; 
            }while( (leftToRight < (numOfPoints -1)) && 
                        (setOfPoints[leftToRight].getXPoint() < pivotX) );
            //decrease right point until smaller x is found than pivot
            do
            { 
                rightToLeft--;
            }while( (rightToLeft > 0) && 
                        (setOfPoints[rightToLeft].getXPoint() > pivotX) );
            //perform swap leftToRight with RighttoLeft
            TwoDPoints temp = setOfPoints[leftToRight];
            setOfPoints[leftToRight] = setOfPoints[rightToLeft];
            setOfPoints[rightToLeft] = temp;
        }while( leftToRight < rightToLeft);
        //undo last swap when leftToRight < rightToLeft
        TwoDPoints temp = setOfPoints[leftToRight];
        setOfPoints[leftToRight] = setOfPoints[rightToLeft];
        setOfPoints[rightToLeft] = temp;
        //swap left with rightToLeft
        temp = setOfPoints[left];
        setOfPoints[left] = setOfPoints[rightToLeft];
        setOfPoints[rightToLeft] = temp;
        //return new left point
        return rightToLeft;
    }
    
    /****************************************************************/
    /*Method: mergeSort                                             */
    /*Purpose: uses merge sort method to sort y coordinates         */  
    /*Parameters:                                                   */
    /*          TwoDPoints[] fullArray: current full array          */
    /*  Returns: boolean: if points were sorted                     */
    /****************************************************************/
    
    public static boolean mergeSort(TwoDPoints[] fullArray)
    {
        //make sure array has more than one point
        if(fullArray.length > 1)
        {
            //get the index of the split
            int splitOne = (int) Math.floor(fullArray.length / 2);
            int SplitTwo = fullArray.length - splitOne;
            //create TwoDPoint array to hold the split
            TwoDPoints halfOne[] = new TwoDPoints[splitOne];
            TwoDPoints halfTwo[] = new TwoDPoints[SplitTwo];
            //copy the corresponding portion of array
            System.arraycopy(fullArray, 0, halfOne, 0, splitOne);
            System.arraycopy(fullArray, splitOne, halfTwo, 0, SplitTwo);
            //call mergesort for each split
            mergeSort(halfOne);
            mergeSort(halfTwo);
            //merge back together
            merge(halfOne, halfTwo, fullArray);
            return true;
        }
        else
            return false;
    }
    
    /****************************************************************/
    /*Method: merge                                                 */
    /*Purpose: uses the smaller arrays to sort bigger array         */  
    /*Parameters:                                                   */
    /*        TwoDPoints[] halfOne: first half sorted               */
    /*        TwoDPoints[] halfTwo: second half sorted              */
    /*        TwoDPoints[] fullArray full array to store halfed     */
    /*  Returns: void                                               */
    /****************************************************************/
    
    public static void merge(TwoDPoints[] halfOne, TwoDPoints[] halfTwo, TwoDPoints[] fullArray)
    {
        int counterOne = 0;//counter for halfOne
        int counterTwo = 0;//counter for haldTwo
        int arrayIndex = 0;//index of fullArray
        //add until one array is empty
        while( (counterOne < halfOne.length) && (counterTwo < halfTwo.length) )
        {
            //check which value is smaller and add to array
            if(halfOne[counterOne].getYPoint() <= halfTwo[counterTwo].getYPoint())
            {
                fullArray[arrayIndex] = halfOne[counterOne];
                counterOne++;
            }
            else
            {
                fullArray[arrayIndex] = halfTwo[counterTwo];
                counterTwo++;
            }
            //increase index
            arrayIndex++;
        }
        //check which array has left over elements
        if(counterOne == halfOne.length)
        {
            System.arraycopy(halfTwo, counterTwo, fullArray, arrayIndex, (halfTwo.length - counterTwo));
        }
        else
            System.arraycopy(halfOne, counterOne, fullArray, arrayIndex, (halfOne.length - counterOne));
    }
    
    
    /****************************************************************/
    /*Method: efficientClosestPair                                  */
    /*Purpose: solves the closest-pair problem using divide&conquer */  
    /*Parameters:                                                   */
    /*          TwoDPoints[] xSortedArray: points sorted by x       */
    /*          TwoDPoints[] ySortedArray: points sorted by y       */
    /*  Returns: float: closest distance                            */
    /****************************************************************/
    
    public static float efficientClosestPair(TwoDPoints[] xSortedArray, TwoDPoints[] ySortedArray) 
    {
       float dLeft;//closest distance on left
       float dRight;//closest distance on right
       float dMinSqr = 0;//min distance squared
       //do brute force method if 3 or less points
       if(xSortedArray.length <= 3)
       {
           return bruteClosestPair(xSortedArray);
       }
       else
       {
           
           int halfOne = (int)(xSortedArray.length / 2);
           int halfTwo = xSortedArray.length - halfOne;
           TwoDPoints xSortLeft[] = new TwoDPoints[halfOne];
           TwoDPoints xSortRight[] = new TwoDPoints[halfTwo];
           System.arraycopy(xSortedArray, 0, xSortLeft, 0, halfOne);
           System.arraycopy(xSortedArray, halfOne, xSortRight, 0, halfTwo);
           TwoDPoints ySortLeft[] = new TwoDPoints[halfOne];
           TwoDPoints ySortRight[] = new TwoDPoints[halfTwo];
           System.arraycopy(ySortedArray, 0, ySortLeft, 0, halfOne);
           System.arraycopy(ySortedArray, halfOne, ySortRight, 0, halfTwo);
           
           dLeft = efficientClosestPair(xSortLeft,ySortLeft);
           dRight = efficientClosestPair(xSortRight,ySortRight);
           
           float minDis = Math.min(dLeft, dRight);
           int middle = xSortedArray[halfOne-1].getXPoint();
           TwoDPoints subArray[] = new TwoDPoints[numOfPoints];
           int subArrayCounter = 0;
           for(int index = 0; index < ySortedArray.length; index++)
           {
               if((Math.abs(ySortedArray[index].getXPoint() - middle) < minDis))
               {
                   subArray[subArrayCounter] = ySortedArray[index];
                   subArrayCounter++;
               }
           }
           dMinSqr = minDis * minDis;
           for(int index = 0; index < subArrayCounter - 1; index++)
           {
               int nextPoint = index + 1;
               while( (nextPoint <= (subArrayCounter-1)) && 
                       (((subArray[nextPoint].getYPoint() - subArray[index].getYPoint()) * (subArray[nextPoint].getYPoint() - subArray[index].getYPoint())) < dMinSqr))
               {
                   ClosestPoints testPoints = new ClosestPoints
                               (subArray[nextPoint].getXPoint(),subArray[nextPoint].getYPoint(), subArray[index].getXPoint(),subArray[index].getYPoint());
                   testPoints.setDistance(dist(subArray[nextPoint],subArray[index]));
                   if(testPoints.compareTo(minPoints) == -1)
                       minPoints = testPoints;       
                   dMinSqr = minPoints.distance;
                   nextPoint++;
               }
           }  
           
       }
       return (dMinSqr);
    }
    
    public static float bruteClosestPair(TwoDPoints[] pointsArray)
    {
            float min = dist(pointsArray[0],pointsArray[1]);
            for(int indexOne = 0; indexOne <pointsArray.length - 1; indexOne++)
            {
                for(int indexTwo = 0; indexTwo < pointsArray.length; indexTwo++)
                {
                    if (dist(pointsArray[indexOne], pointsArray[indexTwo]) < min || minPoints.getDistance() == 0)
                    {
                        min = dist(pointsArray[indexOne], pointsArray[indexTwo]);
                        minPoints.setPoints(pointsArray[indexOne].getXPoint(), pointsArray[indexOne].getYPoint(), pointsArray[indexTwo].getXPoint(), pointsArray[indexTwo].getYPoint());
                        minPoints.setDistance(min);
                    }
                    
                }
            }
            return min;
    }
    
    public static float dist(TwoDPoints p1, TwoDPoints p2)
    {
        return (float) Math.sqrt( (p1.getXPoint() - p2.getXPoint())*(p1.getXPoint() - p2.getXPoint()) +
                     (p1.getYPoint() - p2.getYPoint())*(p1.getYPoint() - p2.getYPoint())
                   );
    }
    
    public static float distsqrd(TwoDPoints p1, TwoDPoints p2)
    {
        return (float)( (p1.getXPoint() - p2.getXPoint())*(p1.getXPoint() - p2.getXPoint()) +
                (p1.getYPoint() - p2.getYPoint())*(p1.getYPoint() - p2.getYPoint())
              );
    }

}
