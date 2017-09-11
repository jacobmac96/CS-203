import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClosetPair{

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
        //creates matrix from input file
        if(getPoints(inputFile))
        {
            quickSort(0, (numOfPoints - 1));
            TwoDPoints xSetOfPoints[] = new TwoDPoints[numOfPoints];
            System.arraycopy(setOfPoints, 0, xSetOfPoints, 0, numOfPoints);
            mergeSort(setOfPoints);
            TwoDPoints ySetOfPoints[] = setOfPoints;
            float d = efficientClosestPair(xSetOfPoints, ySetOfPoints);
            printPoints();
            System.out.println("The closest points are" + minPoints.getPoints());
            System.out.println(minPoints.distance);
        }
            
    }
    
    
    public static void printPoints() {
        // TODO Auto-generated method stub
        for(int i = 0; i < numOfPoints; i++)
        {
            System.out.println(setOfPoints[i].getXYPoint());
        }
    }


    /****************************************************************/
    /*Method: createMatrix                                          */
    /*Purpose: reads the input file and creates a adjacency matrix  */   
    /*Parameters:                                                   */
    /*          String: inputFile file to read from                 */
    /*  Returns: boolean: if the matrix was created or not          */
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
            System.out.println("Make sure number of nodes is an integer");
            return false;
        }
        //init the adjacMatrix with number of nodes
        setOfPoints = new TwoDPoints[numOfPoints];
        //keep scanning through line to get edges
        while(pointsScanner.hasNextInt() && currPoint < numOfPoints)//check if file has another line
        {
            int newX = pointsScanner.nextInt();
            int newY = pointsScanner.nextInt();                    
            setOfPoints[currPoint++] = new TwoDPoints(newX, newY);
            if(pointsScanner.hasNextLine())
                pointsScanner.nextLine();
                        
        }
        return true;    
    }
    
    
    public static void quickSort(int left, int right)
    {
        if(left < right)
        {
            int s = hoarePartitioning(left , right);
            quickSort(left, s-1);
            quickSort(s+1, right);
        }
    }


    public static int hoarePartitioning(int left, int right) {
        int p = setOfPoints[left].getXPoint();
        int i = left - 1;
        int j = right + 1;
        do{
            do{ i++; } while( (i < (numOfPoints -1)) && (setOfPoints[i].getXPoint() <= p) );
            do{ j--; } while( (j > 0) && (setOfPoints[j].getXPoint() >= p) );
            TwoDPoints temp = setOfPoints[i];
            setOfPoints[i] = setOfPoints[j];
            setOfPoints[j] = temp;
        }while( i < j);
       
        TwoDPoints temp = setOfPoints[i];
        setOfPoints[i] = setOfPoints[j];
        setOfPoints[j] = temp;
        temp = setOfPoints[left];
        setOfPoints[left] = setOfPoints[j];
        setOfPoints[j] = temp;
        
        return j;
    }
    
    public static void mergeSort(TwoDPoints[] A)
    {
        if(A.length > 1)
        {
            int N1 = (int) Math.floor(A.length / 2);
            int N2 = A.length - N1;
            TwoDPoints B[] = new TwoDPoints[N1];
            TwoDPoints C[] = new TwoDPoints[N2];
            System.arraycopy(A, 0, B, 0, N1);
            System.arraycopy(A, N1, C, 0, N2);
            mergeSort(B);
            mergeSort(C);
            merge(B, C, A);
        }
    }
    public static void merge(TwoDPoints[] B, TwoDPoints[] C, TwoDPoints[] A)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        while( (i < B.length) && (j < C.length) )
        {
            if(B[i].getYPoint() <= C[j].getYPoint())
            {
                A[k] = B[i];
                i++;
            }
            else
            {
                A[k] = C[j];
                j++;
            }
            k++;
        }
        if(i == B.length)
        {
            System.arraycopy(C, j, A, k, C.length -j);
        }
        else
            System.arraycopy(B, i, A, k, B.length -i);
    }
    
    public static float efficientClosestPair(TwoDPoints[] P, TwoDPoints[] Q) 
    {
       float dLeft;
       float dRight;
       float dMinSqr = 0;
       if(P.length == 1)
           return 0;
       if(P.length <= 3)
       {
           return bruteClosestPair(P);
       }
       else
       {
           int N1 = (int)(P.length / 2);
           int N2 = P.length - N1;
           TwoDPoints PLeft[] = new TwoDPoints[N1];
           TwoDPoints PRight[] = new TwoDPoints[N2];
           System.arraycopy(P, 0, PLeft, 0, N1);
           System.arraycopy(P, N1, PRight, 0, N2);
           TwoDPoints QLeft[] = new TwoDPoints[N1];
           TwoDPoints QRight[] = new TwoDPoints[N2];
           System.arraycopy(Q, 0, QLeft, 0, N1);
           System.arraycopy(Q, N1, QRight, 0, N2);
           
           dLeft = efficientClosestPair(PLeft,QLeft);
           dRight = efficientClosestPair(PRight,QRight);
           
           float d = Math.min(dLeft, dRight);
           int m = P[N1-1].getXPoint();
           TwoDPoints S[] = new TwoDPoints[numOfPoints];
           int j = 0;
           for(int i = 0; i < Q.length; i++)
           {
               if((Math.abs(Q[i].getXPoint() - m) < d))
               {
                   S[j] = Q[i];
                   j++;
               }
           }
           dMinSqr = d * d;
           for(int i = 0; i < j - 1; i++)
           {
               int k = i + 1;
               while( (k <= (j-1)) && 
                       (((S[k].getYPoint() - S[i].getYPoint()) * (S[k].getYPoint() - S[i].getYPoint())) < dMinSqr))
               {
                   ClosestPoints testPoints = new ClosestPoints
                               (S[k].getXPoint(),S[k].getYPoint(), S[i].getXPoint(),S[i].getYPoint());
                   testPoints.setDistance(dist(S[k],S[i]));
                   if(testPoints.compareTo(minPoints) == -1)
                       minPoints = testPoints;       
                   dMinSqr = minPoints.distance;
                   k = k+1;
               }
           }  
           
       }
       return (dMinSqr);
    }
    
    public static float bruteClosestPair(TwoDPoints[] P)
    {
            float min = 100;
            for(int i = 0; i <P.length - 1; i++)
            {
                for(int j = 0; j < P.length; j++)
                {
                    if (dist(P[i], P[j]) < min || minPoints.getDistance() == 0)
                    {
                        min = dist(P[i], P[j]);
                        minPoints.setPoints(P[i].getXPoint(), P[i].getYPoint(), P[j].getXPoint(), P[j].getYPoint());
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
